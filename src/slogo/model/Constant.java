package slogo.model;

public class Constant implements Command {

  private int value;

  public Constant(int value) {
    this.value = value;
  }

  public int getNumberParameters() {
    return 0;
  }

  public int execute() {
    return value;
  }
}
