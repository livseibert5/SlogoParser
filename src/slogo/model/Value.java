package slogo.model;

/**
 * Value class is the super type for Constant and Variable. Since both Constant and Variable can be
 * passed to functions as arguments and both have a double value, they are both Value types.
 *
 * @author Livia Seibert
 */
public abstract class Value {

  private double value;

  /**
   * Value constructor assigns a double value to the object.
   *
   * @param value double value of object
   */
  public Value(double value) {
    this.value = value;
  }

  /**
   * Allows access to assigned value.
   *
   * @return value of Value object
   */
  public double getValue() {
    return value;
  }

  /**
   * Allows value to be set by parser.
   *
   * @param value double to set value variable to
   */
  public void setValue(double value) {
    this.value = value;
  }
}
