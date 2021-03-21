package slogo.frontend;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
public class WindowControl extends ViewMaker {
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

  private final Group root = new Group();

  private final Controller myController;
  private final TableDisplay myTableDisplay;
  private CommandField myCommand;

  private final Parser myParser;
  private final TurtleDisplay myTurtleDisplay;

  private ErrorView errorView;
  private ViewMaker turtleDetailsView;
  private ViewMaker helpView;
  private ImageCustomizeView myCustomizer;


  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    super(DEFAULT_WIDTH, DEFAULT_HEIGHT, "Slogo");
    myController = new Controller();
    myParser = new Parser(myController);
    myTableDisplay = new TableDisplay(myController.getVariableHandler(), myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(root, myController.getTurtleHandler(), myController.getColorHandler());
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
    LanguageDropDown dropDown = new LanguageDropDown(root, myController);
    setUpViews();
    setUpPreferences();
    setUpButtons();
    setUpKeyInput();
    getRoot().setCenter(root);
    showView();
  }

  private void setUpViews() {
    TurtleWindow myTurtleWindow = new TurtleWindow(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    myCustomizer = new ImageCustomizeView(WINDOW_SIZE, WINDOW_SIZE, "Customize Colors and Images", myTurtleDisplay, myTurtleWindow);
    errorView = new ErrorView(200, 200);
    helpView = new HelpView(HELP_WIDTH, HELP_HEIGHT);
    turtleDetailsView = new TurtleDetailsView(400, 400, myController.getTurtleHandler());

    List<PropertyChangeListener> viewListeners = new ArrayList<>();
    viewListeners.add(myCustomizer.getListener());
    viewListeners.add(errorView.getListener());
    viewListeners.add(helpView.getListener());
    viewListeners.add(turtleDetailsView.getListener());

    addMultipleListeners(viewListeners);
  }

  private void setUpButtons() {
    ToXML toXML = new ToXML(myController);
    addButton(HELP_X + 150, HELP_Y, "Save as XML", event -> {
      try {
        toXML.exportToXML();
      } catch (ParserConfigurationException | TransformerException e) {
        e.printStackTrace();
      }
    });
    addButton(HELP_X - 200, HELP_Y, "Upload XML File", event -> uploadXML());
    addButton(UPLOAD_X, UPLOAD_Y, "Customize", event -> myCustomizer.showView());
    addButton(HELP_X + 50, HELP_Y, "Help", evt -> helpView.showView());
    addButton(ENTER_X, ENTER_Y, "Enter", event -> enterEvent());
    addButton(DEFAULT_WIDTH - 200, 0, "New Workspace", evt -> {
      WindowControl window = new WindowControl(new Stage());
    });
    addButton(DEFAULT_WIDTH - 400, 0, "Turtle Details",
        e -> turtleDetailsView.showView());
  }

  private void addButton(double x, double y, String title, EventHandler<ActionEvent> evt) {
    Button newButton = makeButton(x, y, title);
    newButton.setOnAction(evt);
    root.getChildren().add(newButton);
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
    FromXML fromXML = new FromXML(myController);
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload XML");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("XML Files", "*.xml"));
    //File file = fileChooser.showOpenDialog(stage);
    File file = fileChooser.showOpenDialog(getStage());
    if (file != null) {
      try {
        fromXML.readFile(file.getName());
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

  private void setUpPreferences() {
    List<String> results = new ArrayList<>();
    File[] files = new File("src/slogo/frontend/resources/styles").listFiles();
    assert files != null;
    for (File file : files) {
      if (file.isFile()) {
        results.add(file.getName().substring(0, file.getName().lastIndexOf('.')));
      }
    }
    ComboBox<String> prefs = new ComboBox<>(FXCollections.observableList(results));
    prefs.setOnAction(event -> {
      //sceneMaker.changeStyle(prefs.getValue());
      changeStyle(prefs.getValue());
    });
    prefs.setValue("Default");
    prefs.relocate(300,0);
    root.getChildren().add(prefs);
  }
}
