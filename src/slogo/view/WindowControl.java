package slogo.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;
import slogo.controller.Controller;
import slogo.model.Turtle;

/**
 * Creates stage, scene, and animation.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class WindowControl {

  public static final String TITLE = "SLogo";

  private Stage myStage;
  //private CreateScene myScene;

  private static final int FRAMES_PER_SECOND = 2;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final Timeline animation = new Timeline();

  private Controller myController = new Controller();
  private static TurtleDisplay myTurtleDisplay;

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl() {
    myStage = new Stage();
    myStage.setResizable(false);

    //myStage.setScene(myScene.getScene());
    myStage.setTitle(TITLE);
    myStage.show();

    //use a group
    //myTurtleDisplay = new TurtleDisplay(Root, myController.getTurtleHandler());
  }

  /**
   * Starts animation for Turtle.
   */
  // on observer change, update! TODO
/*
  private static void animationStart() {
    KeyFrame frame = new KeyFrame(Duration.seconds(SECOND_DELAY), event -> myTurtleDisplay.step());
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

 */
}
