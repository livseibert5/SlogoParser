package slogo;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Creates stage, scene, and animation.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class Control {

  public static final String TITLE = "SLogo";

  private Stage myStage;
  //private CreateScene myScene;

  private static final int FRAMES_PER_SECOND = 2;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final Timeline animation = new Timeline();

  /**
   * Constructor for Control class. Returns Control object.
   */
  public Control() {
    myStage = new Stage();
    myStage.setResizable(false);

    //myStage.setScene(myScene.getScene());
    myStage.setTitle(TITLE);
    myStage.show();
  }
}
