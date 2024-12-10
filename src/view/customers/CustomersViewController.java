package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import model.ModelManager;
import model.CustomerList;
import view.ViewHandler;

public class CustomersViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;
  @FXML private Button getButton;
  @FXML private Button backButton;
  @FXML private TextArea allCustomersArea;

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
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
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

  private void updateCustomerArea()
  {
    CustomerList customers = modelManager.getAllCustomers();
    allCustomersArea.setText(customers.toString());
  }
}
