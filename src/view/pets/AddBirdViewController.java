package view.pets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Pets.Bird;
import model.Pets.PetList;
import model.ModelManager;
import utils.PetsXML;
import view.ViewHandler;

public class AddBirdViewController
{
  private Scene scene;
  private ModelManager modelManager;
  private ViewHandler viewHandler;

  @FXML private TextField nameField;
  @FXML private TextField ageField;
  @FXML private TextField colorField;
  @FXML private ComboBox<String> genderComboBox;
  @FXML private TextField priceField;
  @FXML private TextField commentField;
  @FXML private TextField preferredFoodField;
  @FXML private TextField specieField;

  private Bird newBird;

  public void init(ViewHandler viewHandler, ModelManager modelManager,
      Scene scene)
  {
    this.viewHandler = viewHandler;
    this.modelManager = modelManager;
    this.scene = scene;
  }

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
      String preferredFood = preferredFoodField.getText().trim();
      String specie = specieField.getText().trim();

      newBird = new Bird(name, age, color, gender, comment, price,
          preferredFood, specie);

      PetList pets = modelManager.getAllPets();
      pets.add(newBird);
      modelManager.savePets(pets);

      new PetsXML();

      Stage stage = (Stage) nameField.getScene().getWindow();
      stage.close();
    }
    catch (Exception e)
    {
      System.out.println("Error saving bird: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @FXML private void handleCancel(ActionEvent event)
  {
    newBird = null;
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }

  public Bird getNewBird()
  {
    return newBird;
  }

  public Scene getScene()
  {
    return scene;
  }
}