package view.pets;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ModelManager;
import model.Pets.Dog;
import javafx.scene.control.TableView;
import model.Pets.PetList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.CharacterStringConverter;
import utils.PetsXML;
import view.ViewHandler;

import javafx.scene.control.Alert;

public class DogsViewController
{
  @FXML private TableView<Dog> dogTable;
  @FXML private TableColumn<Dog, String> nameColumn;
  @FXML private TableColumn<Dog, Integer> ageColumn;
  @FXML private TableColumn<Dog, String> colourColumn;
  @FXML private TableColumn<Dog, Character> genderColumn;
  @FXML private TableColumn<Dog, String> commentColumn;
  @FXML private TableColumn<Dog, Integer> priceColumn;
  @FXML private TableColumn<Dog, String> breedColumn;
  @FXML private TableColumn<Dog, String> breederNameColumn;
  private PetList petList;
  private ObservableList<Dog> observableDogs = FXCollections.observableArrayList();

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    this.petList = modelManager.getAllPets();
    updateTableData();
  }

  @FXML public void initialize()
  {
    dogTable.setEditable(true);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    breedColumn.setCellValueFactory(new PropertyValueFactory<>("breed"));
    breederNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("breederName"));

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setName(event.getNewValue());
      savePetList();
    });

    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setAge(event.getNewValue());
      savePetList();
    });

    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setColour(event.getNewValue());
      savePetList();
    });

    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Dog, Character> cell = new TextFieldTableCell<>(
          new CharacterStringConverter());
      cell.textProperty().addListener((obs, oldText, newText) -> {
        if (newText != null && newText.length() > 1)
        {
          cell.setText(oldText);
        }
      });
      return cell;
    });
    genderColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setGender(event.getNewValue());
      savePetList();
    });

    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setComment(event.getNewValue());
      savePetList();
    });

    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setPrice(event.getNewValue());
      savePetList();
    });

    breedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breedColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setBreed(event.getNewValue());
      savePetList();
    });

    breederNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breederNameColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setBreederName(event.getNewValue());
      savePetList();
    });
  }

  @FXML private void handleAddDog()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddDogView.fxml"));
      Parent root = loader.load();

      AddDogViewController controller = loader.getController();
      Scene scene = new Scene(root);
      controller.init(viewHandler, modelManager, scene);

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Dog");
      stage.setScene(scene);
      stage.showAndWait();

      Dog newDog = controller.getNewDog();
      if (newDog != null)
      {
        petList.add(newDog);
        savePetList();
        updateTableData();
      }
    }
    catch (Exception e)
    {
      System.out.println("Error opening window: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @FXML private void handleRemoveDog()
  {
    Dog selectedDog = dogTable.getSelectionModel().getSelectedItem();
    if (selectedDog != null)
    {
      petList.remove(selectedDog);
      savePetList();
      updateTableData();

      new PetsXML();
    }
    else
    {
      showAlert("No selection", "Please select a dog to remove.");
    }
  }

  private void savePetList()
  {
    modelManager.savePets(petList);
  }

  private void showAlert(String title, String content)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void updateTableData()
  {
    observableDogs = FXCollections.observableArrayList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        if (petList.get(i) instanceof Dog)
        {
          observableDogs.add((Dog) petList.get(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    dogTable.setItems(observableDogs);
  }

  public void reset()
  {
    if (modelManager != null)
    {
      petList = modelManager.getAllPets();
      updateTableData();
    }
  }
}
