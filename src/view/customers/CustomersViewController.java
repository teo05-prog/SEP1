package view.customers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import model.Customer;
import model.ModelManager;
import model.CustomerList;
import view.ViewHandler;

public class CustomersViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private Button getButton;
  @FXML private Button backButton;
  @FXML private TableView<Customer> allCustomersTable;
  @FXML private TableView.TableViewSelectionModel<Customer> defaultSelectionModel;

  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      allCustomersTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allCustomersTable.getSelectionModel().clearSelection();
      allCustomersTable.setSelectionModel(null);
    }
  }

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
    allCustomersTable.getItems().clear();
    CustomerList customers = modelManager.getAllCustomers();

    for (int i = 0; i < customers.size(); i++)
    {
      allCustomersTable.getItems().add(customers.get(i));
    }
  }
}
