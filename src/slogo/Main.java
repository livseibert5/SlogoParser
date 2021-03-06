package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.WindowControl;

/**
 * Initializes window.
 *
 * @author Jessica Yang
 */
public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage stage) {
    WindowControl control = new WindowControl();
  }
}
