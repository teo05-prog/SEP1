package view.kennel;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.ModelManager;
import view.ViewHandler;

public class KennelViewController
{
  @FXML private CurrentViewController currentViewController;
  @FXML private PastViewController pastViewController;
  @FXML private NewBookingViewController newBookingViewController;

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
    if (currentViewController != null)
      currentViewController.init(viewHandler, modelManager);
    if (pastViewController != null)
      pastViewController.init(viewHandler, modelManager);
    if (newBookingViewController != null)
      newBookingViewController.init(viewHandler, modelManager);
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
    if (currentTab.isSelected())
    {
      currentViewController.reset();
    }
    else if (pastTab.isSelected())
    {
      pastViewController.reset();
    }
    else if (newBookingTab.isSelected())
    {
      newBookingViewController.reset();
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
          currentViewController.reset();
          pastViewController.reset();
          newBookingViewController.reset();
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
