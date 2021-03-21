package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.Scene;
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

<<<<<<< HEAD
  private final Stage viewWindow = new Stage();
  private final Group viewRoot = new Group();
=======
  private BorderPane viewRoot = new BorderPane();
  private Scene viewScene;
  private Stage viewWindow = new Stage();
>>>>>>> mal115

  /**
   * Constructor for ViewMaker abstract class. Assumes setUpRoot is implemented in child classes.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   * @param title string title of window
   */
  public ViewMaker(double sizeX, double sizeY, String title) {
    Scene viewScene = new Scene(viewRoot, sizeX, sizeY);
    viewWindow.setTitle(title);
    viewWindow.setScene(viewScene);
  }

  public ViewMaker(double sizeX, double sizeY, String title, TurtleDisplay turtles) {
    viewScene = new Scene(viewRoot, sizeX, sizeY);
    setUpRoot(viewRoot, sizeX, sizeY);
    viewWindow.setTitle(title);
    viewWindow.setScene(viewScene);
  }

  /**
   * Used by child-classes to access class root.
   *
   * @return viewRoot to be added to
   */
<<<<<<< HEAD
  protected Group getRoot() {
    return viewRoot;
  }
=======
  protected abstract void setUpRoot(BorderPane myRoot, double sizeX, double sizeY);
>>>>>>> mal115

  /**
   * Text object maker and formatter for child classes.
   *
   * @param xCoordinate width of text
   * @param yCoordinate height of text
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
   * Displays window.
   */
  public void showView() {
    viewWindow.show();
  }
}
