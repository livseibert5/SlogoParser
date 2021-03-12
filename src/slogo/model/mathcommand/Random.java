package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Random implements Command{

  private final double argument;

  public Random(Value argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.random() * argument;
  }
}