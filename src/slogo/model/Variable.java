package slogo.model;

public class Variable extends Expression {

  private String name;

  public Variable(String name, double value) {
    super(value);
    this.name = name;
  }

  public Variable(String name) {
    super(0);
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
