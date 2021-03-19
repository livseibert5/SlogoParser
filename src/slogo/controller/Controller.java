package slogo.controller;

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

  private final TurtleHandler turtleHandler;
  private final VariableHandler variableHandler;
  private final UserDefinedCommandHandler userDefinedCommandHandler;
  private final ColorHandler colorHandler;
  private String language;

  /**
   * Defines a new turtleHandler, variableHandler, and userDefinedCommand handler for
   * both the front end and back end to use.
   */
  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
    userDefinedCommandHandler = new UserDefinedCommandHandler();
    colorHandler = new ColorHandler();
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
   * Allows the back end to get the language for the parser.
   *
   * @return language being used
   * @author Rachel Luria
   */
  public String getLanguage(){
    return language;
  }

  /**
   * Allows the front end to set the language.
   *
   * @param lang desired language
   */
  public void setLanguage(String lang) {language = lang;}
}
