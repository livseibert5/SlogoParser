package slogo.model;

public class Forward implements Command {

  private int pixels;

  public Forward(Command argument) {
    this.pixels = argument.execute();
  }

  public int getNumberParameters() {
    return 1;
  }

  public int execute() {
    int[] currentLocation = turtle.getLocation();
    double currentOrientation = turtle.getOrientation();
    turtle.move(pixels * Math.cos(currentOrientation), pixels * Math.sin(currentOrientation));
    return pixels;
  }
}
