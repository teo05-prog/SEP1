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
  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
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
    else if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?", ButtonType.YES,
          ButtonType.NO);
      alert.setTitle("Exit");
      alert.setHeaderText(null);

      alert.showAndWait();

      if (alert.getResult() == ButtonType.YES)
      {
        System.exit(0);
      }
    }
    else if (e.getSource() == aboutMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setHeaderText(null);
      alert.setTitle("About");
      alert.setContentText(
          "This is just a little program that demonstrates some of the GUI features in Java");
      alert.showAndWait();
    }
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
