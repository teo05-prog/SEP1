package view.pets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pets.Various;

public class AddVariousViewController
{

  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField colorField;
  @FXML private ComboBox<String> genderComboBox;
  @FXML private TextField priceField;
  @FXML private TextField commentField;
  @FXML private TextField specieField;

  private Various newVarious;

  @FXML private void handleSave(ActionEvent event)
  {
    try
    {
      String name = nameField.getText().trim();
      int age = Integer.parseInt(ageField.getText().trim());
      String color = colorField.getText().trim();
      char gender = genderComboBox.getValue().charAt(0);
      String comment = commentField.getText().trim();
      int price = Integer.parseInt(priceField.getText().trim());
      String specie = specieField.getText().trim();

      newVarious = new Various(name, age, color, gender, comment, price,
          specie);

      Stage stage = (Stage) nameField.getScene().getWindow();
      stage.close();
    }
    catch (Exception e)
    {
      System.out.println("Invalid input: " + e.getMessage());
    }
  }

  @FXML private void handleCancel(ActionEvent event)
  {
    newVarious = null;
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }

  public Various getNewVarious()
  {
    return newVarious;
  }
}
