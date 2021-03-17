package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
  protected void setUpRoot(Group myRoot, double sizeX, double sizeY) {
    Text errorText = new Text(sizeX / 2, sizeY / 2, "Invalid command.");
    errorText.setFill(Color.BLACK);
    myRoot.setId("errorMessage");
    myRoot.getChildren().add(errorText);
  }
}
