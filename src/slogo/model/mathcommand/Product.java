package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Product is the Command subclass that finds the product of given two inputs.
 *
 * @author Rachel Luria
 */
public class Product implements Command{

  private final double argument1;
  private final double argument2;

  /**
   * Constructor for the Power command, takes two constants as its arguments and
   * gets their values by executing a constant command
   *
   * @param argument1 number being multiplied
   * @param argument2 number being multiplied
   */
  public Product(Value argument1, Value argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  /**
   * Finds the product of two arguments
   *
   * @param turtle turtle object to execute command on
   * @return product
   */
  @Override
  public double execute(Turtle turtle) {
    return argument1 * argument2;
  }
}