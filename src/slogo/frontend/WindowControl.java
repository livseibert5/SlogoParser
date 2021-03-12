package slogo.frontend;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.stage.Stage;
import slogo.controller.Controller;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

/**
 * Creates stage, scene, and animation.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public class WindowControl {
  private static final int HELP_X = 1000;
  private static final int HELP_Y = 700;

  private CreateScene myScene;
  private Group root = new Group();

  private Controller myController;
  private Parser myParser;
  private TurtleDisplay myTurtleDisplay;
  private SceneComponents myComponents;
  private HelpButtonMaker helpButton;
  private EnterButtonMaker enterButton;


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
    helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", 0, 0, root, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          myParser.parse(myComponents.getTextInput());
          myTurtleDisplay.updateTurtleView(1);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (NoSuchMethodException e) {
          e.printStackTrace();
        } catch (InvocationTargetException e) {
          e.printStackTrace();
        } catch (InstantiationException e) {
          e.printStackTrace();
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        } catch (MathException e) {
          e.printStackTrace();
        }
      }
    });
  }
}
