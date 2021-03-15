package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class SetPenSize implements Command {

  private double value;

  public SetPenSize(Value value) {
    this.value = value.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    turtle.setPenThickness(value);
    return value;
  }
}
