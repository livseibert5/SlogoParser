package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import slogo.Observable;
import slogo.controller.Controller;
import slogo.model.Turtle;
import slogo.model.ToXML;
import slogo.model.parser.Parser;
import java.lang.reflect.Field;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

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

  private SceneMaker sceneMaker;
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
  private CommandField myCommand;
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');

  private int imageNumber = 1;
  private final Parser myParser;
  private final TurtleDisplay myTurtleDisplay;
  //private final SceneComponents myComponents;
  //private final String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private String USER_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private ErrorView errorWindow = new ErrorView(200, 200);
  private ViewMaker turtleDetailsWindow;

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    sceneMaker = new SceneMaker(root, myStage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myController = new Controller();
    myParser = new Parser(myController);
    myTableDisplay = new TableDisplay(myController.getVariableHandler(), myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(root, myController.getTurtleHandler(), myController.getColorHandler());
    myController.getTurtleHandler().addMultipleListeners(myTurtleDisplay.getListeners());
    myTableDisplay.setHandler(event -> {
      try {
        myCommand.executeSourcedCommand(myTableDisplay.getSelectedUserCommand());
      } catch (Exception e) {
        errorWindow.updateMessage("No commands available.");
        errorWindow.showView();
      }
    });
    TurtleWindow myTurtleWindow = new TurtleWindow(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myCommand = new CommandField(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_HEIGHT);
    ImageCustomizer myCustomizer = new ImageCustomizer(WINDOW_SIZE, WINDOW_SIZE, "Customize Colors and Images", myTurtleDisplay, myTurtleWindow);
    CustomizerButton customizerButton = new CustomizerButton("Customize", UPLOAD_X, UPLOAD_Y, root, event -> myCustomizer.showView());
    helpButton = new HelpButtonMaker("Help", HELP_X + 50, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root, event -> enterEvent());
    LanguageDropDown dropDown = new LanguageDropDown(root, myController);
    //ColorPickerMaker penColor = new ColorPickerMaker(root, DEFAULT_WIDTH - WINDOW_SIZE/2 - DEFAULT_BORDER, DEFAULT_BORDER/3, "Pen");
    //penColor.setHandler(event -> myTurtleDisplay.setLineColor(penColor.getNewColor()));
    ToXML toXML = new ToXML(myController);
    WorkspaceButtonMaker newButton = new WorkspaceButtonMaker("New Workspace", DEFAULT_WIDTH - 200, 0, root);
    EnterButtonMaker xmlUpload = new EnterButtonMaker("Upload XML File", HELP_X - 200, HELP_Y, root, event -> uploadXML());
    EnterButtonMaker xmlSave = new EnterButtonMaker("Save as XML", HELP_X + 150, HELP_Y, root, event -> {
      try {
        toXML.exportToXML();
      } catch (ParserConfigurationException e) {
        e.printStackTrace();
      } catch (TransformerException e) {
        e.printStackTrace();
      }
    });
    turtleDetailsWindow = new TurtleDetailsView(400, 400, myController.getTurtleHandler());
    Button turtleDetailButton = makeButton(HELP_X + 100, HELP_Y, "Turtle Details",
        e -> turtleDetailsWindow.showView());
    setUpKeyInput();
  }

  private Button makeButton(double x, double y, String title, EventHandler<ActionEvent> evt) {
    Button newButton = new Button(title);
    newButton.relocate(x, y);
    newButton.setId(title.replaceAll(" ", ""));
    newButton.setOnAction(evt);
    root.getChildren().add(newButton);
    return newButton;
  }

  private void enterEvent() {
    try {
      int value = myParser.parse(myCommand.getTextInput());
      myCommand.clearTextInput();
      myCommand.printReturnValue(value);
    } catch (Exception e) {
      errorWindow.updateMessage("Invalid command.");
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

  /**
   * Assumes user will not use ctrl key.
   */
  private void setUpKeyInput() {
    try {
      Class<?> keyHandlerClass = Class.forName("slogo.frontend.KeyInputHandler");
      KeyInputHandler keyHandler = new KeyInputHandler();
      List<PropertyChangeListener> turtleListeners = new ArrayList<>();
      for (Turtle turtle: myController.getTurtleHandler().getActiveTurtles()) {
        turtleListeners.addAll(turtle.getListeners());
      }
      keyHandler.addMultipleListeners(turtleListeners); //TODO add listeners from backend
      root.setOnKeyPressed(evt -> {
        try {
          Method moveMethod = keyHandlerClass.getMethod("press" + evt.getCode().toString());
          moveMethod.invoke(keyHandler);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          errorWindow.updateMessage("Invalid key button. Try using WASD, and RL.");
          errorWindow.showView();
        }
      });
    } catch (ClassNotFoundException e) {
      errorWindow.updateMessage("Fatal error: KeyInputHandler class not found.");
      errorWindow.showView();
    }
  }
}
