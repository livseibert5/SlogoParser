package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Minus implements Command{

  private final double argument;

  public Minus(Value argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    if(argument == 0){
      return 0;
    }
    else {
      return -1 * argument;
    }
  }
}