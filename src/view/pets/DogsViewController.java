package view.pets;

import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ModelManager;
import model.Pets.Dog;
import javafx.scene.control.TableView;
import model.Pets.PetList;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.CharacterStringConverter;
import view.ViewHandler;

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
  private ObservableList<Dog> observableDogs;

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
    });
    ageColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    ageColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setAge(event.getNewValue());
    });
    colourColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    colourColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setColour(event.getNewValue());
    });
    genderColumn.setCellFactory(column -> {
      TextFieldTableCell<Dog, Character> cell = new TextFieldTableCell<>(
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
      Dog dog = event.getRowValue();
      dog.setComment(event.getNewValue());
    });
    priceColumn.setCellFactory(
        TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
    priceColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setPrice(event.getNewValue());
    });
    breedColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breedColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setBreed(event.getNewValue());
    });
    breederNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    breederNameColumn.setOnEditCommit(event -> {
      Dog dog = event.getRowValue();
      dog.setBreederName(event.getNewValue());
    });

    petList = new PetList();
    petList.addPet(
        new Dog("Buddy", 3, "Brown", 'M', "Friendly dog", 500, "Labrador",
            "John Doe"));
    petList.addPet(
        new Dog("Lucy", 2, "Black", 'F', "Energetic dog", 450, "Poodle",
            "Jane Smith"));

    updateTableDate();
  }

  private void updateTableDate()
  {
    observableDogs = FXCollections.observableArrayList();
    for (int i = 0; i < petList.getPetsCount(); i++)
    {
      try
      {
        if (petList.getPets(i) instanceof Dog)
        {
          observableDogs.add((Dog) petList.getPets(i));
        }
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
    dogTable.setItems(observableDogs);
  }

  @FXML private void addDog()
  {
    Dog newDog = new Dog("New Dog", 1, "White", 'M', "Newly added", 300,
        "Unknown", "Unknown");
    petList.addPet(newDog);
  }

  @FXML private void removeSelectedDog()
  {
    Dog selectedDog = dogTable.getSelectionModel().getSelectedItem();
    if (selectedDog != null)
    {
      petList.removePet(selectedDog);
      updateTableDate();
    }
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateDog();
    }
  }

  private void updateDog()
  {
    dogTable.getItems().clear();
    PetList dogs = modelManager.getAllDogs(petList);

    for (int i = 0; i < dogs.size(); i++)
    {
      dogTable.getItems().add(dogs.getDog(i));
    }
  }
}
