package slogo.frontend;

import javafx.scene.Group;
import slogo.model.Turtle;

/**
 * View for current state of a turtle.
 *
 * @author Jessica Yang
 */
public class TurtleView extends ViewMaker {

  private final Turtle myTurtle;

  /*
  private final PropertyChangeListener turtleXCoordinateListener;
  private final PropertyChangeListener turtleYCoordinateListener;
  private final PropertyChangeListener turtleOrientationListener;
  private final PropertyChangeListener penColorListener;
  private final PropertyChangeListener penUpListener;
  */

  /**
   * Constructor for TurtleView class.
   *
   * @param sizeX x dimension of window
   * @param sizeY y dimension of window
   */
  public TurtleView(int sizeX, int sizeY, Turtle turtle) {
    super(sizeX, sizeY, "Turtle Details");
    myTurtle = turtle;

  }

  /*
  private void setUpListeners() {
    turtleXCoordinateListener = evt -> {
      if (evt.getPropertyName().equals("xLocation")) {

      }
    }
  }

  private void addAllListeners() {
    //myTurtle.addListener();
  }
  */

  /**
   * Adds turtle details to root.
   *
   * @param myRoot to be added to
   * @param sizeX width of the window
   * @param sizeY height of the window
   */
  @Override
  protected void setUpRoot(Group myRoot, double sizeX, double sizeY) {
    //pencolor, location, orientation, up/down, thickness?
    String xLocation = Double.toString(myTurtle.getLocation()[0]);
    String yLocation = Double.toString(myTurtle.getLocation()[1]);
    String orientation = Double.toString(myTurtle.getOrientation());
    String penDown = Boolean.toString(myTurtle.penIsDown());

    myRoot.getChildren().add(makeText(sizeX / 4, sizeY / 4, xLocation,
        "xLocation"));
    myRoot.getChildren().add(makeText(sizeX / 4, sizeY * (3 / 4.0), yLocation,
        "yLocation"));
    myRoot.getChildren().add(makeText(sizeX / 4, sizeY / 2, orientation,
        "orientation"));
    myRoot.getChildren().add(makeText(sizeX / 4, sizeY * (3.0 / 4), penDown,
        "penDown"));
  }
}
