package slogo.model.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Creates instances of the appropriate command for use in the Parser.
 *
 * @author Livia Seibert
 */
public class CommandFactory {

  private static final String RESOURCES_PACKAGE = "slogo.model.resources.";
  private final Map<String, String> mySymbols;

  /**
   * Constructor for CommandFactory that sets up the factory.
   */
  public CommandFactory() {
    mySymbols = new HashMap<>();
    addCommandClasses();
  }

  /**
   * Adds class keys and class locations to symbols list.
   */
  private void addCommandClasses() {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "CommandFactory");
    for (String key : Collections.list(resources.getKeys())) {
      String className = resources.getString(key);
      mySymbols.put(key, className);
    }
  }

  /**
   * Calls the constructor of the class to create the actual instance of the object.
   *
   * @param clazz     class type of object we'd like to instantiate
   * @param arguments arguments list to pass to new object
   * @return new Command object
   * @throws NoSuchMethodException method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException can't make new object from command factory
   * @throws IllegalAccessException trying to make object in command factory without access
   */
  private Object makeClass(Class<?> clazz, List<Object> arguments)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return clazz.getDeclaredConstructor(determineParameterTypes(clazz))
        .newInstance(arguments.toArray());
  }

  /**
   * Returns the number of parameters the constructor for the given command type takes.
   *
   * @param commandType type of command class to instantiate
   * @return number of parameters the command's constructor takes
   * @throws ClassNotFoundException can't find class to pull constructors from
   */
  public int determineNumberParameters(String commandType) throws ClassNotFoundException {
    return getConstructors(commandType)[0].getParameterCount();
  }

  /**
   * Returns a list of constructors for a given command type class.
   *
   * @param commandType type of command class to instantiate
   * @return list of constructors for the given command type
   * @throws ClassNotFoundException can't find class to pull constructors from
   */
  private Constructor<?>[] getConstructors(String commandType) throws ClassNotFoundException {
    var clazz = Class.forName(mySymbols.get(commandType));
    return clazz.getConstructors();
  }

  public boolean isControlCommand(String commandType) throws ClassNotFoundException {
    return mySymbols.containsKey(commandType) && Arrays
        .asList(getConstructors(commandType)[0].getParameterTypes())
        .contains(Class.forName("slogo.controller.Controller"));
  }

  /**
   * Returns list of types of parameters for a class's constructor.
   *
   * @param clazz class type of object
   * @return list of Classes that are the types of the constructor parameters
   */
  private Class<?>[] determineParameterTypes(Class<?> clazz) {
    Constructor<?>[] constructors = clazz.getConstructors();
    return constructors[0].getParameterTypes();
  }

  /**
   * Allows Parser to create a command of a specific type with a list of arguments without knowing
   * the implementation details of the class's constructor.
   *
   * @param commandType type of command class we want to instantiate
   * @param arguments   list of objects that are the arguments for the new command class
   * @return new command object
   * @throws ClassNotFoundException class not found in command factory
   * @throws NoSuchMethodException method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException can't make new object from command factory
   * @throws IllegalAccessException trying to make object in command factory without access
   */
  public Object createCommand(String commandType, List<Object> arguments)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    var clazz = Class.forName(mySymbols.get(commandType));
    return makeClass(clazz, arguments);
  }
}
