package slogo.controller;

import slogo.model.handlers.TurtleHandler;
import slogo.model.handlers.UserDefinedCommandHandler;
import slogo.model.handlers.VariableHandler;

/**
 * Handles the transfer of data between the front end and the backend.
 *
 * @author Livia Seibert
 */
public class Controller {

  private TurtleHandler turtleHandler;
  private VariableHandler variableHandler;
  private UserDefinedCommandHandler userDefinedCommandHandler;

  /**
   * Defines a new turtleHandler, variableHandler, and userDefinedCommand handler for
   * both the front end and back end to use.
   */
  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
    userDefinedCommandHandler = new UserDefinedCommandHandler();
  }

  /**
   * Allows front end and back end to access variable handler.
   *
   * @return current variable handler
   */
  public VariableHandler getVariableHandler() {
    return variableHandler;
  }

  /**
   * Allows front end and back end to access turtle handler.
   *
   * @return current turtle handler
   */
  public TurtleHandler getTurtleHandler() {
    return turtleHandler;
  }

  /**
   * Allows front end and back end to access user defined command handler.
   *
   * @return current user defined command handler
   */
  public UserDefinedCommandHandler getUserDefinedCommandHandler() {
    return userDefinedCommandHandler;
  }
}
