package slogo.model;

/**
 * Variable class stores the rbg value of a color.
 *
 * @author Rachel Luria
 */
public class Color {

  private int r;
  private int g;
  private int b;

  public Color(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  };

  public int getR() {return r;}

  public int getG() {return g;}

  public int getB() {return b;}

}
