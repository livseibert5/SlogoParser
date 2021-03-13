package slogo.model.movementcommand;

/**
 * MovementCommand provides helpful functionality to the Forward, Backward, Left, and Right
 * functions by taking care of the trig functions that each of these classes uses.
 *
 * @author Livia Seibert and Rachel Luria
 */
abstract class MovementCommand {

  private int deltaX = 1;
  private int deltaY = 1;
  private double orientationDirection;

  private static final double NEGLIGIBLE_DIFFERENCE = .0001;
  private static final double ROUNDING_CONSTANT = 100.0;

  private static final double RADIAN_90 = Math.PI / 2;
  private static final double RADIAN_180 = Math.PI;
  private static final double RADIAN_270 = (3 / 2) * Math.PI;
  private static final double RADIAN_360 = 2 * Math.PI;

  /**
   * Finds new end location for turtle based on current location, orientation, and number of pixels
   * to move.
   *
   * @param currentLocation current x and y position of turtle
   * @param orientation     current orientation of turtle in radians
   * @param pixels          number of pixels to move
   * @return new coordinates of turtle
   */
  protected double[] determineLocation(double[] currentLocation, double orientation,
      double pixels) {
    findQuadrantDirection(orientation);
    double xDiff = isZero(Math.cos(orientationDirection)) ? 0 : Math.cos(orientationDirection);
    double yDiff = isZero(Math.sin(orientationDirection)) ? 0 : Math.sin(orientationDirection);
    return new double[]{round(currentLocation[0] + deltaX * pixels * xDiff),
        round(currentLocation[1] + deltaY * pixels * yDiff)};
  }

  /**
   * Determines which quadrant the current orientation of the turtle is in so that the delta of x
   * and y can be calculated in the correct direction.
   *
   * @param orientation current orientation of turtle in radians
   */
  protected void findQuadrantDirection(double orientation) {
    if (orientation >= 0 && orientation < RADIAN_90) {
      orientationDirection = orientation;
    } else if (orientation >= RADIAN_90 && orientation < RADIAN_180) {
      deltaX = -1;
      orientationDirection = RADIAN_180 - orientation;
    } else if (orientation >= RADIAN_180 && orientation < RADIAN_270) {
      deltaX = -1;
      deltaY = -1;
      orientationDirection = RADIAN_270 - orientation;
    } else {
      deltaY = -1;
      orientationDirection = RADIAN_360 - orientation;
    }
  }

  /**
   * Determines if value is close enough to 0 to be considered zero.
   *
   * @param value value to compare to zero
   * @return true if value is almost 0, otherwise false
   */
  private boolean isZero(double value) {
    return Math.abs(value - 0) < NEGLIGIBLE_DIFFERENCE;
  }

  /**
   * Rounds double values to 2 decimal places.
   *
   * @param value double to be rounded
   * @return value rounded to 2 decimals
   */
  private double round(double value) {
    return Math.round(value * ROUNDING_CONSTANT) / ROUNDING_CONSTANT;
  }
}
