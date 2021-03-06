package slogo.model;

public class Backward implements Command {

  private int pixels;

  public Backward(Command argument) {
    this.pixels = argument.execute();
  }

  public int getNumberParameters() {
    return 1;
  }

  public int execute() {
    turtle.setOrientation(turtle.getOrientation() + 180.0)
    int[] currentLocation = turtle.getLocation();
    double currentOrientation = turtle.getOrientation();
    turtle.move(pixels * Math.cos(currentOrientation), pixels * Math.sin(currentOrientation));
    return pixels;
  }
}
