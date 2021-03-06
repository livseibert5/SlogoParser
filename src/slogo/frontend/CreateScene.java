package slogo.frontend;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ResourceBundle;

public class CreateScene {
    private final Group root = new Group();
    private static final int DEFAULT_SIZE = 1200;
    public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
    public static final String DEFAULT_RESOURCE_FOLDER = "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/");
    public static final String STYLESHEET = "default.css";
    private final ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "text");
    private final Stage stage;
    public static final double FRAMES_PER_SECOND = 60.0;
    public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;

    public CreateScene(Stage myStage) {
        this.stage = myStage;
    }

    public Scene makeScene() {
        Scene scene = new Scene(root, DEFAULT_SIZE, DEFAULT_SIZE);
        scene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
        return scene;
    }
    public void displayStage(Scene scene) {
        stage.setScene(scene);
        stage.setTitle(myResources.getString("MenuTitle"));
        stage.show();
    }
    public Timeline makeTimeline() {
        Timeline animation = new Timeline();
        KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), e -> update());
        animation.setCycleCount(Timeline.INDEFINITE);
        animation.getKeyFrames().add(frame);
        animation.play();
        return animation;
    }
}
