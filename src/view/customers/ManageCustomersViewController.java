package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import model.Customer;
import model.ModelManager;

public class ManageCustomersViewController
{
  private ModelManager modelManager;

  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneNoField;
  @FXML private TextField emailField;
  @FXML private ComboBox<Customer> customerBox;
  @FXML private Button updateButton;
  @FXML private Button addButton;
  @FXML private Button removeButton;

  public void init(ModelManager modelManager)
  {
    this.modelManager = modelManager;
    reset();
  }

  public void reset()
  {
    if(modelManager!=null)
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

      if (firstName.equals(""))
      {
        firstName = "?";
      }

      if (lastName.equals(""))
      {
        lastName = "?";
      }

      if (phone.equals(""))
      {
        phone = "?";
      }

      if (email.equals(""))
      {
        email = "?";
      }

      modelManager.changeCountry(firstName, lastName, phone, email);
      updateStudentBox();
      countryField.setText("");
    }
  }
}
