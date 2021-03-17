package slogo.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
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
  private static final String RESOURCE_FOLDER = "slogo.model.resources.";
  private ResourceBundle colors = ResourceBundle
      .getBundle(RESOURCE_FOLDER + "Colors");
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
    initializeColors();
  }

  private void initializeColors() {
    Enumeration<String> keys = colors.getKeys();
    for(int i = 0; i < colors.keySet().size(); i++){
      String key = keys.nextElement();
      String rgb = colors.getString(key);
      Color color = new Color(makeColor(rgb.substring(0, 2)), makeColor(rgb.substring(2, 4)), makeColor(rgb.substring(4)));
      colorMap.put(i, color);
    }
  }

  private int makeColor(String hex){
    return Integer.parseInt(hex, 16);
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

  public Color getColor(int index) {return colorMap.get(index);}
}
