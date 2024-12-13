package view.pets;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class PetsViewController
{
  @FXML private DogsViewController dogsViewController;
  @FXML private CatsViewController catsViewController;
  @FXML private BirdsViewController birdsViewController;
  @FXML private FishViewController fishViewController;
  @FXML private RodentsViewController rodentsViewController;
  @FXML private VariousViewController variousViewController;

  @FXML private TabPane tabPane;
  @FXML private Tab dogsTab;
  @FXML private Tab catsTab;
  @FXML private Tab birdsTab;
  @FXML private Tab fishTab;
  @FXML private Tab rodentsTab;
  @FXML private Tab variousTab;
  @FXML private MenuItem fileMenuItem;
  @FXML private MenuItem aboutMenuItem;
}
