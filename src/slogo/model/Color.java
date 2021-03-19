package slogo.model;

/**
 * Variable class stores the rbg value of a color.
 *
 * @author Rachel Luria
 */
public class Color {

  private final int r;
  private final int g;
  private final int b;

  /**
   * Constructor to assign the r,g,b values of the color
   *
   * @param r value in the rgb scale
   * @param g value in the rgb scale
   * @param b value in the rgb scale
   */
  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  /**
   * Gets the red value in the rgb scale
   *
   * @return r value
   */
  public int getR() {return r;}

  /**
   * Gets the green value in the rgb scale
   *
   * @return g value
   */
  public int getG() {return g;}

  /**
   * Gets the blue value in the rgb scale
   * @return b scale
   */
  public int getB() {return b;}

}
