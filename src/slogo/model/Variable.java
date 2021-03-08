package slogo.model;

public class Variable {

  private String name;
  private double value;

  public Variable(String name, double value) {
        this.name = name;
        this.value = value;
  }

  public Variable(String name) {
    this.name = name;
  }

  public double getValue(){
    return value;
  }

  public String getName() {
    return name;
  }

  public void setValue(double value) {
    this.value = value;
  }
}
