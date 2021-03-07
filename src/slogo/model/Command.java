package slogo.model;

public interface Command {
  public default int getNumberParameters() {
    return 0;
  }
  public double execute(Turtle turtle);
}
