package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;

public class PenColor implements Command {

  @Override
  public double execute(Turtle turtle) {
    return turtle.getPenColor();
  }
}
