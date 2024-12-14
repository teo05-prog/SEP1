package view.pets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pets.Dog;

public class AddDogViewController
{

  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField colorField;
  @FXML private ComboBox<String> genderComboBox;
  @FXML private TextField priceField;
  @FXML private TextField commentField;
  @FXML private TextField breedField;
  @FXML private TextField breederNameField;

  private Dog newDog;

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
      String breed = breedField.getText().trim();
      String breederName = breederNameField.getText().trim();

      newDog = new Dog(name, age, color, gender, comment, price, breed,
          breederName);

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
    newDog = null;
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }

  public Dog getNewDog()
  {
    return newDog;
  }
}
