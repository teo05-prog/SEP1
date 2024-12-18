package view.pets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ModelManager;
import model.Pets.Bird;
import model.Pets.PetList;
import utils.PetsXML;
import view.ViewHandler;

public class BirdsViewController
{
  @FXML private TableView<Bird> birdTable;
  @FXML private TableColumn<Bird, String> nameColumn;
  @FXML private TableColumn<Bird, Integer> ageColumn;
  @FXML private TableColumn<Bird, String> colourColumn;
  @FXML private TableColumn<Bird, Character> genderColumn;
  @FXML private TableColumn<Bird, String> commentColumn;
  @FXML private TableColumn<Bird, Integer> priceColumn;
  @FXML private TableColumn<Bird, String> preferredFoodColumn;
  @FXML private TableColumn<Bird, String> specieColumn;
  private PetList petList;
  private ObservableList<Bird> observableBirds = FXCollections.observableArrayList();

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
    birdTable.setEditable(true);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    preferredFoodColumn.setCellValueFactory(
        new PropertyValueFactory<>("preferredFood"));
    specieColumn.setCellValueFactory(new PropertyValueFactory<>("specie"));

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setName(event.getNewValue());
      savePetList();
    });

    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setAge(event.getNewValue());
      savePetList();
    });

    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setColour(event.getNewValue());
      savePetList();
    });

    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Bird, Character> cell = new TextFieldTableCell<>(
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
      Bird bird = event.getRowValue();
      bird.setGender(event.getNewValue());
      savePetList();
    });

    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setComment(event.getNewValue());
      savePetList();
    });

    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setPrice(event.getNewValue());
      savePetList();
    });

    preferredFoodColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    preferredFoodColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setPreferredFood(event.getNewValue());
      savePetList();
    });

    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setSpecie(event.getNewValue());
      savePetList();
    });
  }

  @FXML private void handleAddBird()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddBirdView.fxml"));
      Parent root = loader.load();

      AddBirdViewController controller = loader.getController();
      Scene scene = new Scene(root);
      controller.init(viewHandler, modelManager, scene);

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Bird");
      stage.setScene(scene);
      stage.showAndWait();

      Bird newBird = controller.getNewBird();
      if (newBird != null)
      {
        petList.addPet(newBird);
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

  @FXML private void handleRemoveBird()
  {
    Bird selectedBird = birdTable.getSelectionModel().getSelectedItem();
    if (selectedBird != null)
    {
      petList.removePet(selectedBird);
      savePetList();
      updateTableData();

      new PetsXML();
    }
    else
    {
      showAlert("No selection", "Please select a bird to remove.");
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
    observableBirds = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Bird)
        {
          observableBirds.add((Bird) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    birdTable.setItems(observableBirds);
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