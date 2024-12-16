package view.kennel;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.*;
import view.ViewHandler;

public class PastViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TableView<Booking> allPastBookingsTable;
  @FXML private TableView.TableViewSelectionModel<Booking> defaultSelectionModel;

  @FXML private TableColumn<Booking, String> firstName;
  @FXML private TableColumn<Booking, String> lastName;
  @FXML private TableColumn<Booking, String> petType;
  @FXML private TableColumn<Booking, String> petName;
  @FXML private TableColumn<Booking, String> startDate;
  @FXML private TableColumn<Booking, String> endDate;
  @FXML private TableColumn<Booking, Double> price;

  @FXML private void initialize()
  {
    defaultSelectionModel = allPastBookingsTable.getSelectionModel();
    setupTableColumns();
  }

  private void setupTableColumns()
  {
    firstName.setCellValueFactory(
        new PropertyValueFactory<>("customerFirstName"));
    lastName.setCellValueFactory(
        new PropertyValueFactory<>("customerLastName"));
    petType.setCellValueFactory(new PropertyValueFactory<>("petType"));
    petName.setCellValueFactory(new PropertyValueFactory<>("petName"));
    startDate.setCellValueFactory(
        new PropertyValueFactory<>("startDateString"));
    endDate.setCellValueFactory(new PropertyValueFactory<>("endDateString"));

    price.setCellValueFactory(cellData -> {
      Booking booking = cellData.getValue();
      return new SimpleDoubleProperty(booking.getPrice()).asObject();
    });

    price.setCellFactory(tc -> new TableCell<>()
    {
      @Override protected void updateItem(Double price, boolean empty)
      {
        super.updateItem(price, empty);
        if (empty || price == null)
        {
          setText(null);
        }
        else
        {
          setText(String.format("%.2f", price));
        }
      }
    });
  }

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
    MyDate today = MyDate.today();

    for (int i = 0; i < bookings.size(); i++)
    {
      Booking booking = bookings.get(i);
      if (booking.getEndDate().isBefore(today))
      {
        allPastBookingsTable.getItems().add(booking);
      }
    }
  }

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    changeSelectableState(true);
    reset();
  }
}