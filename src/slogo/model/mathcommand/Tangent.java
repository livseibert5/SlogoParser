package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

public class Tangent implements Command{

  private final double argument;
  private final String ERROR_MESSAGE = "Invalid Tangent Input";

  public Tangent(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) throws MathException {
    if(argument % 90 == 0 && argument % 180 != 0){
      throw new MathException(ERROR_MESSAGE);
    }
    else{
      return Math.tan(Math.toRadians(argument));
    }
  }
}