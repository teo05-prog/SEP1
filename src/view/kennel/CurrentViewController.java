package view.kennel;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import model.*;
import view.ViewHandler;

public class CurrentViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Booking> allBookingsTable;
  @FXML private TableView.TableViewSelectionModel<Booking> defaultSelectionModel;

  public void changeSelectableState(boolean bool)
  {
    if (bool)
    {
      allBookingsTable.setSelectionModel(defaultSelectionModel);
    }
    else
    {
      allBookingsTable.getSelectionModel().clearSelection();
      allBookingsTable.setSelectionModel(null);
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
      updateBookingsArea();
    }
  }

  private void updateBookingsArea()
  {
    allBookingsTable.getItems().clear();
    KennelList bookings = modelManager.getAllBookings();
    MyDate today = MyDate.today();

    for (int i = 0; i < bookings.size(); i++)
    {
      Booking booking = bookings.get(i);
      if (isCurrentBooking(booking, today))
      {
        allBookingsTable.getItems().add(booking);
      }
    }
  }

  private boolean isCurrentBooking(Booking booking, MyDate today)
  {
    return (today.compareTo(booking.getStartDate()) >= 0
        && today.compareTo(booking.getEndDate()) <= 0);
  }
}

