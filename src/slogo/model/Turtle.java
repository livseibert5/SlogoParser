package slogo.model;

public class Turtle {

  private int xCoordinate;
  private int yCoordinate;
  private double orientation;

  public Turtle() {
    xCoordinate = 0;
    yCoordinate = 0;
    orientation = 90.0;
  }

  public double getOrientation() {
    return orientation;
  }

  public int[] getLocation() {
    return new int[]{xCoordinate, yCoordinate};
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

  public void setLocation(int[] newLocation) {
    xCoordinate = newLocation[0];
    yCoordinate = newLocation[1];
  }

  public int getXCoordinate() {
    return xCoordinate;
  }

  public int getYCoordinate() {
    return yCoordinate;
  }
}
