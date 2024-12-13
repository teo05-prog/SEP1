package view.purchases;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import model.ModelManager;
import model.Purchase;
import model.PurchaseList;
import view.ViewHandler;

public class AllPurchasesViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Purchase> allPurchasesTable;
  @FXML private TableView.TableViewSelectionModel<Purchase> defaultSelectionModel;

  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      allPurchasesTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allPurchasesTable.getSelectionModel().clearSelection();
      allPurchasesTable.setSelectionModel(null);
    }
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updatePurchaseArea();
    }
  }

  private void updatePurchaseArea()
  {
    allPurchasesTable.getItems().clear();
    PurchaseList purchases = modelManager.getAllPurchases();

    for (int i = 0; i < purchases.size(); i++)
    {
      allPurchasesTable.getItems().add(purchases.get(i));
    }
  }
}
