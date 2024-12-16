package view.kennel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.*;
import model.Pets.Pet;
import view.ViewHandler;

public class CurrentViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Booking> allBookingsTable;
  @FXML private TableView.TableViewSelectionModel<Booking> defaultSelectionModel;

  @FXML private TableColumn<Customer, String> firstName;
  @FXML private TableColumn<Customer, String> lastName;
  @FXML private TableColumn<Pet, String> petType;
  @FXML private TableColumn<MyDate, String> startDate;
  @FXML private TableColumn<MyDate, String> endDate;
  @FXML private TableColumn<Integer, String> price;

  @FXML private void initialize()
  {
    //setupTableColumns();
    defaultSelectionModel = allBookingsTable.getSelectionModel();
  }

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

