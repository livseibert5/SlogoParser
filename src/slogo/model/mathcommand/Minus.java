package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * Minus is the Command subclass that finds the minus of a given input.
 *
 * @author Rachel Luria
 */
public class Minus implements Command {

  private final double argument;

  /**
   * Constructor for the Minus command, takes a constant as its argument and gets its value by
   * executing a constant command
   *
   * @param argument Constant that the minus gets taken of
   */
  public Minus(Value argument) {
    this.argument = argument.getValue();
  }

  /**
   * Finds the minus of the argument
   *
   * @param turtle turtle object to execute command on
   * @return minus of the arg
   */
  @Override
  public double execute(Turtle turtle) {
    if (argument == 0) {
      return 0;
    } else {
      return -1 * argument;
    }
  }
}