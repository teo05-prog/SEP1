package view.kennel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;
import model.Pets.*;
import utils.MyFileHandler;
import view.ViewHandler;

import java.util.Optional;

public class NewBookingViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private TextField emailAndPhoneField;
  @FXML private TextField petNameField;
  @FXML private TextArea petInfoArea;
  @FXML private ChoiceBox<String> petTypes;
  @FXML private Button addButton;

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    reset();
  }

  public void reset()
  {
    startDate.setValue(null);
    endDate.setValue(null);
    emailAndPhoneField.clear();
    petNameField.clear();
    petInfoArea.clear();
    petTypes.getSelectionModel().clearSelection();

    if (petTypes.getItems().isEmpty())
    {
      petTypes.getItems()
          .addAll("Dog", "Cat", "Bird", "Fish", "Rodent", "Various");
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
      saveBooking(newBooking);
      reset();
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
    if (emailAndPhoneField.getText().trim().isEmpty())
    {
      showAlert("Please enter customer email or phone number.");
      return false;
    }

    return true;
  }

  private Booking createBooking()
  {
    MyDate bookingStartDate = new MyDate(startDate.getValue().getYear(),
        startDate.getValue().getMonthValue(),
        startDate.getValue().getDayOfMonth());
    MyDate bookingEndDate = new MyDate(endDate.getValue().getYear(),
        endDate.getValue().getMonthValue(), endDate.getValue().getDayOfMonth());

    String contactDetail = emailAndPhoneField.getText().trim();
    if (contactDetail.isEmpty())
    {
      showAlert("Please provide either a phone number or an email address.");
      return null;
    }

    Customer existingCustomer = findCustomerByContact(contactDetail);

    Customer customer;
    if (existingCustomer == null)
    {
      boolean addCustomer = showCustomerNotFoundAlert();

      if (addCustomer)
      {
        viewHandler.openView("AddKennelCustomer");
        return null;
      }
      else
      {
        return null;
      }
    }
    else
    {
      customer = existingCustomer;
    }

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

    Booking newBooking = new Booking(customer, pet, bookingStartDate,
        bookingEndDate);
    newBooking.setStartDate(bookingStartDate);
    newBooking.setEndDate(bookingEndDate);
    newBooking.setCustomer(customer);
    newBooking.setPetInfo(pet);

    return newBooking;
  }

  private Customer findCustomerByContact(String contactInfo)
  {
    CustomerList allCustomers = modelManager.getAllCustomers();
    for (int i = 0; i < allCustomers.size(); i++)
    {
      Customer customer = allCustomers.get(i);
      if (contactInfo.equals(customer.getEmail()) || contactInfo.equals(
          customer.getPhone()))
      {
        return customer;
      }
    }
    return null;
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

    return result.isPresent() && result.get() == addCustomerButton;
  }

  private void saveBooking(Booking booking)
  {
    try
    {
      KennelList bookings = modelManager.getAllBookings();
      bookings.add(booking);
      MyFileHandler.writeToBinaryFile("bookings.bin", bookings);

      showSuccessAlert("Booking added successfully!");
    }
    catch (Exception e)
    {
      showAlert("Error saving booking: " + e.getMessage());
    }
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

  private Pet createSpecificPet(String petType, String name, String comment)
  {
    int age = 3;
    String colour = "Brown";
    char gender = 'M';
    int price = 50;
    String tempStr = "Just a string";
    boolean tempBoo = true;

    switch (petType)
    {
      case "Dog":
        return new Dog(name, age, colour, gender, comment, price, tempStr,
            tempStr);
      case "Cat":
        return new Cat(name, age, colour, gender, comment, price, tempStr,
            tempStr);
      case "Bird":
        return new Bird(name, age, colour, gender, comment, price, tempStr,
            tempStr);
      case "Fish":
        return new Fish(name, age, colour, gender, comment, price, tempStr,
            tempBoo, tempStr);
      case "Rodent":
        return new Rodent(name, age, colour, gender, comment, price, tempBoo,
            tempStr);
      case "Various":
        return new Various(name, age, colour, gender, comment, price, tempStr);
      default:
        return null;
    }
  }
}
