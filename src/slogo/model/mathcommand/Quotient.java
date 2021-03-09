package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Quotient implements Command{

  private final double argument1;
  private final double argument2;

  public Quotient(Constant argument1, Constant argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return argument1 / argument2;
  }
}