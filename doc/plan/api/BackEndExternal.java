import java.util.List;
import java.util.Map;

/**
 * The purpose of this interface is to allow user input from the front end to be parsed and
 * executed on the back end, and to let the results of the commands be passed from the back end
 * to the front end to be displayed.
 */
interface BackEndExternal {
  /**
   * Allows user input to be passed from the front end to the parser to be handled.
   *
   * @param command entered by user
   */
  public void parse(String command);

  /**
   * If the parser determines that the command sets a new variable value, it will populate
   * a map with the new variable and its value. This map can be accessed be the front end to
   * be displayed with this method.
   *
   * @return map of variable names and their values
   */
  public Map<String, String> getVariables();

  /**
   * Lets front end access new commands defined by user so that they can be displayed in a box on
   * the front end.
   *
   * @return list of new user defined commands
   */
  public List<String> getUserCommands();

  /**
   * Tells the back end which language the incoming commands will be in so that it can use the
   * proper resource bundle.
   */
  public void setLanguage(String language);

  /**
   * Each type of command has an expected output, and this function lets the front end access
   * that input to display it in the IDE.
   *
   * @return return value of the entered command
   */
  public String getParserOutput();

  /**
   * Allows front end to access the current coordinates of the turtle.
   *
   * @return x location and y location of turtle on grid
   */
  public int[] getTurtleLocation();

  /**
   * Allows front end to access the current orientation of the turtle.
   *
   * @return degree orientation of the turtle on the grid
   */
  public double getTurtleOrientation();

  /**
   * Returns new turtle object to front end when user declares new turtle.
   *
   * @return new turtle object
   */
  public Object getTurtle();
}