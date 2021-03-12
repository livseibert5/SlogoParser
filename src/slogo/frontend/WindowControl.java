package slogo.frontend;

import javafx.scene.Group;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.parser.Parser;

/**
 * Creates stage, scene, and animation.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class WindowControl {

  public static final String TITLE = "SLogo";

  private CreateScene myScene;
  private Group root = new Group();

  private Controller myController;
  private Parser myParser;
  private static TurtleDisplay myTurtleDisplay;

  /*
  private static final int FRAMES_PER_SECOND = 2;
  private static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
  private static final Timeline animation = new Timeline();
   */


  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {

    myScene = new CreateScene(myStage, root);
    myController = new Controller();
    myParser = new Parser(myController);
    //myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler().getTurtle(), root);
  }
}
