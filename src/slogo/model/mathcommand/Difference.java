package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Difference implements Command{

  private final double argument1;
  private final double argument2;

  public Difference(Value argument1, Value argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return argument1 - argument2;
  }
}