package view.customers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.ModelManager;
import view.ViewHandler;

public class MainCustomersViewController
{
  @FXML private CustomersViewController customersController;
  @FXML private ManageCustomersViewController manageCustomersController;

  @FXML private TabPane tabPane;
  @FXML private Tab customersTab;
  @FXML private Tab manageCustomersTab;
  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void initialize()
  {
//    ModelManager modelManager = new ModelManager(
//        "customers.bin",
//        "pets.bin",
//        "kennel.bin",
//        "purchases.bin"
//    );
    customersController.init(viewHandler, modelManager, scene);
    manageCustomersController.init(viewHandler, modelManager, scene);
  }

  public void init(ViewHandler viewHandler, Scene scene,
      ModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;
  }

  public void reset()
  {

  }

  public Scene getScene()
  {
    return scene;
  }

  public void tabChanged(Event event)
  {
    if (customersTab.isSelected()){
      if (customersController != null) {
        customersController.reset();
      }
    }
    else if (manageCustomersTab.isSelected()){
      if (manageCustomersController != null) {
        manageCustomersController.reset();
      }
    }

  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == exitMenuItem)
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
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

}
