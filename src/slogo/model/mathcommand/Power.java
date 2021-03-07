package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Power implements Command{

  private int base;
  private int exponent;

  public Power(Constant base, Constant exponent){
    this.base = base.getValue();
    this.exponent = exponent.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 2;
  }

  @Override
  public int execute(Turtle turtle) {
    return (int) Math.pow(base, exponent);
  }
}