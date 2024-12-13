package view.purchases;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.ModelManager;
import model.Pets.PetList;
import view.ViewHandler;

import java.util.ArrayList;

public class NewPurchasesViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private DatePicker purchaseDate;
  @FXML private TextField timeField;
  @FXML private TextField emailOrPhoneField;
  @FXML private TextField customerNameField;
  @FXML private TextField priceField;
  @FXML private ChoiceBox<String> petTypes;
  @FXML private ChoiceBox<String> petNames;
  @FXML private ChoiceBox<String> discount;
  @FXML private Button addButton;

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    initializePetTypes();
    petTypes.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          updatePetNames(newValue);
        });
    reset();
  }

  private void initializePetTypes()
  {
    petTypes.getItems().clear();

    petTypes.getItems()
        .addAll("Dog", "Cat", "Bird", "Fish", "Rodent", "Various");
  }

  private void updatePetNames(String petType)
  {
    PetList petList = new PetList();
    petNames.getItems().clear();
    if (petType == null)
      return;

    ArrayList<String> names = new ArrayList<>();
    switch (petType)
    {
      case "Dog":
         names = modelManager.getAllDogs(petList);
        break;
      case "Cat":
        names = modelManager.getAllCats();
        break;
      case "Bird":
        names = modelManager.getAllBirds();
        break;
      case "Fish":
        names = modelManager.getAllFish();
        break;
      case "Rodent":
        names = modelManager.getAllRodents();
        break;
      case "Various":
        names = modelManager.getAllVarious();
        break;
    }
    petNames.getItems().addAll(names);
  }

  public void reset()
  {
    purchaseDate.setValue(null);
    timeField.clear();
    emailOrPhoneField.clear();
    customerNameField.clear();
    priceField.clear();
    petTypes.getSelectionModel().clearSelection();
    petNames.getItems().clear();
  }

}
