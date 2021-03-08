package slogo.model;

import slogo.controller.Variable;

public class ExpressionFactory {

  public Constant makeConstant(int value) {
    return new Constant(value);
  }

  public CommandBlock makeCommandBlock(String commandBlock) {
    return new CommandBlock(commandBlock);
  }

  public Variable makeVariable(String name, double value) {
    return new Variable(name, value);
  }
}
