package view.customers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.ModelManager;
import model.CustomerList;
import view.ViewHandler;

public class CustomersViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Customer> allCustomersTable;
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Customer, String> phoneColumn;
  @FXML private TableColumn<Customer, String> emailColumn;

  private TableView.TableViewSelectionModel<Customer> defaultSelectionModel;

  @FXML private void initialize()
  {
    setupTableColumns();
    defaultSelectionModel = allCustomersTable.getSelectionModel();
  }

  private void setupTableColumns()
  {
    firstNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    updateCustomerArea();
  }

  public void changeSelectableState(boolean selectable)
  {
    if (selectable)
    {
      allCustomersTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allCustomersTable.getSelectionModel().clearSelection();
      allCustomersTable.setSelectionModel(null);
    }
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updateCustomerArea();
    }
  }

  private void updateCustomerArea()
  {
    if (modelManager != null)
    {
      allCustomersTable.getItems().clear();
      CustomerList customers = modelManager.getAllCustomers();
      for (int i = 0; i < customers.size(); i++)
      {
        allCustomersTable.getItems().add(customers.get(i));
      }
    }
  }

  public Customer getSelectedCustomer()
  {
    return allCustomersTable.getSelectionModel().getSelectedItem();
  }

  public Scene getScene()
  {
    return scene;
  }
}