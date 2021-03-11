package slogo;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slogo.frontend.CreateScene;
import slogo.frontend.SceneComponents;

/**
 * Feel free to completely change this code or delete it entirely. 
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
