package slogo.controller;

import slogo.model.TurtleHandler;
import slogo.model.VariableHandler;

public class Controller {

  private TurtleHandler turtleHandler;
  private VariableHandler variableHandler;

  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
  }

  public VariableHandler getVariableHandler() {
    return variableHandler;
  }

  public TurtleHandler getTurtleHandler() {
    return turtleHandler;
  }
}
