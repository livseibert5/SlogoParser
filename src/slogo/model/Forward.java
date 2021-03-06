package slogo.model;

public class Forward extends MovementCommand implements Command {

  private int pixels;

  public Forward(Command argument) {
    this.pixels = argument.execute();
  }

  public int getNumberParameters() {
    return 1;
  }

  public int execute() {
    int[] newLocation = determineLocation(turtle.getLocation(), turtle.getOrientation(), pixels);
    return pixels;
  }
}
