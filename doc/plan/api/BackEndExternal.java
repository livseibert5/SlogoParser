import java.util.List;
import java.util.Map;

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
   * Returns new turtle object to front end when user declares new turtle.
   *
   * @return new turtle object
   */
  public Object getTurtle();
}