package slogo.model;

public class Turtle {

  private double xCoordinate;
  private double yCoordinate;
  private double orientation;
  private boolean penDown;
  private boolean isShowing;

  public Turtle() {
    xCoordinate = 0;
    yCoordinate = 0;
    orientation = 90.0;
  }

  public double getOrientation() {
    return orientation;
  }

  public double[] getLocation() {
    return new double[]{xCoordinate, yCoordinate};
  }

  public void setOrientation(double orientation) {
    if (orientation > 360) {
      this.orientation = orientation - 360;
    } else if (orientation < 0) {
      this.orientation = 360 + orientation;
    } else {
      this.orientation = orientation;
    }
  }

  public void setLocation(double[] newLocation) {
    xCoordinate = newLocation[0];
    yCoordinate = newLocation[1];
  }

  public boolean penIsDown() {
    return penDown;
  }

  public boolean isShowing() {
    return isShowing;
  }

  public double getXCoordinate() {
    return xCoordinate;
  }

  public double getYCoordinate() {
    return yCoordinate;
  }
}
