package slogo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.model.Color;
import slogo.model.handlers.ColorHandler;
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
  private ColorHandler colorHandler;
  private String language;

  /**
   * Defines a new turtleHandler, variableHandler, and userDefinedCommand handler for
   * both the front end and back end to use.
   */
  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
    userDefinedCommandHandler = new UserDefinedCommandHandler();
    language = "English";
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

  public ColorHandler getColorHandler(){
    return colorHandler;
  }

  /**
   * Allows the front end to set the language for the parser.
   *
   * @return language being used
   * @author Rachel Luria
   */
  public String getLanguage(){
    return language;
  }

}
