package slogo.model.movementcommand;

abstract class MovementCommand {

  private int deltaX;
  private int deltaY;
  private double orientationDirection;
  private static final double NEGLIGIBLE_DIFFERENCE = .0001;

  protected int[] determineLocation(int[] currentLocation, double orientation, int pixels) {
    findQuadrantDirection(orientation);
    int xDiff = isZero(Math.cos(orientationDirection)) ? 0 : (int) Math.cos(orientationDirection);
    int yDiff = isZero(Math.sin(orientationDirection)) ? 0 : (int) Math.sin(orientationDirection);
    return new int[]{deltaX * pixels * xDiff, deltaY * pixels * yDiff};
  }

  protected void findQuadrantDirection(double orientation) {
    if (orientation >= 0 && orientation < Math.PI / 2) {
      deltaX = 1;
      deltaY = 1;
      orientationDirection = orientation;
    } else if (orientation >= Math.PI / 2 && orientation < Math.PI) {
      deltaX = -1;
      deltaY = 1;
      orientationDirection = Math.PI - orientation;
    } else if (orientation >= Math.PI && orientation < (3/2) * Math.PI) {
      deltaX = -1;
      deltaY = -1;
      orientationDirection = (3/2) * Math.PI - orientation;
    } else {
      deltaX = 1;
      deltaY = -1;
      orientationDirection = 2 * Math.PI - orientation;
    }
  }

  private boolean isZero(double value) {
    return Math.abs(value - 0) < NEGLIGIBLE_DIFFERENCE;
  }
}
