package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Not class is the Command subclass that returns 1 if the argument is 0
 * and returns 0 if the argument is non-zero.
 *
 * @author Livia Seibert
 */
public class Not implements Command {

  int argument;

  public Not(Constant argument) {
    this.argument = argument.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 1;
  }

  @Override
  public double execute(Turtle turtle) {
    return this.argument == 0 ? 1 : 0;
  }
}
