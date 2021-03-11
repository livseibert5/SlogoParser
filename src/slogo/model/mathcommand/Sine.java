package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Expression;
import slogo.model.Turtle;

public class Sine implements Command{

  private final double argument;

  public Sine(Expression argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.sin(Math.toRadians(argument));
  }
}