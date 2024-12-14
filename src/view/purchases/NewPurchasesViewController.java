package view.purchases;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Customer;
import model.CustomerList;
import model.ModelManager;
import model.Pets.Pet;
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

  private Pet selectedPet;

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    initializePetTypes();
    initializeDiscounts();

    emailOrPhoneField.textProperty()
        .addListener((observable, oldValue, newValue) -> {
          lookupCustomer(newValue);
        });

    petTypes.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          updatePetNames(newValue);
        });

    petNames.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          updateSelectedPet(newValue);
        });

    discount.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          updatePrice();
        });
    reset();
  }

  private void updateSelectedPet(String petName)
  {
    if (petName == null || petTypes.getValue() == null)
      return;

    PetList allPets = modelManager.getAllPets();
    PetList filteredPets = switch (petTypes.getValue())
    {
      case "Dog" -> modelManager.getAllDogs(allPets);
      case "Cat" -> modelManager.getAllCats(allPets);
      case "Bird" -> modelManager.getAllBirds(allPets);
      case "Fish" -> modelManager.getAllFish(allPets);
      case "Rodent" -> modelManager.getAllRodents(allPets);
      case "Various" -> modelManager.getAllVarious(allPets);
      default -> new PetList();
    };

    for (int i = 0; i < filteredPets.size(); i++)
    {
      try
      {
        Pet pet = filteredPets.getPets(i);
        if (pet.getName().equals(petName))
        {
          selectedPet = pet;
          updatePrice();
          return;
        }
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }
  }

  private void updatePrice()
  {
    if (selectedPet == null || discount.getValue() == null)
    {
      priceField.clear();
      return;
    }
    int originalPrice = selectedPet.getPrice();

    double discountPercentage = getDiscountPercentage(discount.getValue());
    double finalPrice = originalPrice * (1 - discountPercentage);

    priceField.setText(String.format("%.2f", finalPrice));
  }

  private double getDiscountPercentage(String discountValue)
  {
    switch (discountValue)
    {
      case "No discount":
        return 0.0;
      case "5%":
        return 0.05;
      case "10%":
        return 0.10;
      case "15%":
        return 0.15;
      case "20%":
        return 0.20;
      case "25%":
        return 0.25;
      case "30%":
        return 0.30;
      case "40%":
        return 0.40;
      case "50%":
        return 0.50;
      default:
        return 0.0;
    }
  }

  private void lookupCustomer(String contactInfo)
  {
    if (contactInfo == null || contactInfo.trim().isEmpty())
    {
      return;
    }

    CustomerList allCustomers = modelManager.getAllCustomers();
    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);

      if (contactInfo.equals(customer.getEmail()) || contactInfo.equals(
          customer.getPhone()))
      {
        customerNameField.setText(
            customer.getFirstName() + " " + customer.getLastName());
        return;
      }
    }
    showCustomerNotFoundAlert();
  }

  private void showCustomerNotFoundAlert()
  {
    customerNameField.clear();
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Customer Not Found");
    alert.setHeaderText("Customer Does Not Exist");
    alert.setContentText(
        "The customer with this email or phone number does not exist in the system. "
            + "Please add the customer first before making a purchase.");
    alert.showAndWait();
  }

  private void initializePetTypes()
  {
    petTypes.getItems().clear();

    petTypes.getItems()
        .addAll("Dog", "Cat", "Bird", "Fish", "Rodent", "Various");
  }

  private void updatePetNames(String petType)
  {
    petNames.getItems().clear();
    if (petType == null)
      return;

    PetList allPets = modelManager.getAllPets();
    ArrayList<String> names = new ArrayList<>();

    PetList filteredPets = switch (petType)
    {
      case "Dog" -> modelManager.getAllDogs(allPets);
      case "Cat" -> modelManager.getAllCats(allPets);
      case "Bird" -> modelManager.getAllBirds(allPets);
      case "Fish" -> modelManager.getAllFish(allPets);
      case "Rodent" -> modelManager.getAllRodents(allPets);
      case "Various" -> modelManager.getAllVarious(allPets);
      default -> new PetList();
    };

    for (int i = 0; i < filteredPets.size(); i++)
    {
      try
      {
        Pet pet = filteredPets.getPets(i);
        names.add(pet.getName());
      }
      catch (Exception e)
      {
        System.err.println("Error accessing pet: " + e.getMessage());
      }
    }

    petNames.getItems().addAll(names);
  }

  private void initializeDiscounts()
  {
    discount.getItems().clear();

    discount.getItems()
        .addAll("No discount", "5%", "10%", "15%", "20%", "25%", "30%", "40%",
            "50%");
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
    discount.getSelectionModel().clearSelection();
    selectedPet = null;
  }

  public void handleActions()
  {

  }
}
