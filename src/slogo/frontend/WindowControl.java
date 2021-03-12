package slogo.frontend;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
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

  private CreateScene myScene;
  private Group root = new Group();

  private Controller myController;
  private Parser myParser;
  private TurtleDisplay myTurtleDisplay;
  private SceneComponents myComponents;

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    myScene = new CreateScene(myStage, root);
    myController = new Controller();
    myParser = new Parser(myController);
    myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler().getTurtle(1), root);
    List<PropertyChangeListener> listenerList = new ArrayList<>();
    listenerList.add(myTurtleDisplay);
    myComponents = new SceneComponents(root, listenerList);
  }
}
