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
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setColour(event.getNewValue());
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
    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setPrice(event.getNewValue());
    });
    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Various various = event.getRowValue();
      various.setComment(event.getNewValue());
    });
  }

  @FXML private void handleAddVarious()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddVariousView.fxml"));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Various");
      stage.setScene(new Scene(root));
      stage.showAndWait();

      AddVariousViewController controller = loader.getController();
      Various newVarious = controller.getNewVarious();
      if (newVarious != null)
      {
        observableVarious.add(newVarious);
      }
    }
    catch (Exception e)
    {
      System.out.println("Error opening window: " + e.getMessage());
    }

  }

  private void updateTableData()
  {
    observableVarious = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Various)
        {
          observableVarious.add((Various) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    variousTable.setItems(observableVarious);
  }

  @FXML
  private void handleRemoveVarious()
  {
    Various selectedVarious = variousTable.getSelectionModel()
        .getSelectedItem();
    if (selectedVarious != null)
    {
      observableVarious.remove(selectedVarious);
    }
    else
    {
      showAlert("No selection",
          "Please select a various pet to remove.");
    }
  }

  private void showAlert(String title, String content)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateVarious();
    }
  }

  private void updateVarious()
  {
    variousTable.getItems().clear();
    PetList various = modelManager.getAllVarious(petList);

    for (int i = 0; i < various.size(); i++)
    {
      variousTable.getItems().add(various.getVarious(i));
    }
  }

}
