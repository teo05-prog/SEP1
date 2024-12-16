package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ModelManager;
import view.customers.MainCustomersViewController;
import view.kennel.KennelViewController;
import view.main.MainViewController;
import view.pets.PetsViewController;
import view.purchases.PurchasesViewController;

import java.io.IOException;

public class ViewHandler
{
  private Stage stage;
  private Scene currentScene;
  private MainViewController mainViewController;
  private MainCustomersViewController customersViewController;
  private PetsViewController petsViewController;
  private KennelViewController kennelViewController;
  private PurchasesViewController purchasesViewController;

  private ModelManager modelManager;

  public ViewHandler(Stage stage, ModelManager modelManager)
  {
    this.stage = stage;
    this.modelManager = modelManager;
  }

  public void start()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("/view/main/MainView.fxml"));

      Parent root = loader.load();
      Scene scene = new Scene(root);

      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);

      stage.setScene(scene);
      stage.setTitle("VIAPets App");
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public void openView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/main/MainView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }

  public void openKennelView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/kennel/KennelView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }

  public void openPurchasesView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/purchases/PurchasesView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainViewController controller = loader.getController();
      controller.init(this, modelManager, scene);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }

  public void openCustomersView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/customers/MainCustomersView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      MainCustomersViewController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }

  public void openPetsView(String id)
  {
    FXMLLoader loader = new FXMLLoader(
        getClass().getResource("/view/pets/PetsView.fxml"));
    try
    {
      Parent root = loader.load();
      Scene scene = new Scene(root);
      PetsViewController controller = loader.getController();
      controller.init(this, scene, modelManager);
      stage.setScene(scene);
      stage.show();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

    if (stage.getScene() == null)
    {
      System.err.println("Error: Scene is null. Cannot open view " + id);
      return;
    }
  }
}
