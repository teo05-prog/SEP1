package view.kennel;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.Booking;
import model.KennelList;
import model.ModelManager;
import view.ViewHandler;

public class PastViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Booking> allPastBookingsTable;
  @FXML private TableView.TableViewSelectionModel<Booking> defaultSelectionModel;

  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      allPastBookingsTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allPastBookingsTable.getSelectionModel().clearSelection();
      allPastBookingsTable.setSelectionModel(null);
    }
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    reset();
  }

  public void reset()
  {
    if (modelManager != null)
    {
      updatePastBookingsArea();
    }
  }

  private void updatePastBookingsArea()
  {
    allPastBookingsTable.getItems().clear();
    KennelList bookings = modelManager.getAllBookings();

    for (int i = 0; i < bookings.size(); i++)
    {
      allPastBookingsTable.getItems().add(bookings.get(i));
    }
  }
}
