package view.purchases;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Pets.Pet;
import model.Pets.PetList;
import view.ViewHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;

public class NewPurchasesViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;
  private Customer selectedCustomer;

  @FXML private DatePicker purchaseDate;
  @FXML private TextField timeField;
  @FXML private TextField phoneField;
  @FXML private TextField customerNameField;
  @FXML private TextField priceField;
  @FXML private ChoiceBox<String> petTypes;
  @FXML private ChoiceBox<String> petNames;
  @FXML private ChoiceBox<String> discount;
  @FXML private Button addButton;

  private Pet selectedPet;

  @FXML public void initialize()
  {
    purchaseDate.setEditable(false);
    timeField.setEditable(false);
    customerNameField.setEditable(false);
    priceField.setEditable(false);
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    setCurrentDateTime();
    initializePetTypes();
    initializeDiscounts();
    setupListeners();
    reset();
  }

  private void setupListeners()
  {
    phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (modelManager != null && !newValue.equals(oldValue))
      {
        lookupCustomer(newValue);
      }
    });

    petTypes.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue != null)
          {
            updatePetNames(newValue);
          }
        });

    petNames.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue != null)
          {
            updateSelectedPet(newValue);
          }
        });

    discount.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldValue, newValue) -> {
          if (newValue != null)
          {
            updatePrice();
          }
        });
  }

  private void setCurrentDateTime()
  {
    purchaseDate.setValue(LocalDate.now());
    LocalTime now = LocalTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    timeField.setText(now.format(formatter));
  }

  private void initializePetTypes()
  {
    petTypes.getItems().clear();
    petTypes.getItems()
        .addAll("Dog", "Cat", "Bird", "Fish", "Rodent", "Various");
  }

  private void initializeDiscounts()
  {
    discount.getItems().clear();
    discount.getItems()
        .addAll("No discount", "5%", "10%", "15%", "20%", "25%", "30%", "40%",
            "50%");
  }

  private void lookupCustomer(String contactInfo)
  {
    if (contactInfo == null || contactInfo.trim().isEmpty())
    {
      customerNameField.clear();
      selectedCustomer = null;
      return;
    }

    CustomerList allCustomers = modelManager.getAllCustomers();
    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);
      if (contactInfo.equals(customer.getEmail()) || contactInfo.equals(
          customer.getPhone()))
      {
        selectedCustomer = customer;
        customerNameField.setText(
            customer.getFirstName() + " " + customer.getLastName());
        return;
      }
    }
    selectedCustomer = null;
    showCustomerNotFoundAlert();
  }

  private void showCustomerNotFoundAlert()
  {
    customerNameField.clear();

    if (phoneField.getText().trim().length() >= 8)
    {
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Customer Not Found");
      alert.setHeaderText("Customer Does Not Exist");
      alert.setContentText(
          "The customer with this email or phone number does not exist in the system. "
              + "Please add the customer first before making a purchase.");
      ButtonType addCustomerButton = new ButtonType("Add Customer");
      alert.getButtonTypes().setAll(addCustomerButton, ButtonType.CANCEL);
      Optional<ButtonType> result = alert.showAndWait();

      if (result.isPresent() && result.get() == addCustomerButton)
      {
        openAddCustomerDialog();
      }
    }
  }

  private void openAddCustomerDialog()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/purchases/AddPurchaseCustomer.fxml"));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.setTitle("Add New Customer");
      stage.setScene(new Scene(root));

      AddPurchaseCustomerController controller = loader.getController();

      stage.showAndWait();

      Customer newCustomer = controller.getNewCustomer();
      if (newCustomer != null)
      {
        CustomerList customers = modelManager.getAllCustomers();
        customers.add(newCustomer);
        modelManager.saveCustomers(customers);
        customerNameField.setText(
            newCustomer.getFirstName() + " " + newCustomer.getLastName());
        selectedCustomer = newCustomer;
      }
    }
    catch (IOException e)
    {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText(
          "Error opening add customer window: " + e.getMessage());
      alert.showAndWait();
    }
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

  private double getDiscountPercentage(String discountValue)
  {
    return switch (discountValue)
    {
      case "5%" -> 0.05;
      case "10%" -> 0.10;
      case "15%" -> 0.15;
      case "20%" -> 0.20;
      case "25%" -> 0.25;
      case "30%" -> 0.30;
      case "40%" -> 0.40;
      case "50%" -> 0.50;
      default -> 0.0;
    };
  }

  private void updatePrice()
  {
    if (selectedPet == null || discount.getValue() == null)
    {
      priceField.clear();
      return;
    }

    double originalPrice = selectedPet.getPrice();
    double discountPercentage = getDiscountPercentage(discount.getValue());
    double finalPrice = originalPrice * (1 - discountPercentage);

    priceField.setText(String.format("%.2f", finalPrice));
  }

  public void reset()
  {
    setCurrentDateTime();
    phoneField.clear();
    customerNameField.clear();
    priceField.clear();
    petTypes.getSelectionModel().clearSelection();
    petNames.getItems().clear();
    discount.getSelectionModel().clearSelection();
    selectedPet = null;
    selectedCustomer = null;
  }

  @FXML public void handleActions(ActionEvent e)
  {
    if (e.getSource() == addButton)
    {
      if (!validateFields())
      {
        return;
      }

      try
      {
        LocalDate date = purchaseDate.getValue();
        LocalTime time = LocalTime.parse(timeField.getText(),
            DateTimeFormatter.ofPattern("HH:mm"));

        int discountPercent = (int) (getDiscountPercentage(discount.getValue())
            * 100);

        Purchase purchase = new Purchase(selectedCustomer, selectedPet,
            new MyDate(date.getDayOfMonth(), date.getMonthValue(),
                date.getYear()), time, discountPercent);

        PurchaseList purchases = modelManager.getAllPurchases();
        purchases.add(purchase);

        PetList pets = modelManager.getAllPets();
        pets.removePet(selectedPet);

        modelManager.savePurchases(purchases);
        modelManager.savePets(pets);

        showSuccessAlert(
            "Purchase successfully added and pet removed from inventory!");
        reset();
        initializePetTypes();
      }
      catch (Exception ex)
      {
        showErrorAlert("Error creating purchase: " + ex.getMessage());
      }
    }
  }

  private boolean validateFields()
  {
    if (selectedCustomer == null)
    {
      showAlert("Customer Required",
          "Please enter a valid customer phone number.");
      return false;
    }

    if (selectedPet == null || petTypes.getValue() == null
        || petNames.getValue() == null)
    {
      showAlert("Pet Selection Required",
          "Please select both pet type and name.");
      return false;
    }

    if (discount.getValue() == null)
    {
      showAlert("Discount Required", "Please select a discount value.");
      return false;
    }

    return true;
  }

  private void showAlert(String title, String content)
  {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(content);
    alert.showAndWait();
  }

  private void showSuccessAlert(String message)
  {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Success");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void showErrorAlert(String message)
  {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }
}