package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class SceneMaker {

  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
  public static final String STYLESHEET = "default.css";
  private final ResourceBundle myResources = ResourceBundle
      .getBundle(DEFAULT_RESOURCE_PACKAGE + "text" );
  private final Scene scene;

  public SceneMaker(Group root, Stage stage, int width, int height) {
    scene = makeScene(root, width, height);
    displayStage(stage);
  }

  private Scene makeScene(Group root, int width, int height) {
    Scene scene = new Scene(root, width, height);
    scene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  private void displayStage(Stage stage) {
    stage.setScene(scene);
    stage.setTitle("Slogo");
    stage.setResizable(false);
    stage.show();
  }
}
