package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Pi implements Command{

  @Override
  public int getNumberParameters() {
    return 0;
  }

  @Override
  public int execute(Turtle turtle) {
    return (int) Math.PI;
  }
}