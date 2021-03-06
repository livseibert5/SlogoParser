package slogo.model.booleancommand;

import slogo.model.Command;
import slogo.model.Turtle;

public class LessThan implements Command {

  Command argument1;
  Command argument2;

  public LessThan(Command argument1, Command argument2) {
    this.argument1 = argument1;
    this.argument2 = argument2;
  }

  @Override
  public int getNumberParameters() {
    return 0;
  }

  @Override
  public int execute(Turtle turtle) {
    if (this.argument1.execute(turtle) < this.argument2.execute(turtle)) {
      return 1;
    } else {
      return 0;
    }
  }
}
