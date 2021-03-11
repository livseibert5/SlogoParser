package slogo;

import javafx.application.Application;
import javafx.stage.Stage;
import slogo.view.WindowControl;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.frontend.CreateScene;
import slogo.frontend.SceneComponents;

/**
 * Initializes window.
 *
 * @author Jessica Yang
 */
public class Main extends Application {

    /**
     * Start of the program.
     */
    @Override
    public void start(Stage stage) {
        CreateScene newScene = new CreateScene(stage);
        Scene scene = newScene.makeScene();
        newScene.displayStage(scene);
        SceneComponents components = new SceneComponents(newScene.getRoot());
        components.addEverything();
    }
    public static void main (String[] args) {
        launch(args);
    }
}
