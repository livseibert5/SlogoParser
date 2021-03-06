package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

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
  public int execute(Turtle turtle) {
    return this.argument1 < this.argument2 ? 1 : 0;
  }
}
