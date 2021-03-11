package slogo.frontend;

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

  private Stage myStage;
  private CreateScene myScene;

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
  public WindowControl() {
    myStage = new Stage();
    myStage.setResizable(false);

    //myStage.setScene(myScene.getScene());
    myStage.setTitle(TITLE);
    myStage.show();

    myScene = new CreateScene(myStage);
    myController = new Controller();
    myParser = new Parser(myController);
    myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler().getTurtle(), myScene.getRoot());
  }
}
