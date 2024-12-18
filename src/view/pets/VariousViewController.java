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
import model.Pets.Dog;
import model.Pets.PetList;
import model.Pets.Various;
import view.ViewHandler;

public class VariousViewController
{
  @FXML private TableView<Various> variousTable;
  @FXML private TableColumn<Various, String> nameColumn;
  @FXML private TableColumn<Various, Integer> ageColumn;
  @FXML private TableColumn<Various, String> colourColumn;
  @FXML private TableColumn<Various, Character> genderColumn;
  @FXML private TableColumn<Various, String> commentColumn;
  @FXML private TableColumn<Various, Integer> priceColumn;
  @FXML private TableColumn<Various, String> specieColumn;
  private PetList petList;
  private ObservableList<Various> observableVarious = FXCollections.observableArrayList();

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
    variousTable.setEditable(true);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    specieColumn.setCellValueFactory(new PropertyValueFactory<>("specie"));

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setName(event.getNewValue());
      savePetList();
    });

    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setAge(event.getNewValue());
      savePetList();
    });

    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setColour(event.getNewValue());
      savePetList();
    });

    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Various, Character> cell = new TextFieldTableCell<>(
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
      Various various = event.getRowValue();
      various.setGender(event.getNewValue());
      savePetList();
    });

    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setComment(event.getNewValue());
      savePetList();
    });

    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setPrice(event.getNewValue());
      savePetList();
    });

    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setComment(event.getNewValue());
      savePetList();
    });
  }

  @FXML private void handleAddVarious()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddVariousView.fxml"));
      Parent root = loader.load();

      AddVariousViewController controller = loader.getController();
      Scene scene = new Scene(root);
      controller.init(viewHandler, modelManager, scene);

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Various");
      stage.setScene(scene);
      stage.showAndWait();

      Various newVarious = controller.getNewVarious();
      if (newVarious != null)
      {
        petList.add(newVarious);
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

  @FXML private void handleRemoveVarious()
  {
    Various selectedVarious = variousTable.getSelectionModel()
        .getSelectedItem();
    if (selectedVarious != null)
    {
      petList.remove(selectedVarious);
      savePetList();
      updateTableData();
    }
    else
    {
      showAlert("No selection", "Please select a various pet to remove.");
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
    observableVarious = FXCollections.observableArrayList();
    for (int i = 0; i < petList.size(); i++)
    {
      try
      {
        if (petList.get(i) instanceof Various)
        {
          observableVarious.add((Various) petList.get(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    variousTable.setItems(observableVarious);
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
