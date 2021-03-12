package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class Power implements Command{

  private final double base;
  private final double exponent;

  public Power(Value base, Value exponent){
    this.base = base.getValue();
    this.exponent = exponent.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return Math.pow(base, exponent);
  }
}