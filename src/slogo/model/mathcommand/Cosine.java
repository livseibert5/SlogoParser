package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Cosine implements Command{

  private final double argument;

  public Cosine(Value argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.cos(Math.toRadians(argument));
  }
}