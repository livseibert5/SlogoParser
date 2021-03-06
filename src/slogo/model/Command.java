package slogo.model;

public interface Command {
  public int getNumberParameters();
  public int execute(Turtle turtle);
}
