package slogo.controller;

import java.util.HashMap;
import java.util.Map;
import slogo.model.Color;
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
  private String language;
  private Map<Integer, Color> colorMap;

  /**
   * Defines a new turtleHandler, variableHandler, and userDefinedCommand handler for
   * both the front end and back end to use.
   */
  public Controller() {
    turtleHandler = new TurtleHandler();
    variableHandler = new VariableHandler();
    userDefinedCommandHandler = new UserDefinedCommandHandler();
    language = "English";
    colorMap = new HashMap<>();
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

  /**
   * Allows the front end to set the language for the parser.
   *
   * @return language being used
   * @author Rachel Luria
   */
  public String getLanguage(){
    return language;
  }

  /**
   * Adds a color and index to the color map
   *
   * @param index for the color to be mapped to
   * @param color specific color to be added
   */
  public void addColor(int index, Color color) {
    colorMap.put(index, color);
  }
}
