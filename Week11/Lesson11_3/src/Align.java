import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Align extends Application
{
  private Scene scene;
  private HBox mainPane;
  private VBox checkboxes;
  private GridPane textFields;
  private VBox buttons;
  private TextField textX;
  private TextField textY;
  private Button ok;
  private Button cancel;
  private Button clear;
  private CheckBox exitOk;
  private CheckBox exitCancel;
  private Label x;
  private Label y;

  public void start(Stage window)
  {
    window.setTitle("Align");

    exitOk = new CheckBox("Exit on OK");
    exitCancel = new CheckBox("Exit on Cancel");

    x = new Label("X: ");
    y = new Label("Y: ");

    textX = new TextField();
    textX.setPrefWidth(50);
    textY = new TextField();
    textY.setPrefWidth(50);

    ok = new Button("OK");
    cancel = new Button("Cancel");
    clear = new Button("Clear");

    mainPane = new HBox(5);
    mainPane.setAlignment(Pos.BASELINE_CENTER);
    mainPane.setPadding(new Insets(3, 3, 3, 3));

    checkboxes = new VBox(20);
    checkboxes.setAlignment(Pos.BASELINE_LEFT);
    checkboxes.getChildren().add(exitOk);
    checkboxes.getChildren().add(exitCancel);

    textFields = new GridPane();
    textFields.setVgap(20);
    textFields.setAlignment(Pos.CENTER_LEFT);
    textFields.add(x, 0, 0);
    textFields.add(textX, 1, 0);
    textFields.add(y, 0, 1);
    textFields.add(textY, 1, 1);

    buttons = new VBox(3);
    buttons.setAlignment(Pos.BASELINE_LEFT);
    buttons.getChildren().add(ok);
    buttons.getChildren().add(cancel);
    buttons.getChildren().add(clear);
    ok.setPrefWidth(80);
    clear.setPrefWidth(80);
    cancel.setPrefWidth(80);

    scene = new Scene(mainPane, 270, 105);
    mainPane.getChildren().addAll(checkboxes, textFields, buttons);
    window.setResizable(false);
    window.setScene(scene);
    window.show();
  }
}
