package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class ArcTangent implements Command{

  private final double argument;

  public ArcTangent(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 1;
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.toDegrees(Math.atan(argument));
  }
}