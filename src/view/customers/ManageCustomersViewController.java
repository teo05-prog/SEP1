package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.CustomerList;
import model.ModelManager;
import view.ViewHandler;

/**
 * Controller for managing customer information in the application.
 * Handles the display, addition, removal, and updating of customer records.
 */
public class ManageCustomersViewController {
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  // Form fields
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNoField;
  @FXML private TextField emailField;

  // Table components
  @FXML private TableView<Customer> customerTable;
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> phoneColumn;
  @FXML private TableColumn<Customer, String> emailColumn;

  // Buttons
  @FXML private Button updateButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;

  /**
   * Initializes the controller with necessary dependencies and sets up the TableView
   */
  public void init(ViewHandler viewHandler, ModelManager modelManager, Scene scene) {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

    setupTableColumns();
    setupTableSelectionListener();
  }

  /**
   * Sets up the table columns with their respective property value factories
   */
  private void setupTableColumns() {
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
  }

  /**
   * Sets up the listener for table row selection to update form fields
   */
  private void setupTableSelectionListener() {
    customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
      if (newSelection != null) {
        firstNameField.setText(newSelection.getFirstName());
        lastNameField.setText(newSelection.getLastName());
        phoneNoField.setText(newSelection.getPhone());
        emailField.setText(newSelection.getEmail());
      } else {
        clearFields();
      }
    });
  }

  /**
   * Resets the view to its initial state and refreshes the customer list
   */
  public void reset() {
    if (modelManager != null) {
      updateCustomerTable();
      clearFields();
    }
  }

  /**
   * Handles all button actions in the view
   */
  @FXML
  public void handleActions(ActionEvent e) {
    if (e.getSource() == updateButton) {
      handleUpdateAction();
    }
    else if (e.getSource() == addButton) {
      handleAddAction();
    }
    else if (e.getSource() == removeButton) {
      handleRemoveAction();
    }
  }

  /**
   * Handles the update button action
   */
  private void handleUpdateAction() {
    Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
    if (selectedCustomer == null) {
      showAlert("Please select a customer to update.");
      return;
    }

    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String phone = phoneNoField.getText().trim();
    String email = emailField.getText().trim();

    if (!validateInput(firstName, lastName)) {
      return;
    }

    // Get the current customer list
    CustomerList customers = modelManager.getAllCustomers();

    // Find and update the customer in the list
    for (int i = 0; i < customers.size(); i++) {
      Customer customer = customers.get(i);
      if (customer.equals(selectedCustomer)) {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone.isEmpty() ? "?" : phone);
        customer.setEmail(email.isEmpty() ? "?" : email);
        break;
      }
    }

    // Save the updated list
    modelManager.saveCustomers(customers);
    updateCustomerTable();
    clearFields();
  }

  /**
   * Handles the add button action
   */
  private void handleAddAction() {
    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String phone = phoneNoField.getText().trim();
    String email = emailField.getText().trim();

    if (!validateInput(firstName, lastName)) {
      return;
    }

    phone = phone.isEmpty() ? "?" : phone;
    email = email.isEmpty() ? "?" : email;

    Customer newCustomer = new Customer(firstName, lastName, phone, email);
    CustomerList customers = modelManager.getAllCustomers();
    customers.add(newCustomer);
    modelManager.saveCustomers(customers);
    updateCustomerTable();
    clearFields();
  }

  /**
   * Handles the remove button action
   */
  private void handleRemoveAction() {
    Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();
    if (selectedCustomer == null) {
      showAlert("Please select a customer to remove.");
      return;
    }

    CustomerList customers = modelManager.getAllCustomers();
    customers.remove(selectedCustomer);
    modelManager.saveCustomers(customers);
    updateCustomerTable();
    clearFields();
  }

  /**
   * Validates the input fields
   */
  private boolean validateInput(String firstName, String lastName) {
    if (firstName.isEmpty() || lastName.isEmpty()) {
      showAlert("First Name and Last Name are required.");
      return false;
    }
    return true;
  }

  /**
   * Updates an existing customer's information
   */
  private void updateCustomer(Customer customer, String firstName, String lastName, String phone, String email) {
    phone = phone.isEmpty() ? "?" : phone;
    email = email.isEmpty() ? "?" : email;

    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setPhone(phone);
    customer.setEmail(email);
  }

  /**
   * Shows an alert dialog with the specified message
   */
  private void showAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Clears all input fields
   */
  private void clearFields() {
    firstNameField.clear();
    lastNameField.clear();
    phoneNoField.clear();
    emailField.clear();
  }

  /**
   * Updates the customer table with the current data
   */
  private void updateCustomerTable() {
    customerTable.getItems().clear();
    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++) {
      customerTable.getItems().add(customers.get(i));
    }
  }

  /**
   * Returns the scene associated with this controller
   */
  public Scene getScene() {
    return scene;
  }
}