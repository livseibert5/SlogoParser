package slogo;

import javafx.application.Application;
import javafx.stage.Stage;

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
    Control control = new Control();
  }
}
