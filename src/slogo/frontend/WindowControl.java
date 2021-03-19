package slogo.frontend;

import java.io.File;
import javafx.scene.Group;
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

  private final Group root = new Group();
  private final Stage stage = new Stage();

  private final Parser myParser;
  private final TurtleDisplay myTurtleDisplay;
  private final SceneComponents myComponents;
  private final String DEFAULT_IMAGE_PATH = "/" + (TurtleDisplay.class.getPackageName() + ".resources.images.").replace('.', '/');
  private int imageNumber;
  private final String USER_FILE = DEFAULT_IMAGE_PATH + "UserImage.jpg";

  private final ViewMaker errorWindow = new ErrorView(200, 200);

  /**
   * Constructor for WindowControl class. Returns WindowControl object.
   */
  public WindowControl(Stage myStage) {
    CreateScene myScene = new CreateScene(myStage, root);
    Controller myController = new Controller();
    myParser = new Parser(myController);
    TableDisplay myTableDisplay = new TableDisplay(myController.getVariableHandler(),
        myController.getUserDefinedCommandHandler(), root);
    myTurtleDisplay = new TurtleDisplay(myController.getTurtleHandler(), root);

    myComponents = new SceneComponents(root, myTurtleDisplay.getListeners());

    createUploadButton();
    HelpButtonMaker helpButton = new HelpButtonMaker("Help", HELP_X, HELP_Y, root);
    createEnterButton();

  }

  private void createUploadButton() {
    uploadButton = new UploadButtonMaker("Upload Image", UPLOAD_X, UPLOAD_Y, root, new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
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
    });
  }

  private void createEnterButton() {
    EnterButtonMaker enterButton = new EnterButtonMaker("Enter", ENTER_X, ENTER_Y, root,
        event -> {
          try {
            int value = myParser.parse(myComponents.getTextInput());
            myTurtleDisplay.updateTurtleView(1);
            myComponents.clearTextInput();
            myComponents.printReturnValue(value);
          } catch (Exception e) {
            errorWindow.showView();
          }
        });
  }
}
