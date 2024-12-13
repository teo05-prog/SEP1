package view.pets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ModelManager;
import model.Pets.PetList;
import model.Pets.Rodent;
import view.ViewHandler;

import java.awt.*;
import java.awt.event.ActionEvent;

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
  private ObservableList<Rodent> observableRodents;
  @FXML private Button addButton;
  @FXML private Button removeButton;

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
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setColour(event.getNewValue());
    });
    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Rodent, Character> cell = new TextFieldTableCell<>(
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
      Rodent rodent = event.getRowValue();
      rodent.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setPrice(event.getNewValue());
    });
    doesItBiteColumn.setCellFactory(
        CheckBoxTableCell.forTableColumn(doesItBiteColumn));
    doesItBiteColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setDoesItBite(event.getNewValue());
    });
    specieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    specieColumn.setOnEditCommit(event -> {
      Rodent rodent = event.getRowValue();
      rodent.setSpecie(event.getNewValue());
    });
    updateTableDate();
  }

  private void updateTableDate()
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

  @FXML public void handleActions(ActionEvent e)
  {
    if (e.getSource() == addButton)
    {
      handleAddRodent();
    }
    else if (e.getSource() == removeButton)
    {
      handleRemoveRodent();
    }
  }

  private void handleAddRodent()
  {
    Rodent newRodent = new Rodent("", 1, "", 'M', "", 100, true, "");
    observableRodents.add(newRodent);
  }

  private void handleRemoveRodent()
  {
    Rodent selectedRodent = rodentTable.getSelectionModel().getSelectedItem();
    if (selectedRodent != null)
    {
      observableRodents.remove(selectedRodent);
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
      updateRodents();
    }
  }

  private void updateRodents()
  {
    rodentTable.getItems().clear();
    PetList rodents = modelManager.getAllRodents(petList);

    for (int i = 0; i < rodents.size(); i++)
    {
      rodentTable.getItems().add(rodents.getRodent(i));
    }
  }
}
