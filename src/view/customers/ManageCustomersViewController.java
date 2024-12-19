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

public class ManageCustomersViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNoField;
  @FXML private TextField emailField;

  @FXML private TableView<Customer> customerTable;
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> phoneColumn;
  @FXML private TableColumn<Customer, String> emailColumn;

  @FXML private Button updateButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;

    setupTableColumns();
    setupTableSelectionListener();
  }

  private void setupTableColumns()
  {
    firstNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
  }

  private void setupTableSelectionListener()
  {
    customerTable.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldSelection, newSelection) -> {
          if (newSelection != null)
          {
            firstNameField.setText(newSelection.getFirstName());
            lastNameField.setText(newSelection.getLastName());
            phoneNoField.setText(newSelection.getPhone());
            emailField.setText(newSelection.getEmail());
          }
          else
          {
            clearFields();
          }
        });
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateCustomerTable();
      clearFields();
    }
  }

  @FXML public void handleActions(ActionEvent e)
  {
    if (e.getSource() == updateButton)
    {
      handleUpdateAction();
    }
    else if (e.getSource() == addButton)
    {
      handleAddAction();
    }
    else if (e.getSource() == removeButton)
    {
      handleRemoveAction();
    }
  }

  private void handleUpdateAction()
  {
    Customer selectedCustomer = customerTable.getSelectionModel()
        .getSelectedItem();
    if (selectedCustomer == null)
    {
      showAlert("Please select a customer to update.");
      return;
    }

    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String phone = phoneNoField.getText().trim();
    String email = emailField.getText().trim();

    if (!validateInput(firstName, lastName))
    {
      return;
    }
    CustomerList customers = modelManager.getAllCustomers();
    for (int i = 0; i < customers.size(); i++)
    {
      Customer customer = customers.get(i);
      if (customer.equals(selectedCustomer))
      {
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone.isEmpty() ? "?" : phone);
        customer.setEmail(email.isEmpty() ? "?" : email);
        break;
      }
    }
    modelManager.saveCustomers(customers);
    updateCustomerTable();
    clearFields();
  }

  private void handleAddAction()
  {
    String firstName = firstNameField.getText().trim();
    String lastName = lastNameField.getText().trim();
    String phone = phoneNoField.getText().trim();
    String email = emailField.getText().trim();

    if (!validateInput(firstName, lastName))
    {
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

  private void handleRemoveAction()
  {
    Customer selectedCustomer = customerTable.getSelectionModel()
        .getSelectedItem();
    if (selectedCustomer == null)
    {
      showAlert("Please select a customer to remove.");
      return;
    }

    CustomerList customers = modelManager.getAllCustomers();
    customers.remove(selectedCustomer);
    modelManager.saveCustomers(customers);
    updateCustomerTable();
    clearFields();
  }

  private boolean validateInput(String firstName, String lastName)
  {
    if (firstName.isEmpty() || lastName.isEmpty())
    {
      showAlert("First Name and Last Name are required.");
      return false;
    }
    return true;
  }

  private void updateCustomer(Customer customer, String firstName,
      String lastName, String phone, String email)
  {
    phone = phone.isEmpty() ? "?" : phone;
    email = email.isEmpty() ? "?" : email;

    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setPhone(phone);
    customer.setEmail(email);
  }

  private void showAlert(String message)
  {
    Alert alert = new Alert(Alert.AlertType.WARNING);
    alert.setTitle("Warning");
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.showAndWait();
  }

  private void clearFields()
  {
    firstNameField.clear();
    lastNameField.clear();
    phoneNoField.clear();
    emailField.clear();
  }

  private void updateCustomerTable()
  {
    // O(k) where k is current number of items in TableView
    customerTable.getItems().clear();
    // O(n) from previous getAllCustomers() analysis
    CustomerList customers = modelManager.getAllCustomers();
    // O(m) where m is number of customers in CustomerList
    for (int i = 0; i < customers.size(); i++)
    {
      // O(1) amortized time for ArrayList add operation
      customerTable.getItems().add(customers.get(i));
    }
    // Total time complexity: T(n,k,m) = k + n + m
    // where k = initial table items, n = file objects, m = customers
  }

  public Scene getScene()
  {
    return scene;
  }
}