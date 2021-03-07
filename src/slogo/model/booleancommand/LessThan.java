package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * LessThan class is the Command subclass that returns 1 if the first argument
 * is less than the second argument, and returns 0 otherwise.
 *
 * @author Livia Seibert
 */
public class LessThan implements Command {

  int argument1;
  int argument2;
  
  public LessThan(Constant argument1, Constant argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 2;
  }

  @Override
  public double execute(Turtle turtle) {
    return this.argument1 < this.argument2 ? 1 : 0;
  }
}
