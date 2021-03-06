package slogo.model;

public class Turtle {

  private int xCoordinate;
  private int yCoordinate;
  private double orientation;

  public Turtle() {
    xCoordinate = 0;
    yCoordinate = 0;
    orientation = Math.PI / 2;
  }

  public double getOrientation() {
    return orientation;
  }

  public int[] getLocation() {
    return new int[]{xCoordinate, yCoordinate};
  }

  public void setOrientation(double orientation) {
    this.orientation = orientation;
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
