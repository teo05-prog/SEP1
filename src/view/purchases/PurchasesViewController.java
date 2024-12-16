package view.purchases;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.ModelManager;
import view.ViewHandler;

public class PurchasesViewController
{
  @FXML private AllPurchasesViewController allPurchasesViewController;
  @FXML private NewPurchasesViewController newPurchasesViewController;

  @FXML private TabPane tabPane;
  @FXML private Tab allPurchasesTab;
  @FXML private Tab newPurchaseTab;
  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  public void init(ViewHandler viewHandler, Scene scene,
      ModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (allPurchasesViewController != null)
    {
      allPurchasesViewController.init(viewHandler, modelManager, scene);
      allPurchasesViewController.reset();
    }

    if (newPurchasesViewController != null)
    {
      newPurchasesViewController.init(viewHandler, modelManager);
      newPurchasesViewController.reset();
    }

    tabPane.getSelectionModel().selectedItemProperty()
        .addListener((observable, oldTab, newTab) -> {
          if (newTab == allPurchasesTab)
          {
            allPurchasesViewController.reset();
          }
          else if (newTab == newPurchaseTab)
          {
            newPurchasesViewController.reset();
          }
        });
  }

  public void reset()
  {
    if (allPurchasesViewController != null)
    {
      allPurchasesViewController.reset();
    }
    if (newPurchasesViewController != null)
    {
      newPurchasesViewController.reset();
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  @FXML public void tabChanged(Event event)
  {
    if (allPurchasesTab.isSelected())
    {
      allPurchasesViewController.reset();
    }
    else if (newPurchaseTab.isSelected())
    {
      newPurchasesViewController.reset();
    }
  }

  @FXML public void handleActions(ActionEvent e)
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
          "Here you can see all the purchases in the system and also add new ones.");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

}
