package slogo.frontend;

import javafx.scene.Group;

/**
 * Class for error pop up window.
 *
 * @author Jessica Yang
 */
public class ErrorView extends ViewMaker {

  private String errorMessage;

  /**
   * Constructor for ErrorView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public ErrorView(double sizeX, double sizeY) {
    super(sizeX, sizeY, "Error");
    setUpRoot(getRoot(), sizeX, sizeY);
  }

  /**
   * Adds text to the root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  private void setUpRoot(Group myRoot, double sizeX, double sizeY) {
    myRoot.getChildren().add(makeText(sizeX / 3, sizeY / 3, errorMessage,
        "errorMessage"));
  }

  /**
   * Allows error message to be customized for specific events.
   *
   * @param message specific string message
   */
  public void setMessage(String message) {
    errorMessage = message;
  }
}
