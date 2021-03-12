package slogo.model.mathcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Expression;
import slogo.model.Turtle;

public class Product implements Command{

  private final double argument1;
  private final double argument2;

  public Product(Expression argument1, Expression argument2){
    this.argument1 = argument1.getValue();
    this.argument2 = argument2.getValue();
  }

  @Override
  public double execute(Turtle turtle) {
    return argument1 * argument2;
  }
}