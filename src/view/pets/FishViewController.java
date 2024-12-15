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
import model.Pets.Fish;
import model.Pets.PetList;
import view.ViewHandler;

public class FishViewController
{
  @FXML private TableView<Fish> fishTable;
  @FXML private TableColumn<Fish, String> nameColumn;
  @FXML private TableColumn<Fish, Integer> ageColumn;
  @FXML private TableColumn<Fish, String> colourColumn;
  @FXML private TableColumn<Fish, Character> genderColumn;
  @FXML private TableColumn<Fish, String> commentColumn;
  @FXML private TableColumn<Fish, Integer> priceColumn;
  @FXML private TableColumn<Fish, String> waterColumn;
  @FXML private TableColumn<Fish, Boolean> predatorColumn;
  @FXML private TableColumn<Fish, String> specieColumn;
  private PetList petList;
  private ObservableList<Fish> observableFish = FXCollections.observableArrayList();

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
    fishTable.setEditable(true);
    nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
    colourColumn.setCellValueFactory(new PropertyValueFactory<>("colour"));
    genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
    commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    waterColumn.setCellValueFactory(new PropertyValueFactory<>("water"));
    predatorColumn.setCellValueFactory(
        new PropertyValueFactory<>("predatorColumn"));
    specieColumn.setCellValueFactory(new PropertyValueFactory<>("specie"));

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setName(event.getNewValue());
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setColour(event.getNewValue());
    });
    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Fish, Character> cell = new TextFieldTableCell<>(
          new CharacterStringConverter());
      cell.textProperty().addListener((obs, oldText, newText) -> {
        if (newText.length() > 1)
        {
          cell.setText(oldText);
        }
      });
      return cell;
    });
    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setPrice(event.getNewValue());
    });
    waterColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    waterColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setComment(event.getNewValue());
    });
    predatorColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
    predatorColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setComment(String.valueOf(event.getNewValue()));
    });
    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Fish fish = event.getRowValue();
      fish.setComment(event.getNewValue());
    });
  }

  private void updateTableData()
  {
    observableFish = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Fish)
        {
          observableFish.add((Fish) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    fishTable.setItems(observableFish);
  }

  @FXML private void handleAddFish()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddFishView.fxml"));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Fish");
      stage.setScene(new Scene(root));
      stage.showAndWait();

      AddFishViewController controller = loader.getController();
      Fish newFish = controller.getNewFish();
      if (newFish != null)
      {
        observableFish.add(newFish);
      }
    }
    catch (Exception e)
    {
      System.out.println("Error opening window: " + e.getMessage());
    }

  }

  private void handleRemoveFish()
  {
    Fish selectedFish = fishTable.getSelectionModel().getSelectedItem();
    if (selectedFish != null)
    {
      observableFish.remove(selectedFish);
    }
    else
    {
      showAlert("No selection", "Please select a dog to remove.");
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
      updateFish();
    }
  }

  private void updateFish()
  {
    fishTable.getItems().clear();
    PetList fish = modelManager.getAllFish(petList);

    for (int i = 0; i < fish.size(); i++)
    {
      fishTable.getItems().add(fish.getFish(i));
    }
  }

}
