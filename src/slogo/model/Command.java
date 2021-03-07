package slogo.model;

public interface Command {
  public int getNumberParameters();
  public double execute(Turtle turtle);
}
