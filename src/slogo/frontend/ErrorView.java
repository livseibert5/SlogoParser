package slogo.frontend;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;

/**
 * Class for error pop up window.
 *
 * @author Jessica Yang
 */
public class ErrorView extends ViewMaker {

  private Text messageText;

  /**
   * Constructor for ErrorView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public ErrorView(double sizeX, double sizeY) {
    super(sizeX, sizeY, "Error");
    messageText = makeText(10, sizeY / 3, null,
        "errorMessage");
    messageText.setWrappingWidth(sizeX - 20);
    setUpRoot(getRoot(), sizeX, sizeY);
  }

  /**
   * Adds text to the root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  private void setUpRoot(BorderPane myRoot, double sizeX, double sizeY) {
    myRoot.getChildren().add(messageText);
  }

  /**
   * Allows error message to be customized for specific events.
   *
   * @param message specific string message
   */
  public void updateMessage(String message) {
    messageText.setText(message);
  }
}
