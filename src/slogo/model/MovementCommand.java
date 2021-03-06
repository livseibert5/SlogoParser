package slogo.model;

abstract class MovementCommand {

  private int deltaX;
  private int deltaY;
  private double orientationDirection;

  protected int[] determineLocation(int[] currentLocation, double orientation, int pixels) {
    findQuadrantDirection(orientation);
    return new int[]{(int) (deltaX * pixels * Math.cos(orientationDirection)),
        (int) (deltaY * pixels * Math.sin(orientationDirection))};
  }

  protected void findQuadrantDirection(double orientation) {
    if (orientation >= 0 && orientation < 90) {
      deltaX = 1;
      deltaY = 1;
      orientationDirection = orientation;
    } else if (orientation >= 90 && orientation < 180) {
      deltaX = -1;
      deltaY = 1;
      orientationDirection = 180 - orientation;
    } else if (orientation >= 180 && orientation < 270) {
      deltaX = -1;
      deltaY = -1;
      orientationDirection = 270 - orientation;
    } else {
      deltaX = 1;
      deltaY = -1;
      orientationDirection = 360 - orientation;
    }
  }
}
