package slogo.controller;

import slogo.model.handlers.TurtleHandler;
import slogo.model.handlers.UserDefinedCommandHandler;
import slogo.model.handlers.VariableHandler;

public class Controller {

  private TurtleHandler turtleHandler;
  private VariableHandler variableHandler;
  private UserDefinedCommandHandler userDefinedCommandHandler;

  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
    userDefinedCommandHandler = new UserDefinedCommandHandler();
  }

  public VariableHandler getVariableHandler() {
    return variableHandler;
  }

  public TurtleHandler getTurtleHandler() {
    return turtleHandler;
  }

  public UserDefinedCommandHandler getUserDefinedCommandHandler() {
    return userDefinedCommandHandler;
  }
}
