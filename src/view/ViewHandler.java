package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
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
    loadViewMain();
    loadViewCustomers();
    loadViewPets();
    loadViewKennel();
    loadViewPurchases();
    openView("MainView");
  }

  public void openView(String id)
  {
    switch (id)
    {
      case "MainView":
        stage.setScene(mainViewController.getScene());
        mainViewController.reset();
        break;
      case "CustomersView":
        stage.setScene(customersViewController.getScene());
        customersViewController.reset();
        break;
      case "PetsView":
        stage.setScene(petsViewController.getScene());
        petsViewController.reset();
        break;
      case "KennelView":
        stage.setScene(kennelViewController.getScene());
        kennelViewController.reset();
        break;
      case "PurchasesView":
        stage.setScene(purchasesViewController.getScene());
        purchasesViewController.reset();
        break;
    }

    String title = "";

    if(stage.getScene().getRoot().getUserData() !=null)
    {
      title = stage.getScene().getRoot().getUserData().toString();
    }

    stage.setTitle(title);
    stage.show();
  }

  private void loadViewMain()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MainView.fxml"));
      Region root = loader.load();
      mainViewController = loader.getController();
      mainViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewCustomers()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("MainCustomersView.fxml"));
      Region root = loader.load();
      customersViewController = loader.getController();
      customersViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewPets()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("PetsView.fxml"));
      Region root = loader.load();
      petsViewController = loader.getController();
      petsViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewKennel()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("KennelView.fxml"));
      Region root = loader.load();
      kennelViewController = loader.getController();
      kennelViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  private void loadViewPurchases()
  {
    try
    {
      FXMLLoader loader = new FXMLLoader();
      loader.setLocation(getClass().getResource("PurchasesView.fxml"));
      Region root = loader.load();
      purchasesViewController = loader.getController();
      purchasesViewController.init(this, new Scene(root), modelManager);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
