package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

public class NaturalLog implements Command{

  private final double argument;
  private final String ERROR_MESSAGE = "Invalid Log Input";

  public NaturalLog(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public double execute(Turtle turtle) throws MathException {
    if(argument > 0) {
      return Math.log(argument);
    }
    else{
      throw new MathException(ERROR_MESSAGE);
    }
  }
}