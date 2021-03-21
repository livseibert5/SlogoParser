package slogo.frontend;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Creates basic window that can be extended.
 * TODO assumptions, dependencies, example of use
 *
 * @author Jessica Yang
 */
public abstract class ViewMaker {

  private final Stage viewStage = new Stage();
  private final BorderPane viewRoot = new BorderPane();
  public static final String DEFAULT_RESOURCE_PACKAGE = "slogo.frontend.resources.";
  public static final String DEFAULT_RESOURCE_FOLDER =
      "/" + DEFAULT_RESOURCE_PACKAGE.replace(".", "/" );
  public static final String STYLESHEET = "default.css";

  /**
   * Constructor for ViewMaker abstract class. Assumes setUpRoot is implemented in child classes.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   * @param title string title of window
   */
  public ViewMaker(double sizeX, double sizeY, String title) {
    Scene viewScene = new Scene(viewRoot, sizeX, sizeY);
    viewScene.getStylesheets().add(getClass().getResource(DEFAULT_RESOURCE_FOLDER + STYLESHEET).toExternalForm());
    viewStage.setTitle(title);
    viewStage.setScene(viewScene);
  }

  /**
   * Used by child-classes to access class root.
   *
   * @return viewRoot to be added to
   */
  protected BorderPane getRoot() {
    return viewRoot;
  }

  /**
   * Text object maker and formatter for child classes.
   *
   * @param xCoordinate x location
   * @param yCoordinate y location
   * @param text content of text
   * @param id text id
   * @return newText Text object
   */
  protected Text makeText(double xCoordinate, double yCoordinate, String text, String id) {
    Text newText = new Text(xCoordinate, yCoordinate, text);
    newText.setFill(Color.BLACK);
    newText.setId(id);

    return newText;
  }

  /**
   * Button object maker and formatter for child classes.
   *
   * @param xCoordinate x location
   * @param yCoordinate y location
   * @param text content of text
   * @return newButton Button object
   */
  protected Button makeButton(double xCoordinate, double yCoordinate, String text) {
    Button newButton = new Button(text);
    newButton.relocate(xCoordinate, yCoordinate);
    newButton.setId(text);
    return newButton;
  }

  /**
   * Displays window.
   */
  public void showView() {
    viewStage.show();
  }
}
