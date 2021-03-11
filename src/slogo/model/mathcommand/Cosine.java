package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Cosine implements Command{

  private final double argument;

  public Cosine(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.toDegrees(Math.cos(argument));
  }
}