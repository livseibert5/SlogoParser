package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
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
  private static final int HELP_X = 1000;
  private static final int HELP_Y = 700;
  private static final int ENTER_X = 300;
  private static final int ENTER_Y = 675;
  private static final int UPLOAD_X = 175;
  private static final int UPLOAD_Y = 0;

  private CreateScene myScene;
  private Group root = new Group();

  private Controller myController;
  private Parser myParser;
  private TurtleDisplay myTurtleDisplay;
  private SceneComponents myComponents;
  private HelpButtonMaker helpButton;
  private EnterButtonMaker enterButton;
  private UploadButtonMaker uploadButton;


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

    uploadButton = new UploadButtonMaker("Upload Image", UPLOAD_X, UPLOAD_Y, root);
    helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          int value = myParser.parse(myComponents.getTextInput());
          myTurtleDisplay.updateTurtleView(1);
          myComponents.clearTextInput();
          myComponents.getReturnValue(value);
        } catch (Exception e) {
          makeErrorWindow("Invalid command.");
        }
      }
    });
  }

  private void makeErrorWindow(String errorMessage) {
    double windowSize = 200;
    Group errorRoot = new Group();
    Scene errorScene = new Scene(errorRoot, windowSize, windowSize);

    Text errorText = new Text(windowSize / 2, windowSize / 2, errorMessage);
    errorText.setFill(Color.BLACK);
    errorText.setId("errorMessage");
    errorRoot.getChildren().add(errorText);

    Stage errorWindow = new Stage();
    errorWindow.setTitle("Error!");
    errorWindow.setScene(errorScene);
    errorWindow.show();
  }
}
