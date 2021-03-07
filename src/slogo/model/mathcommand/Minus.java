package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Minus implements Command{

  private final double argument;

  public Minus(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 1;
  }

  @Override
  public double execute(Turtle turtle) {
    return -1 * argument;
  }
}