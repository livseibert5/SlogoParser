package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class CreateScene {

  private final Group root;
  private static final int DEFAULT_HEIGHT = 750;
  private static final int DEFAULT_WIDTH = 1350;
  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
  public static final String STYLESHEET = "default.css";
  private final ResourceBundle myResources = ResourceBundle
      .getBundle(DEFAULT_RESOURCE_PACKAGE + "text" );
  private final Stage stage;

  public CreateScene(Stage myStage, Group myRoot) {
    this.stage = myStage;
    this.root = myRoot;
    displayStage(makeScene());
  }

  private Scene makeScene() {
    Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    scene.getStylesheets()
        .add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    return scene;
  }

  private void displayStage(Scene scene) {
    stage.setScene(scene);
    stage.setTitle("Slogo" );
    stage.setResizable(false);
    stage.show();
  }


}
