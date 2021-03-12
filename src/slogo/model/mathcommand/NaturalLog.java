package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;
import slogo.model.backendexceptions.MathException;

public class NaturalLog implements Command{

  private final double argument;
  private final String ERROR_MESSAGE = "Invalid Log Input";

  public NaturalLog(Value argument){
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