package slogo.model;

/**
 * Variable class stores the rbg value of a color.
 *
 * @author Rachel Luria
 */
public class Color {

  private final double r;
  private final double g;
  private final double b;
  private final double MAX_RGB = 255.0;

  /**
   * Constructor to assign the r,g,b values of the color
   *
   * @param r value in the rgb scale
   * @param g value in the rgb scale
   * @param b value in the rgb scale
   */
  public Color(double r, double g, double b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the red value in the rgb scale
   *
   * @return r value
   */
  public double getR() {
    return r / MAX_RGB;
  }

  /**
   * Gets the green value in the rgb scale
   *
   * @return g value
   */
  public double getG() {
    return g / MAX_RGB;
  }

  /**
   * Gets the blue value in the rgb scale
   *
   * @return b scale
   */
  public double getB() {
    return b / MAX_RGB;
  }

}
