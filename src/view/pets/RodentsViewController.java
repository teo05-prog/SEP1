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
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ModelManager;
import model.Pets.Dog;
import model.Pets.PetList;
import model.Pets.Rodent;
import utils.PetsXML;
import view.ViewHandler;

public class RodentsViewController
{
  @FXML private TableView<Rodent> rodentTable;
  @FXML private TableColumn<Rodent, String> nameColumn;
  @FXML private TableColumn<Rodent, Integer> ageColumn;
  @FXML private TableColumn<Rodent, String> colourColumn;
  @FXML private TableColumn<Rodent, Character> genderColumn;
  @FXML private TableColumn<Rodent, String> commentColumn;
  @FXML private TableColumn<Rodent, Integer> priceColumn;
  @FXML private TableColumn<Rodent, Boolean> doesItBiteColumn;
  @FXML private TableColumn<Rodent, String> specieColumn;
  private PetList petList;
  private ObservableList<Rodent> observableRodents = FXCollections.observableArrayList();

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
    rodentTable.setEditable(true);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    doesItBiteColumn.setCellValueFactory(
        new PropertyValueFactory<>("doesItBite"));
    specieColumn.setCellValueFactory(new PropertyValueFactory<>("specie"));

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setName(event.getNewValue());
      savePetList();
    });

    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setAge(event.getNewValue());
      savePetList();
    });

    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setColour(event.getNewValue());
      savePetList();
    });

    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Rodent, Character> cell = new TextFieldTableCell<>(
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
      Rodent rodent = event.getRowValue();
      rodent.setGender(event.getNewValue());
      savePetList();
    });

    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setComment(event.getNewValue());
      savePetList();
    });

    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setPrice(event.getNewValue());
      savePetList();
    });

    doesItBiteColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
    doesItBiteColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setDoesItBite(event.getNewValue());
      savePetList();
    });

    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setSpecie(event.getNewValue());
      savePetList();
    });
  }

  @FXML private void handleAddRodent()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddRodentView.fxml"));
      Parent root = loader.load();

      AddRodentViewController controller = loader.getController();
      Scene scene = new Scene(root);
      controller.init(viewHandler, modelManager, scene);

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Rodent");
      stage.setScene(scene);
      stage.showAndWait();

      Rodent newRodent = controller.getNewRodent();
      if (newRodent != null)
      {
        petList.add(newRodent);
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

  @FXML private void handleRemoveRodent()
  {
    Rodent selectedRodent = rodentTable.getSelectionModel().getSelectedItem();
    if (selectedRodent != null)
    {
      petList.removePet(selectedRodent);
      savePetList();
      updateTableData();

      new PetsXML();
    }
    else
    {
      showAlert("No selection", "Please select a rodent to remove.");
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
    observableRodents = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Rodent)
        {
          observableRodents.add((Rodent) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    rodentTable.setItems(observableRodents);
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
