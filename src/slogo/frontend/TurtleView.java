package slogo.frontend;

import java.beans.PropertyChangeListener;
import javafx.scene.Group;

/**
 * View for current state of a turtle.
 *
 * @author Jessica Yang
 */
public class TurtleView extends ViewMaker {

  private PropertyChangeListener lineColorListener;

  /**
   * Constructor for TurtleView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public TurtleView(int sizeX, int sizeY) {
    super(sizeX, sizeY, "Turtle Details");
  }

  /**
   * Adds turtle details to root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  @Override
  protected void setUpRoot(Group myRoot, double sizeX, double sizeY) {

  }
}
