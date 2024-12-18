package view.kennel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;

public class AddKennelCustomerController
{
  @FXML private TextField firstNameField;
  @FXML private TextField lastNameField;
  @FXML private TextField phoneField;
  @FXML private TextField emailField;

  private Customer newCustomer;

  @FXML private void handleSave(ActionEvent event)
  {
    try
    {
      String firstName = firstNameField.getText().trim();
      String lastName = lastNameField.getText().trim();
      String phone = phoneField.getText().trim();
      String email = emailField.getText().trim();

      newCustomer = new Customer(firstName, lastName, phone, email);

      Stage stage = (Stage) firstNameField.getScene().getWindow();
      stage.close();
    }
    catch (Exception e)
    {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  @FXML private void handleCancel(ActionEvent event)
  {
    newCustomer = null;
    Stage stage = (Stage) firstNameField.getScene().getWindow();
    stage.close();
  }

  public Customer getNewCustomer()
  {
    return newCustomer;
  }
}

