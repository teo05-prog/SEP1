package view.pets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pets.Rodent;

public class AddRodentViewController
{

  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField colorField;
  @FXML private ComboBox<String> genderComboBox;
  @FXML private TextField priceField;
  @FXML private TextField commentField;
  @FXML private TextField specieField;
  @FXML private TextField doesItBiteField;

  private Rodent newRodent;

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
      boolean doesItBite = Boolean.parseBoolean(
          doesItBiteField.getText().trim());

      newRodent = new Rodent(name, age, color, gender, comment, price,
          doesItBite, specie);

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
    newRodent = null;
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }

  public Rodent getNewRodent()
  {
    return newRodent;
  }
}

