package view.kennel;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Booking;
import model.Customer;
import model.ModelManager;
import view.ViewHandler;

public class NewBookingViewController
{
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private MenuItem exitMenuItem;
  @FXML private MenuItem aboutMenuItem;

  @FXML private DatePicker startDate;
  @FXML private DatePicker endDate;
  @FXML private TextField emailAndPhoneField;
  @FXML private TextField petNameField;
  @FXML private TextArea petInfoArea;
  @FXML private ChoiceBox<String> petTypes;
  @FXML private Button addButton;

  public void init(ViewHandler viewHandler, ModelManager modelManager)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    reset();
  }

  public void reset()
  {
    startDate.setValue(null);
    endDate.setValue(null);
    emailAndPhoneField.clear();
    petNameField.clear();
    petInfoArea.clear();
    petTypes.getSelectionModel().clearSelection();

    if (petTypes.getItems().isEmpty())
    {
      petTypes.getItems()
          .addAll("Dog", "Cat", "Bird", "Fish", "Rodent", "Various");
    }
  }
}
