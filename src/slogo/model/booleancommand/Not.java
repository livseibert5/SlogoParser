package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

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
  public int execute(Turtle turtle) {
    return this.argument == 0 ? 1 : 0;
  }
}
