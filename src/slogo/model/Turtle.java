package slogo.model;

import slogo.Observable;

/**
 * Turtle is the class that holds the data for the location and orientation of turtle objects so
 * they can be displayed and moved across the screen.
 */
public class Turtle extends Observable<Object> {

  private double xCoordinate;
  private double yCoordinate;
  private double orientation;
  private boolean penDown;
  private boolean isShowing;
  private double penColor;
  private double penThickness;
  private double penShape;
  private static final int TURTLE_BOUNDARY = 300;

  /**
   * Constructor for Turtle object, puts turtle in the middle of the canvas and sets its orientation
   * to 90.
   */
  public Turtle() {
    xCoordinate = 0;
    yCoordinate = 0;
    orientation = 90.0;
    isShowing = true;
    penColor = 0;
    penShape = 0;
  }

  /**
   * Allows Command classes to set a new orientation for the turtle. Makes sure this new orientation
   * is positive and less than 360.
   *
   * @param orientation new turtle orientation in degrees
   */
  public void setOrientation(double orientation) {
    if (orientation > 360) {
      this.orientation = orientation - 360;
    } else if (orientation < 0) {
      this.orientation = 360 + orientation;
    } else {
      this.orientation = orientation;
    }
  }

  /**
   * Allows Command classes to set new location for turtle with a 2-D array.
   *
   * @param newLocation x-coordinate and y-coordinate of new location for turtle
   */
  public void setLocation(double[] newLocation) {
    double prevX = xCoordinate;
    xCoordinate = newLocation[0] > TURTLE_BOUNDARY ? TURTLE_BOUNDARY : newLocation[0];
    xCoordinate = newLocation[0] < -1 * TURTLE_BOUNDARY ? -1 * TURTLE_BOUNDARY : xCoordinate;
    notifyListeners("xLocation", prevX, xCoordinate);
    double prevY = yCoordinate;
    yCoordinate = newLocation[1] > TURTLE_BOUNDARY ? TURTLE_BOUNDARY : newLocation[1];
    yCoordinate = newLocation[1] < -1 * TURTLE_BOUNDARY ? -1 * TURTLE_BOUNDARY : yCoordinate;
    notifyListeners("yLocation", prevY, yCoordinate);
  }

  /**
   * Allows access to the orientation of the turtle in degrees for the front end and the test
   * cases.
   *
   * @return orientation of turtle in degrees
   */
  public double getOrientation() {
    return orientation;
  }

  /**
   * Allows access to the location of the turtle as an array for the front end and test cases.
   *
   * @return array with current location og turtle
   */
  public double[] getLocation() {
    return new double[]{xCoordinate, yCoordinate};
  }

  /**
   * Allows access to state of the turtle's pen.
   *
   * @return true if pen is down, otherwise false
   */
  public boolean penIsDown() {
    return penDown;
  }

  /**
   * Allows access to whether or not turtle is shown on screen.
   *
   * @return true if turtle is visible, otherwise false
   */
  public boolean isShowing() {
    return isShowing;
  }

  /**
   * Allows access to x-coordinate of turtle's current location.
   *
   * @return x-coordinate of turtle's current location
   */
  public double getXCoordinate() {
    return xCoordinate;
  }

  /**
   * Allows access to y-coordinate of turtle's current location.
   *
   * @return y-coordinate of turtle's current location
   */
  public double getYCoordinate() {
    return yCoordinate;
  }

  /**
   * Puts the turtle's pen down by setting the penDown boolean to true.
   */
  public void setPenDown() {
    notifyListeners("penDown", penDown, true);
    penDown = true;
  }

  /**
   * Puts the turtle's pen up by setting the penDown boolean to false.
   */
  public void setPenUp() {
    notifyListeners("penDown", penDown, false);
    penDown = false;
  }

  /**
   * Allows the turtle to be displayed by setting isShowing to true so the front end knows to show
   * it.
   */
  public void showTurtle() {
    notifyListeners("isShowing", isShowing, true);
    isShowing = true;
  }

  /**
   * Hides the turtle from the canvas by setting isShowing to false so the front end knows not to
   * display it.
   */
  public void hideTurtle() {
    notifyListeners("isShowing", isShowing, false);
    isShowing = false;
  }

  /**
   * Allows the turtle's pen color to be changed.
   *
   * @param colorIndex index of new pen color
   */
  public void setPenColor(double colorIndex) {
    notifyListeners("penColor", penColor, colorIndex);
    penColor = colorIndex;
  }

  /**
   * Allows access to the turtle's current pen color.
   *
   * @return index of current pen color
   */
  public double getPenColor() {
    return penColor;
  }

  /**
   * Allows the turtle's pen thickness to be changed.
   *
   * @param thickness thickness in pixels of turtle's pen
   */
  public void setPenThickness(double thickness) {
    notifyListeners("penThickness", penThickness, thickness);
    penThickness = thickness;
  }

  /**
   * Allows access to the current thickness of the turtle's pen.
   *
   * @return thickness in pixels of turtle's pen
   */
  public double getPenThickness() {
    return penThickness;
  }

  /**
   * Allows access to the pen's shape to be changed.
   *
   * @param value index of the new pen shape
   */
  public void setPenShape(double value) {
    notifyListeners("penShape", penShape, value);
    penShape = value;
  }
}
