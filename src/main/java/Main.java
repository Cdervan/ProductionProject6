import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class runs main GUI application.
 *
 * @author Christopher Dervan
 */
public class Main extends Application {

  /**
   * Main method.
   *
   * @param args - String array arguments
   */
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Starting point of JavaFX program. Sets window title.
   *
   * @param primaryStage - the main/start stage, represents primary window
   * @throws Exception for the stage
   */
  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 550, 550);

    primaryStage.setTitle("Production Log");

    primaryStage.setScene(scene);

    primaryStage.show();

    scene.getStylesheets().add(getClass().getResource("stylesheet.css").toExternalForm());
  }
}
