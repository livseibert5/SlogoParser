package slogo.model;

public class Variable extends Expression {

  private String name;
  //private double value;

  public Variable(String name, double value) {
    super(value);
    this.name = name;
     //   this.value = (int) value;
  }

  public Variable(String name) {
    super(0);
    this.name = name;
  }

  public String getName() {
    return name;
  }

  //public void setValue(double value) {
   // this.value = (int) value;
  //}
}
