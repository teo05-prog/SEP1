package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.ModelManager;

public class StartApplication extends Application
{
  @Override public void start(Stage primaryStage)
  {
    try
    {
      primaryStage.setTitle("VIAPets App");
      ModelManager modelManager = new ModelManager("customers.bin", "pets.bin",
          "bookings.bin", "purchases.bin");
      ViewHandler viewHandler = new ViewHandler(primaryStage, modelManager);
      viewHandler.start();
      primaryStage.show();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      System.err.println(
          "Error initializing the application: " + e.getMessage());
    }
  }

  public static void main(String[] args)
  {
    launch(args);
  }
}
