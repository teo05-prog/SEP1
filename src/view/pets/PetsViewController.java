package view.pets;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import model.ModelManager;
import view.ViewHandler;

public class PetsViewController
{
  @FXML private DogsViewController dogsViewController;
  @FXML private CatsViewController catsViewController;
  @FXML private BirdsViewController birdsViewController;
  @FXML private FishViewController fishViewController;
  @FXML private RodentsViewController rodentsViewController;
  @FXML private VariousViewController variousViewController;

  @FXML private TabPane tabPane;
  @FXML private Tab dogsTab;
  @FXML private Tab catsTab;
  @FXML private Tab birdsTab;
  @FXML private Tab fishTab;
  @FXML private Tab rodentsTab;
  @FXML private Tab variousTab;
  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;
  @FXML private Button backButton;

  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML public void initialize()
  {

  }

  public void init(ViewHandler viewHandler, Scene scene,
      ModelManager modelManager)
  {
    this.modelManager = modelManager;
    this.viewHandler = viewHandler;
    this.scene = scene;

    if (dogsViewController != null)
      dogsViewController.init(viewHandler, modelManager, scene);
    if (catsViewController != null)
      catsViewController.init(viewHandler, modelManager, scene);
    if (birdsViewController != null)
      birdsViewController.init(viewHandler, modelManager, scene);
    if (fishViewController != null)
      fishViewController.init(viewHandler, modelManager, scene);
    if (rodentsViewController != null)
      rodentsViewController.init(viewHandler, modelManager, scene);
    if (variousViewController != null)
      variousViewController.init(viewHandler, modelManager, scene);
  }

  public void reset()
  {
    if (dogsTab.isSelected())
    {
      dogsViewController.reset();
    }
    else if (catsTab.isSelected())
    {
      catsViewController.reset();
    }
    else if (birdsTab.isSelected())
    {
      birdsViewController.reset();
    }
    else if (fishTab.isSelected())
    {
      fishViewController.reset();
    }
    else if (rodentsTab.isSelected())
    {
      rodentsViewController.reset();
    }
    else if (variousTab.isSelected())
    {
      variousViewController.reset();
    }
  }

  public Scene getScene()
  {
    return scene;
  }

  @FXML public void tabChanged(Event event)
  {
    if (dogsTab.isSelected())
    {
      dogsViewController.reset();
    }
    else if (catsTab.isSelected())
    {
      catsViewController.reset();
    }
    else if (birdsTab.isSelected())
    {
      birdsViewController.reset();
    }
    else if (fishTab.isSelected())
    {
      fishViewController.reset();
    }
    else if (rodentsTab.isSelected())
    {
      rodentsViewController.reset();
    }
    else if (variousTab.isSelected())
    {
      variousViewController.reset();
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
          "Here you can check existing pets in the shop, while also being able to add new or to remove existing pets.");
      alert.showAndWait();
    }
    else if (e.getSource() == backButton)
    {
      viewHandler.openView("MainView");
    }
  }

  public TabPane getTabPane()
  {
    return tabPane;
  }

  public void setTabPane(TabPane tabPane)
  {
    this.tabPane = tabPane;
  }
}