package view.pets;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ModelManager;
import model.Pets.Fish;
import model.Pets.PetList;
import utils.PetsXML;
import view.ViewHandler;

public class AddFishViewController
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
  @FXML private TextField specieField;
  @FXML private TextField waterField;
  @FXML private TextField predatorField;

  private Fish newFish;

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
      String specie = specieField.getText().trim();
      String water = waterField.getText().trim();
      boolean predator = Boolean.parseBoolean(predatorField.getText().trim());

      newFish = new Fish(name, age, color, gender, comment, price, specie,
          predator, water);

      PetList pets = modelManager.getAllPets();
      pets.add(newFish);
      modelManager.savePets(pets);

      new PetsXML();

      Stage stage = (Stage) nameField.getScene().getWindow();
      stage.close();
    }
    catch (Exception e)
    {
      System.out.println("Invalid input: " + e.getMessage());
      e.printStackTrace();
    }
  }

  @FXML private void handleCancel(ActionEvent event)
  {
    newFish = null;
    Stage stage = (Stage) nameField.getScene().getWindow();
    stage.close();
  }

  public Fish getNewFish()
  {
    return newFish;
  }

  public Scene getScene()
  {
    return scene;
  }
}
