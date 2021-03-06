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
    return 0.0;
  }

  public int[] getLocation() {
    return new int[]{0, 0};
  }

  public void setOrientation(double orientation) {
    this.orientation = orientation;
  }

  public int getXCoordinate() {
    return xCoordinate;
  }

  public int getYCoordinate() {
    return yCoordinate;
  }
}
