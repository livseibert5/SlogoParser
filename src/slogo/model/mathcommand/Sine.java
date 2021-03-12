package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Sine implements Command{

  private final double argument;

  public Sine(Value argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.sin(Math.toRadians(argument));
  }
}