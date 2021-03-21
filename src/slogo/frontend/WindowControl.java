package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;
import slogo.controller.Controller;
import slogo.model.FromXML;
import slogo.model.Turtle;
import slogo.model.ToXML;
import slogo.model.parser.Parser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

/**
 * Creates stage, scene, and animation.
 * TODO assumptions, dependencies, example of use
 *d
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
  private static final int HELP_HEIGHT = 500;
  private static final int HELP_WIDTH = 750;

  private SceneMaker sceneMaker;
  private Group root = new Group();
  private Stage stage = new Stage();

  private Controller myController;
  private TableDisplay myTableDisplay;
  private CommandField myCommand;
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');

  private String USER_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";
  private int imageNumber = 1;
  private final Parser myParser;
  private final TurtleDisplay myTurtleDisplay;

  private ErrorView errorView = new ErrorView(200, 200);
  private ViewMaker turtleDetailsView;
  private ViewMaker helpView = new HelpView(HELP_WIDTH, HELP_HEIGHT);
  private ImageCustomizeView myCustomizer;
  private FromXML fromXML = new FromXML(myController);

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    sceneMaker = new SceneMaker(root, myStage, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myController = new Controller();
    myParser = new Parser(myController);
    myTableDisplay = new TableDisplay(myController.getVariableHandler(), myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(root, myController.getTurtleHandler(), myController.getColorHandler());
    TurtleWindow myTurtleWindow = new TurtleWindow(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myController.getTurtleHandler().addMultipleListeners(myTurtleDisplay.getListeners());
    myTableDisplay.setHandler(event -> {
      try {
        myCommand.executeSourcedCommand(myTableDisplay.getSelectedUserCommand());
      } catch (Exception e) {
        errorView.updateMessage("No commands available.");
        errorView.showView();
      }
    });
    myCommand = new CommandField(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_HEIGHT);
    myCustomizer = new ImageCustomizeView(WINDOW_SIZE, WINDOW_SIZE, "Customize Colors and Images", myTurtleDisplay, myTurtleWindow);
    LanguageDropDown dropDown = new LanguageDropDown(root, myController);
    turtleDetailsView = new TurtleDetailsView(400, 400, myController.getTurtleHandler());

    setUpButtons();
    setUpKeyInput();
  }

  private void setUpButtons() {
    ToXML toXML = new ToXML(myController);
    Button xmlSaveButton = makeButton(HELP_X + 150, HELP_Y, "Save as XML", event -> {
      try {
        toXML.exportToXML();
      } catch (ParserConfigurationException | TransformerException e) {
        e.printStackTrace();
      }
    });
    Button xmlUploadButton = makeButton(HELP_X - 200, HELP_Y, "Upload XML File", event -> uploadXML());
    Button customizerButton = makeButton(UPLOAD_X, UPLOAD_Y, "Customize", event -> myCustomizer.showView());
    Button helpButton = makeButton(HELP_X + 50, HELP_Y, "Help", evt -> helpView.showView());
    Button enterButton = makeButton(ENTER_X, ENTER_Y, "Enter", event -> enterEvent());
    Button newButton = makeButton(DEFAULT_WIDTH - 200, 0, "New Workspace", evt -> {
      WindowControl window = new WindowControl(new Stage());
    });
    Button turtleDetailButton = makeButton(DEFAULT_WIDTH - 400, 0, "Turtle Details",
        e -> turtleDetailsView.showView());
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
      errorView.updateMessage("Invalid command.");
      errorView.showView();
    }
  }
  
  private void uploadXML() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload XML");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("XML Files", "*.xml"));
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      try {
        String path = "data/UserUploaded.xml";
        boolean isMoved = file.renameTo(new File(path));
        fromXML.readFile(path);
      } catch (IOException | SAXException | ParserConfigurationException e) {
        errorView.showView();
      }
    }
  }

  /**
   * Assumes user will not use ctrl key.
   */
  private void setUpKeyInput() {
    try {
      Class<?> keyHandlerClass = Class.forName("slogo.frontend.KeyInputHandler");
      KeyInputHandler keyHandler = new KeyInputHandler();
      keyHandler.addMultipleListeners(getAllTurtleListeners());
      root.setOnKeyPressed(evt -> {
        try {
          Method moveMethod = keyHandlerClass.getMethod("press" + evt.getCode().toString());
          moveMethod.invoke(keyHandler);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
          errorView.updateMessage("Invalid key button. Try using WASD, and RL.");
          errorView.showView();
        }
      });
    } catch (ClassNotFoundException e) {
      errorView.updateMessage("Fatal error: KeyInputHandler class not found.");
      errorView.showView();
    }
  }

  private List<PropertyChangeListener> getAllTurtleListeners() {
    List<PropertyChangeListener> turtleListeners = new ArrayList<>();

    for (Turtle turtle : myController.getTurtleHandler().getActiveTurtles()) {
      turtleListeners.addAll(turtle.getListeners());
    }

    return turtleListeners;
  }
}
