package slogo.model.movementcommand;

import slogo.model.Command;

public class Backward extends MovementCommand implements Command {

  private int pixels;

  public Backward(Command argument) {
    this.pixels = argument.execute();
  }

  @Override
  public int getNumberParameters() {
    return 1;
  }

  @Override
  public int execute() {
    turtle.setOrientation(turtle.getOrientation() + 180);
    int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
    return pixels;
  }
}
