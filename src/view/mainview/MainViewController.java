package view.mainview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import model.ModelManager;
import view.ViewHandler;

public class MainViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML Button customersButton;
  @FXML Button petsButton;
  @FXML Button kennelButton;
  @FXML Button purchasesButton;
  @FXML MenuItem exitMenuItem;
  @FXML MenuItem aboutMenuItem;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

  public void handleActions(ActionEvent e)
  {
    if (viewHandler == null)
    {
      System.err.println("ViewHandler is not initialized!");
      return;
    }
    if (e.getSource() == customersButton)
    {
      viewHandler.openCustomersView("CustomersView");
    }
    else if (e.getSource() == petsButton)
    {
      viewHandler.openPetsView("PetsView");
    }
    else if (e.getSource() == kennelButton)
    {
      viewHandler.openKennelView("KennelView");
    }
    else if (e.getSource() == purchasesButton)
    {
      viewHandler.openPurchasesView("PurchasesView");
    }
    else if (e.getSource() == exitMenuItem)
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
          "This is the app for VIAPets. Here you can choose where you want to make changes or simply check stuff.");
      alert.showAndWait();
    }
  }

  public void setScene(Scene scene)
  {
    this.scene = scene;
  }

  public void reset()
  {

  }

  public Scene getScene()
  {
    return scene;
  }

}
