package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
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
  private Stage stage = new Stage();

  private Controller myController;
  private Parser myParser;
  private TurtleDisplay myTurtleDisplay;
  private TableDisplay myTableDisplay;
  private SceneComponents myComponents;
  private HelpButtonMaker helpButton;
  private EnterButtonMaker enterButton;
  private UploadButtonMaker uploadButton;
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private int imageNumber;
  private String USER_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private ViewMaker errorWindow = new ErrorView(200, 200);

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    myScene = new CreateScene(myStage, root);
    myController = new Controller();
    myParser = new Parser(myController);
    myTableDisplay = new TableDisplay(myController.getVariableHandler(), myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler(), root);

    myComponents = new SceneComponents(root, myTurtleDisplay.getListeners());

    uploadButton = new UploadButtonMaker("Upload Image", UPLOAD_X, UPLOAD_Y, root, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Turtle Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
          boolean isMoved = file.renameTo(new File("src/slogo/frontend/resources/images/UserImage.jpg"));
          System.out.println(isMoved);
          myTurtleDisplay.updateImageView();
        }
      }
    });
    helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        try {
          int value = myParser.parse(myComponents.getTextInput());
          myTurtleDisplay.updateTurtleView(1);
          myComponents.clearTextInput();
          myComponents.printReturnValue(value);
        } catch (Exception e) {
          errorWindow.showView();
        }
      }
    });
  }
}
