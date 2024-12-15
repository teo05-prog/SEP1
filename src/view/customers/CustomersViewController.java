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

/**
 * Controller for displaying all customers in a table view.
 * Provides functionality for viewing and selecting customers.
 */
public class CustomersViewController {
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML
  private TableView<Customer> allCustomersTable;

  @FXML
  private TableColumn<Customer, String> firstNameColumn;

  @FXML
  private TableColumn<Customer, String> lastNameColumn;

  @FXML
  private TableColumn<Customer, String> phoneColumn;

  @FXML
  private TableColumn<Customer, String> emailColumn;

  private TableView.TableViewSelectionModel<Customer> defaultSelectionModel;

  /**
   * Initializes the controller. This method is automatically called by JavaFX
   * after the FXML file has been loaded.
   */
  @FXML
  private void initialize() {
    setupTableColumns();
    defaultSelectionModel = allCustomersTable.getSelectionModel();
  }

  /**
   * Sets up the table columns with their respective property value factories.
   */
  private void setupTableColumns() {
    firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
  }

  /**
   * Initializes the controller with its dependencies.
   * @param viewHandler The view handler managing the application's views
   * @param modelManager The model manager handling the application's data
   * @param scene The scene containing this view
   */
  public void init(ViewHandler viewHandler, ModelManager modelManager, Scene scene) {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
    updateCustomerArea(); // Only update after modelManager is set
  }

  /**
   * Changes whether customers in the table can be selected.
   * @param selectable true to enable selection, false to disable
   */
  public void changeSelectableState(boolean selectable) {
    if (selectable) {
      allCustomersTable.setSelectionModel(defaultSelectionModel);
    } else {
      allCustomersTable.getSelectionModel().clearSelection();
      allCustomersTable.setSelectionModel(null);
    }
  }

  /**
   * Resets the view by refreshing the customer data.
   */
  public void reset() {
    if (modelManager != null) {  // Keep the null check
      updateCustomerArea();
    }
  }

  /**
   * Updates the table with the current list of customers.
   */
  private void updateCustomerArea() {
    if (modelManager != null) {  // Add null check here
      allCustomersTable.getItems().clear();
      CustomerList customers = modelManager.getAllCustomers();
      for (int i = 0; i < customers.size(); i++) {
        allCustomersTable.getItems().add(customers.get(i));
      }
    }
  }

  /**
   * Gets the currently selected customer from the table.
   * @return The selected Customer object, or null if none is selected
   */
  public Customer getSelectedCustomer() {
    return allCustomersTable.getSelectionModel().getSelectedItem();
  }

  /**
   * Gets the scene associated with this controller.
   * @return The JavaFX Scene object
   */
  public Scene getScene() {
    return scene;
  }
}