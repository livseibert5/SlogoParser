package slogo.model;

public class Backward extends MovementCommand implements Command {

  private int pixels;

  public Backward(Command argument) {
    this.pixels = argument.execute();
  }

  public int getNumberParameters() {
    return 1;
  }

  public int execute() {
    int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
    newLocation[0] *= -1;
    newLocation[1] *= -1;
    return pixels;
  }
}
