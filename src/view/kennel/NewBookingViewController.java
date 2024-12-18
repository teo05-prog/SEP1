package view.kennel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Pets.*;
import utils.KennelXML;
import utils.MyFileHandler;
import view.ViewHandler;

import java.io.IOException;
import java.util.Optional;

public class NewBookingViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;
  private Customer selectedCustomer;

  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private TextField phoneField;
  @FXML private TextField petNameField;
  @FXML private TextArea petInfoArea;
  @FXML private ChoiceBox<String> petTypes;
  @FXML private Button addButton;

  @FXML private void initialize()
  {
    setupListeners();
  }

  private void setupListeners()
  {
    phoneField.textProperty().addListener((observable, oldValue, newValue) -> {
      if (modelManager != null && !newValue.equals(oldValue))
      {
        lookupCustomer(newValue);
      }
    });
  }

  private void lookupCustomer(String contactInfo)
  {
    if (contactInfo == null || contactInfo.trim().isEmpty())
    {
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
        showSuccessAlert("Customer found: " + customer.getFirstName() + " "
            + customer.getLastName());
        return;
      }
    }
    selectedCustomer = null;
    if (contactInfo.trim().length() >= 8)
    {
      showCustomerNotFoundAlert();
    }
  }

  private void openAddCustomerDialog()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("/view/kennel/AddKennelCustomer.fxml"));
      Parent root = loader.load();

      Stage stage = new Stage();
      stage.setTitle("Add New Customer");
      stage.setScene(new Scene(root));

      AddKennelCustomerController controller = loader.getController();

      stage.showAndWait();

      Customer newCustomer = controller.getNewCustomer();
      if (newCustomer != null)
      {
        CustomerList customers = modelManager.getAllCustomers();
        customers.add(newCustomer);
        modelManager.saveCustomers(customers);

        selectedCustomer = newCustomer;
        phoneField.setText(newCustomer.getPhone());
        showSuccessAlert(
            "Customer added successfully: " + newCustomer.getFirstName() + " "
                + newCustomer.getLastName());
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

  public void reset()
  {
    startDate.setValue(null);
    endDate.setValue(null);
    phoneField.clear();
    petNameField.clear();
    petInfoArea.clear();
    petTypes.getSelectionModel().clearSelection();
    selectedCustomer = null;

    if (petTypes.getItems().isEmpty())
    {
      petTypes.getItems().addAll("Dog", "Cat", "Bird");
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == addButton)
    {
      if (!validateInputs())
      {
        return;
      }
      Booking newBooking = createBooking();
      if (newBooking != null)
      {
        saveBooking(newBooking);
        reset();
      }
    }
  }

  private boolean validateInputs()
  {
    if (startDate.getValue() == null || endDate.getValue() == null)
    {
      showAlert("Please select both start and end dates.");
      return false;
    }
    if (endDate.getValue().isBefore(startDate.getValue()))
    {
      showAlert("End date must be after start date.");
      return false;
    }
    if (petTypes.getSelectionModel().isEmpty())
    {
      showAlert("Please select a pet type.");
      return false;
    }
    if (petNameField.getText().trim().isEmpty())
    {
      showAlert("Please enter pet name.");
      return false;
    }
    if (phoneField.getText().trim().isEmpty())
    {
      showAlert("Please enter customer email or phone number.");
      return false;
    }
    if (selectedCustomer == null)
    {
      showAlert("Please select a valid customer before creating a booking.");
      return false;
    }

    return true;
  }

  private Booking createBooking()
  {
    MyDate bookingStartDate = new MyDate(startDate.getValue().getDayOfMonth(),
        startDate.getValue().getMonthValue(), startDate.getValue().getYear());
    MyDate bookingEndDate = new MyDate(endDate.getValue().getDayOfMonth(),
        endDate.getValue().getMonthValue(), endDate.getValue().getYear());

    String petName = petNameField.getText().trim();
    String petComment = petInfoArea.getText().trim();
    String petType = petTypes.getValue();

    if (petType == null)
    {
      showAlert("Please select a pet type.");
      return null;
    }

    Pet pet = createSpecificPet(petType, petName, petComment);
    if (pet == null)
    {
      showAlert("Unsupported pet type.");
      return null;
    }

    Booking newBooking = new Booking(selectedCustomer, pet, bookingStartDate,
        bookingEndDate);
    newBooking.setStartDate(bookingStartDate);
    newBooking.setEndDate(bookingEndDate);
    newBooking.setCustomer(selectedCustomer);
    newBooking.setPetInfo(pet);

    return newBooking;
  }

  private void saveBooking(Booking booking)
  {
    if (booking == null)
    {
      showAlert("Cannot save null booking");
      return;
    }

    try
    {
      KennelList bookings = modelManager.getAllBookings();
      if (bookings == null)
      {
        bookings = new KennelList();
      }
      bookings.add(booking);
      MyFileHandler.writeToBinaryFile("bookings.bin", bookings);
      showSuccessAlert("Booking added successfully!");

      new KennelXML();
    }
    catch (Exception e)
    {
      showAlert("Error saving booking: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private boolean showCustomerNotFoundAlert()
  {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Customer Not Found");
    alert.setHeaderText("Customer Does Not Exist");
    alert.setContentText(
        "The customer with this email or phone number does not exist in the system. "
            + "Would you like to add a new customer?");
    ButtonType addCustomerButton = new ButtonType("Add Customer");
    ButtonType cancelButton = new ButtonType("Cancel",
        ButtonType.CANCEL.getButtonData());

    alert.getButtonTypes().setAll(addCustomerButton, ButtonType.CANCEL);
    Optional<ButtonType> result = alert.showAndWait();

    if (result.isPresent() && result.get() == addCustomerButton)
    {
      openAddCustomerDialog();
      return true;
    }
    return false;
  }

  private Pet createSpecificPet(String petType, String name, String comment)
  {
    int age = 3;
    String colour = "Brown";
    char gender = 'M';
    int price = 50;
    String tempStr = "Just a string";

    return switch (petType)
    {
      case "Dog" ->
          new Dog(name, age, colour, gender, comment, price, tempStr, tempStr);
      case "Cat" ->
          new Cat(name, age, colour, gender, comment, price, tempStr, tempStr);
      case "Bird" ->
          new Bird(name, age, colour, gender, comment, price, tempStr, tempStr);
      default -> null;
    };
  }

  private void showAlert(String message)
  {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Input Error");
    alert.setHeaderText(null);
    alert.setContentText(message);
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

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    reset();
  }
}