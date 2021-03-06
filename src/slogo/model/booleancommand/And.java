package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class And implements Command {

  int argument1;
  int argument2;

  public And(Constant argument1, Constant argument2) {
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 0;
  }

  @Override
  public int execute(Turtle turtle) {
    return this.argument1 != 0 && this.argument2 != 0 ? 1 : 0;
  }
}
