package slogo.frontend;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
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
  private CommandField myCommand;
  private String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private int imageNumber = 1;
  private String USER_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private ViewMaker errorWindow = new ErrorView(200, 200);
  private ViewMaker turtleDetailsWindow;

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    myScene = new CreateScene(myStage, root);
    myController = new Controller();
    myParser = new Parser(myController);
    myTableDisplay = new TableDisplay(myController.getVariableHandler(), myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(root, myController.getTurtleHandler(), myController.getColorHandler());

    myCommand = new CommandField(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_HEIGHT);
    //myComponents = new SceneComponents(root, myTurtleDisplay.getListeners());
    uploadButton = new UploadButtonMaker("Upload Image", UPLOAD_X, UPLOAD_Y, root, event -> uploadEvent());
    helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root, event -> enterEvent());
    LanguageDropDown dropDown = new LanguageDropDown(root, myController);
    TurtleWindow myTurtleWindow = new TurtleWindow(root, WINDOW_SIZE, DEFAULT_BORDER, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    ColorPickerMaker backgroundColor = new ColorPickerMaker(root, DEFAULT_WIDTH - WINDOW_SIZE - DEFAULT_BORDER, DEFAULT_BORDER / 3, "Background");
    backgroundColor.setHandler(event -> myTurtleWindow.setColor(backgroundColor.getNewColor()));
    ColorPickerMaker penColor = new ColorPickerMaker(root, DEFAULT_WIDTH - WINDOW_SIZE/2 - DEFAULT_BORDER, DEFAULT_BORDER/3, "Pen");
    penColor.setHandler(event -> myTurtleDisplay.setLineColor(penColor.getNewColor()));
    WorkspaceButtonMaker newButton = new WorkspaceButtonMaker("New Workspace", DEFAULT_WIDTH - 200, 0, root);
    turtleDetailsWindow = new TurtleDetailsView(400, 400, myController.getTurtleHandler());
    Button turtleDetailButton = makeButton(HELP_X + 100, HELP_Y, "Turtle Details",
        e -> turtleDetailsWindow.showView());
  }

  private Button makeButton(double x, double y, String title, EventHandler<ActionEvent> evt) {
    Button newButton = new Button(title);
    newButton.relocate(x, y);
    newButton.setId(title.replaceAll(" ", ""));
    newButton.setOnAction(evt);
    root.getChildren().add(newButton);
    return newButton;
  }

  private void uploadEvent() {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Upload Turtle Image");
    fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"));
    File file = fileChooser.showOpenDialog(stage);
    if (file != null) {
      boolean isMoved = file.renameTo(new File("src/slogo/frontend/resources/images/UserImage" + imageNumber + ".jpg"));
      System.out.println(isMoved);
      myTurtleDisplay.updateImageView(imageNumber);
      imageNumber++;
    }
  }

  private void enterEvent() {
    try {
      int value = myParser.parse(myCommand.getTextInput());
      myTurtleDisplay.updateTurtleView(myController.getTurtleHandler().getActiveTurtles());
      myCommand.clearTextInput();
      myCommand.printReturnValue(value);
    } catch (Exception e) {
      errorWindow.showView();
    }
  }
}
