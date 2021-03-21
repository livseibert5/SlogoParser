package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

/**
 * Class for error pop up window.
 *
 * @author Jessica Yang
 */
public class ErrorView extends ViewMaker {

  /**
   * Constructor for ErrorView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public ErrorView(double sizeX, double sizeY) {
    super(sizeX, sizeY, "Error");
  }

  /**
   * Adds text to the root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  @Override
  protected void setUpRoot(BorderPane myRoot, double sizeX, double sizeY) {
    myRoot.getChildren().add(makeText(sizeX / 3, sizeY / 3, "Invalid command.",
            "errorMessage"));
  }
}
