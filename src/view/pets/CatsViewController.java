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
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setColour(event.getNewValue());
    });
    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Cat, Character> cell = new TextFieldTableCell<>(
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
      Cat cat = event.getRowValue();
      cat.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setPrice(event.getNewValue());
    });
    breedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breedColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setBreed(event.getNewValue());
    });
    breederNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breederNameColumn.setOnEditCommit(event -> {
      Cat cat = event.getRowValue();
      cat.setBreederName(event.getNewValue());
    });

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

  @FXML private void handleAddCat()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/pets/AddCatView.fxml"));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.initModality(Modality.APPLICATION_MODAL);
      stage.setTitle("Add a New Cat");
      stage.setScene(new Scene(root));
      stage.showAndWait();

      AddCatViewController controller = loader.getController();
      Cat newCat = controller.getNewCat();
      if (newCat != null)
      {
        observableCats.add(newCat);
      }
    }
    catch (Exception e)
    {
      System.out.println("Error opening window: " + e.getMessage());
    }

  }

  @FXML private void handleRemoveCat()
  {
    Cat selectedCat = catTable.getSelectionModel().getSelectedItem();
    if (selectedCat != null)
    {
      observableCats.remove(selectedCat);
    }
    else
    {
      showAlert("No selection", "Please select a cat to remove.");
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
      updateCat();
    }
  }

  private void updateCat()
  {
    catTable.getItems().clear();
    PetList cats = modelManager.getAllCats(petList);

    for (int i = 0; i < cats.size(); i++)
    {
      catTable.getItems().add(cats.getCat(i));
    }
  }
}
