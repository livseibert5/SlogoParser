package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
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
  private static final int DEFAULT_HEIGHT = 750;
  private static final int DEFAULT_WIDTH = 1350;
  private static final int WINDOW_SIZE = 600;
  private static final int DEFAULT_BORDER = 50;
  private final Stage stage = new Stage();

  private CreateScene myScene;
  private Group root = new Group();

  private Controller myController;
  private Parser myParser;
  private TurtleDisplay myTurtleDisplay;
  private TableDisplay myTableDisplay;
  private SceneComponents myComponents;
  private HelpButtonMaker helpButton;
  private EnterButtonMaker enterButton;
  private UploadButtonMaker uploadButton;
  private CommandField myCommand;
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
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
    myTableDisplay.setHandler(event -> myCommand.executeSourcedCommand(myTableDisplay.getSelectedUserCommand()));
    myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler(), root);
    TurtleWindow myTurtleWindow = new TurtleWindow(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myCommand = new CommandField(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_HEIGHT);
    ImageCustomizer myCustomizer = new ImageCustomizer(WINDOW_SIZE, WINDOW_SIZE, "Customize Colors and Images", myTurtleDisplay, myTurtleWindow);
    CustomizerButton customizerButton = new CustomizerButton("Customize", UPLOAD_X, UPLOAD_Y, root, event -> myCustomizer.showView());
    helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root, event -> enterEvent());
    LanguageDropDown dropDown = new LanguageDropDown(root, myController);
    //ColorPickerMaker penColor = new ColorPickerMaker(root, DEFAULT_WIDTH - WINDOW_SIZE/2 - DEFAULT_BORDER, DEFAULT_BORDER/3, "Pen");
    //penColor.setHandler(event -> myTurtleDisplay.setLineColor(penColor.getNewColor()));
    WorkspaceButtonMaker newButton = new WorkspaceButtonMaker("New Workspace", DEFAULT_WIDTH - 200, 0, root);
    EnterButtonMaker xmlButton = new EnterButtonMaker("Upload XML File", HELP_X - 100, HELP_Y, root, event -> uploadXML());
  }



  private void enterEvent() {
    try {
      int value = myParser.parse(myCommand.getTextInput());
      myTurtleDisplay.updateTurtleView(1);
      myCommand.clearTextInput();
      myCommand.printReturnValue(value);
    } catch (Exception e) {
      errorWindow.showView();
    }
  }
  private void uploadXML() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload XML");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("XML Files", "*.xml"));
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      boolean isMoved = file.renameTo(new File("data/UserUploaded.xml"));
    }
  }

}
