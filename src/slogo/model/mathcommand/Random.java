package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Random implements Command{

  private final double argument;

  public Random(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.random() * argument;
  }
}