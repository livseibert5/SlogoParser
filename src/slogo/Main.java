package slogo;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import slogo.frontend.CreateScene;
import slogo.frontend.SceneComponents;
import slogo.frontend.WindowControl;

/**
 * Initializes window.
 *
 * @author Jessica Yang
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Start of the program.
   */
  @Override
  public void start(Stage stage) {
    WindowControl window = new WindowControl(stage);
  }
}
