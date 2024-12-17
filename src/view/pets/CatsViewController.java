package view.pets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ModelManager;
import model.Pets.Cat;
import model.Pets.Dog;
import model.Pets.PetList;
import javafx.scene.control.TableView;
import view.ViewHandler;

import java.awt.*;
import java.awt.event.ActionEvent;

public class CatsViewController
{
  @FXML private TableView<Cat> catTable;
  @FXML private TableColumn<Cat, String> nameColumn;
  @FXML private TableColumn<Cat, Integer> ageColumn;
  @FXML private TableColumn<Cat, String> colourColumn;
  @FXML private TableColumn<Cat, Character> genderColumn;
  @FXML private TableColumn<Cat, String> commentColumn;
  @FXML private TableColumn<Cat, Integer> priceColumn;
  @FXML private TableColumn<Cat, String> breedColumn;
  @FXML private TableColumn<Cat, String> breederNameColumn;
  private PetList petList;
  private ObservableList<Cat> observableCats = FXCollections.observableArrayList();

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
    catTable.setEditable(true);
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
      Cat cat = event.getRowValue();
      cat.setName(event.getNewValue());
      savePetList();
    });

    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setAge(event.getNewValue());
      savePetList();
    });

    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setColour(event.getNewValue());
      savePetList();
    });

    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Cat, Character> cell = new TextFieldTableCell<>(
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
      Cat cat = event.getRowValue();
      cat.setGender(event.getNewValue());
      savePetList();
    });

    commentColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    commentColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setComment(event.getNewValue());
      savePetList();
    });

    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setPrice(event.getNewValue());
      savePetList();
    });

    breedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breedColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setBreed(event.getNewValue());
      savePetList();
    });

    breederNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breederNameColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setBreederName(event.getNewValue());
      savePetList();
    });
  }

  @FXML private void handleAddCat()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddCatView.fxml"));
      Parent root = loader.load();

      AddCatViewController controller = loader.getController();
      Scene scene = new Scene(root);
      controller.init(viewHandler, modelManager, scene);

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Cat");
      stage.setScene(scene);
      stage.showAndWait();

      Cat newCat = controller.getNewCat();
      if (newCat != null)
      {
        petList.add(newCat);
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

  @FXML private void handleRemoveCat()
  {
    Cat selectedCat = catTable.getSelectionModel().getSelectedItem();
    if (selectedCat != null)
    {
      petList.removePet(selectedCat);
      savePetList();
      updateTableData();
    }
    else
    {
      showAlert("No selection", "Please select a cat to remove.");
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
    observableCats = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Cat)
        {
          observableCats.add((Cat) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    catTable.setItems(observableCats);
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
