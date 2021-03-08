package slogo.model;

/**
 * Constant holds an integer value to be used as a parameter for
 * a Command type.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class Constant extends Expression {

  private int value;

  /**
   * Constructor for Constant takes in an integer value.
   *
   * @param value integer value of the constant
   */
  public Constant(double value) {
    super(value);
  }

  /**
   * Allows access to value of Constant.
   *
   * @return integer value of the constant
   */
  //public double getValue() {
    //return value;
  //}
}
