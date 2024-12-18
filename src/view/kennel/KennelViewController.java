package view.kennel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.KennelList;
import model.ModelManager;
import view.ViewHandler;

public class KennelViewController
{
  @FXML private CurrentViewController currentController;
  @FXML private PastViewController pastController;
  @FXML private NewBookingViewController newBookingController;

  @FXML private TabPane tabPane;
  @FXML private Tab currentTab;
  @FXML private Tab pastTab;
  @FXML private Tab newBookingTab;
  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem changePriceMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void initialize()
  {
    tabPane.getSelectionModel().selectedItemProperty()
        .addListener((obs, oldTab, newTab) -> {
          if (modelManager != null)
          {
            KennelList bookings = modelManager.getAllBookings();
          }
        });
  }

  public void init(ViewHandler viewHandler, Scene scene,
      ModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (currentController != null)
    {
      currentController.init(viewHandler, modelManager);
    }
    if (pastController != null)
    {
      pastController.init(viewHandler, modelManager);
    }
    if (newBookingController != null)
    {
      newBookingController.init(viewHandler, modelManager);
    }
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
    if (modelManager != null)
    {
      if (currentTab.isSelected())
      {
        if (currentController != null)
        {
          currentController.reset();
        }
      }
      else if (pastTab.isSelected())
      {
        if (pastController != null)
        {
          pastController.reset();
        }
      }
      else if (newBookingTab.isSelected())
      {
        if (newBookingController != null)
        {
          newBookingController.reset();
        }
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
          "Here you can see the current bookings, the ones in the past and also add a new booking.");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
    else if (e.getSource() == changePriceMenuItem)
    {
      TextInputDialog priceDialog = new TextInputDialog();
      priceDialog.setTitle("Change Price");
      priceDialog.setHeaderText("Enter New Price");
      priceDialog.setContentText("New Price:");

      String newPriceStr = priceDialog.showAndWait().orElse(null);

      if (newPriceStr != null)
      {
        try
        {
          double newPrice = Double.parseDouble(newPriceStr);

          if (newPrice < 0)
          {
            showErrorAlert("Price cannot be negative.");
            return;
          }
          modelManager.updatePrice(newPrice);
          currentController.reset();
          pastController.reset();
          newBookingController.reset();
          showInfoAlert("Price successfully updated to $" + newPrice);

        }
        catch (NumberFormatException ex)
        {
          showErrorAlert("Invalid price. Please enter a valid number.");
        }
      }
    }
  }

  private void showErrorAlert(String message)
  {
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    errorAlert.setTitle("Error");
    errorAlert.setHeaderText(null);
    errorAlert.setContentText(message);
    errorAlert.showAndWait();
  }

  private void showInfoAlert(String message)
  {
    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
    infoAlert.setTitle("Price Update");
    infoAlert.setHeaderText(null);
    infoAlert.setContentText(message);
    infoAlert.showAndWait();
  }
}
