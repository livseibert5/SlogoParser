package slogo.model.displaycommand;

import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

public class SetPenColor implements Command {

  private double value;

  public SetPenColor(Value value) {
    this.value = value.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    turtle.setPenColor(value);
    return value;
  }
}
