package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
  @FXML private ComboBox<Customer> customerBox;
  @FXML private Button updateButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateCustomerBox();

      Customer temp = customerBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        phoneNoField.setPromptText(temp.getPhone());
        emailField.setPromptText(temp.getEmail());
      }
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == updateButton)
    {
      String firstName = firstNameField.getText();
      String lastName = lastNameField.getText();
      String phone = phoneNoField.getText();
      String email = emailField.getText();

      if (phone.equals(""))
      {
        phone = "?";
      }

      if (email.equals(""))
      {
        email = "?";
      }

      modelManager.manageCustomers(firstName, lastName, phone, email);
      updateCustomerBox();
      phoneNoField.setText("");
      emailField.setText("");
    }
    else if (e.getSource() == customerBox)
    {
      Customer temp = customerBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        phoneNoField.setPromptText(temp.getPhone());
        emailField.setPromptText(temp.getEmail());
      }
    }
    else if (e.getSource() == addButton)
    {
      String firstName = firstNameField.getText().trim();
      String lastName = lastNameField.getText().trim();
      String phone = phoneNoField.getText().trim();
      String email = emailField.getText().trim();
      if (firstName.isEmpty() || lastName.isEmpty())
      {
        showAlert("First Name and Last Name are required.");
        return;
      }
      phone = phone.isEmpty() ? "?" : phone;
      email = email.isEmpty() ? "?" : email;
      Customer newCustomer = new Customer(firstName, lastName, phone, email);
      CustomerList customers = modelManager.getAllCustomers();
      customers.add(newCustomer);
      modelManager.saveCustomers(customers);
      updateCustomerBox();
      clearFields();
    }
    else if (e.getSource() == removeButton)
    {
      Customer selectedCustomer = customerBox.getSelectionModel()
          .getSelectedItem();

      if (selectedCustomer == null)
      {
        showAlert("Please select a customer to remove.");
        return;
      }
      CustomerList customers = modelManager.getAllCustomers();
      customers.remove(selectedCustomer);
      modelManager.saveCustomers(customers);
      updateCustomerBox();
      clearFields();
    }
    else if (e.getSource() == customerBox)
    {
      Customer temp = customerBox.getSelectionModel().getSelectedItem();

      if (temp != null)
      {
        firstNameField.setText(temp.getFirstName());
        lastNameField.setText(temp.getLastName());
        phoneNoField.setPromptText(temp.getPhone());
        emailField.setPromptText(temp.getEmail());
      }
    }
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

  private void updateCustomerBox()
  {
    int currentIndex = customerBox.getSelectionModel().getSelectedIndex();

    customerBox.getItems().clear();

    CustomerList students = modelManager.getAllCustomers();
    for (int i = 0; i < students.size(); i++)
    {
      customerBox.getItems().add(students.get(i));
    }

    if (currentIndex == -1 && customerBox.getItems().size() > 0)
    {
      customerBox.getSelectionModel().select(0);
    }
    else
    {
      customerBox.getSelectionModel().select(currentIndex);
    }
  }
}
