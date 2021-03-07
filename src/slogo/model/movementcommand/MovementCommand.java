package slogo.model.movementcommand;

abstract class MovementCommand {

  private int deltaX;
  private int deltaY;
  private double orientationDirection;
  private static final double NEGLIGIBLE_DIFFERENCE = .0001;

  protected double[] determineLocation(double[] currentLocation, double orientation, int pixels) {
    findQuadrantDirection(orientation);
    double xDiff = isZero(Math.cos(orientationDirection)) ? 0 : Math.cos(orientationDirection);
    double yDiff = isZero(Math.sin(orientationDirection)) ? 0 : Math.sin(orientationDirection);
    return new double[]{round(deltaX * pixels * xDiff), round(deltaY * pixels * yDiff)};
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

  private double round(double value) {
    return Math.round(value * 100.0) / 100.0;
  }
}
