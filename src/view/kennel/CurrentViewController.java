package view.kennel;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import model.Pets.Pet;
import view.ViewHandler;

import java.util.ArrayList;

public class CurrentViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Booking> allBookingsTable;
  @FXML private TableColumn<Customer, String> firstNameColumn;
  @FXML private TableColumn<Customer, String> lastNameColumn;
  @FXML private TableColumn<Pet, String> petTypeColumn;
  @FXML private TableColumn<Pet, String> petNameColumn;
  @FXML private TableColumn<MyDate, String> startDateColumn;
  @FXML private TableColumn<MyDate, String> endDateColumn;
  @FXML private TableColumn<Integer, String> priceColumn;

  @FXML private TableView.TableViewSelectionModel<Booking> defaultSelectionModel;

  @FXML private void initialize()
  {
    defaultSelectionModel = allBookingsTable.getSelectionModel();
    setupTableColumns();
  }

  private void setupTableColumns()
  {
    firstNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("customerFirstName"));
    lastNameColumn.setCellValueFactory(
        new PropertyValueFactory<>("customerLastName"));
    petTypeColumn.setCellValueFactory(new PropertyValueFactory<>("petType"));
    petNameColumn.setCellValueFactory(new PropertyValueFactory<>("petName"));
    startDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("startDateString"));
    endDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("endDateString"));
    priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
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

    ArrayList<Booking> currentAndUpcoming = new ArrayList<>();
    for (int i = 0; i < bookings.size(); i++)
    {
      Booking booking = bookings.get(i);
      if (isCurrentOrUpcomingBooking(booking, today))
      {
        currentAndUpcoming.add(booking);
      }
    }
    currentAndUpcoming.sort(
        (b1, b2) -> b1.getStartDate().compareTo(b2.getStartDate()));
    allBookingsTable.getItems().addAll(currentAndUpcoming);
  }

  private boolean isCurrentOrUpcomingBooking(Booking booking, MyDate today)
  {
    return !booking.getEndDate().isBefore(today);
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    reset();
  }

}

