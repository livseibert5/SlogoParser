package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class Random implements Command{

  private int argument;

  public Random(Constant argument){
    this.argument = argument.getValue();
  }

  @Override
  public int getNumberParameters() {
    return 1;
  }

  @Override
  public int execute(Turtle turtle) {
    return (int) (Math.random() * argument);
  }
}