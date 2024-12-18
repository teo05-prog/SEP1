package view.purchases;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import model.ModelManager;
import model.Purchase;
import model.PurchaseList;
import view.ViewHandler;

import java.time.LocalTime;

public class AllPurchasesViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Purchase> allPurchasesTable;
  @FXML private TableColumn<Purchase, String> dateColumn;
  @FXML private TableColumn<Purchase, String> timeColumn;
  @FXML private TableColumn<Purchase, String> nameColumn;
  @FXML private TableColumn<Purchase, String> petNameColumn;
  @FXML private TableColumn<Purchase, String> typeColumn;
  @FXML private TableColumn<Purchase, String> discountColumn;
  @FXML private TableColumn<Purchase, Double> priceColumn;
  @FXML private TableView.TableViewSelectionModel<Purchase> defaultSelectionModel;

  public void initialize()
  {
    dateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDateOfPurchase().toString()));

    timeColumn.setCellValueFactory(cellData -> {
      LocalTime time = cellData.getValue().getTimeOfPurchase();
      if (time == null)
      {
        return new SimpleStringProperty("N/A");
      }
      return new SimpleStringProperty(
          String.format("%02d:%02d", time.getHour(), time.getMinute()));
    });

    nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getCustomer().getFirstName() + " "
            + cellData.getValue().getCustomer().getLastName()));

    petNameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getPet().getName()));

    typeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getPet().getType()));

    discountColumn.setCellValueFactory(cellData -> new SimpleStringProperty(
        cellData.getValue().getDiscount() + "%"));

    priceColumn.setCellValueFactory(cellData -> {
      double price = cellData.getValue().getPet().getPrice() * (1
          - cellData.getValue().getDiscount() / 100.0);
      return new SimpleDoubleProperty(price).asObject();
    });

    defaultSelectionModel = allPurchasesTable.getSelectionModel();
  }

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
    if (allPurchasesTable == null)
    {
      System.err.println("Table not initialized");
      return;
    }

    allPurchasesTable.getItems().clear();
    PurchaseList purchases = modelManager.getAllPurchases();

    if (purchases != null)
    {
      for (int i = 0; i < purchases.size(); i++)
      {
        Purchase p = purchases.get(i);
        if (p != null)
        {
          allPurchasesTable.getItems().add(p);
        }
      }
    }
    else
    {
      System.err.println("No purchases data available");
    }
  }
}