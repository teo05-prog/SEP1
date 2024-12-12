package view.pets;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.CharacterStringConverter;
import javafx.util.converter.IntegerStringConverter;
import model.ModelManager;
import model.Pets.Bird;
import model.Pets.PetList;
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
  private PetList petList;
  private ObservableList<Bird> observableBirds;

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
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

    nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    nameColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setName(event.getNewValue());
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setColour(event.getNewValue());
    });
    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Bird, Character> cell = new TextFieldTableCell<>(
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
      Bird bird = event.getRowValue();
      bird.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setPrice(event.getNewValue());
    });
    preferredFoodColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    preferredFoodColumn.setOnEditCommit(event -> {
      Bird bird = event.getRowValue();
      bird.setPreferredFood(event.getNewValue());
    });
    updateTableDate();
  }

  private void updateTableDate()
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
      updateBird();
    }
  }

  private void updateBird()
  {
    birdTable.getItems().clear();
    PetList birds = modelManager.getAllBirds(petList);

    for (int i = 0; i < birds.size(); i++)
    {
      birdTable.getItems().add(birds.getBird(i));
    }
  }
}
