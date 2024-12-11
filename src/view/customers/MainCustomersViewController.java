package view.customers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
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

  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void initialize()
  {
    modelManager = new ModelManager("customers.bin");
    customersController.init(viewHandler, modelManager);
    manageCustomersController.init(viewHandler, modelManager);
  }

  public void reset()
  {

  }

  public void tabChanged(Event event)
  {
    if(customersTab.isSelected())
    {
      customersController.reset();
    }
    else if (manageCustomersTab.isSelected())
    {
      manageCustomersController.reset();
    }
  }

  public void handleActions(ActionEvent e)
  {
    if (e.getSource() == exitMenuItem)
    {
      Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
          "Do you really want to exit the program?",
          ButtonType.YES, ButtonType.NO);
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
      alert.setContentText("This is just a little program that demonstrates some of the GUI features in Java");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }


}
