package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;

public class Pi implements Command{

  @Override
  public int getNumberParameters() {
    return 0;
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.PI;
  }
}