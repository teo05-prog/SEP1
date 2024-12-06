package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.ModelManager;
import model.CustomerList;

public class CustomersViewController
{
  private ModelManager modelManager;
  @FXML private Button getButton;
  @FXML private TextArea allCustomersArea;

  public void init(ModelManager modelManager)
  {
    this.modelManager = modelManager;
    reset();
  }

  public void reset()
  {
    if(modelManager!=null)
    {
      updateCustomerArea();
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == getButton)
    {
      updateCustomerArea();
    }
  }

  private void updateCustomerArea()
  {
    CustomerList customers = modelManager.getAllCustomers();
    allCustomersArea.setText(customers.toString());
  }

  public void changeEditableState(boolean bool)
  {
    allCustomersArea.setEditable(bool);
  }
}
