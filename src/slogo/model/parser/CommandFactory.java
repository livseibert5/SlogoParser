package slogo.model.parser;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import slogo.model.Variable;

/**
 * Creates instances of the appropriate command for use in the Parser.
 *
 * @author Livia Seibert
 */
public class CommandFactory {

  private static final String RESOURCES_PACKAGE = "slogo.model.resources.";
  private Map<String, String> mySymbols;
  private List<Variable> variables;

  /**
   * Constructor for CommandFactory that sets up the factory.
   */
  public CommandFactory() {
    mySymbols = new HashMap<>();
    addCommandClasses();
    variables = new ArrayList<>();
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
   * @throws NoSuchMethodException
   * @throws IllegalAccessException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws ClassNotFoundException
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
   * @throws ClassNotFoundException
   */
  public int determineNumberParameters(String commandType) throws ClassNotFoundException {
    return getConstructors(commandType)[0].getParameterCount();
  }

  /**
   * Returns a list of constructors for a given command type class.
   *
   * @param commandType type of command class to instantiate
   * @return list of constructors for the given command type
   * @throws ClassNotFoundException
   */
  private Constructor[] getConstructors(String commandType) throws ClassNotFoundException {
    var clazz = Class.forName(mySymbols.get(commandType));
    return clazz.getConstructors();
  }

  public boolean isControlCommand(String commandType) throws ClassNotFoundException {
    if (mySymbols.containsKey(commandType) && Arrays.asList(getConstructors(commandType)[0].getParameterTypes()).contains(Class.forName("slogo.controller.Controller"))) {
      System.out.println("Here");
      return true;
    }
    return false;
  }

  /**
   * Returns list of types of parameters for a class's constructor.
   *
   * @param clazz class type of object
   * @return list of Classes that are the types of the constructor parameters
   */
  private Class[] determineParameterTypes(Class<?> clazz) {
    Constructor[] constructors = clazz.getConstructors();
    return constructors[0].getParameterTypes();
  }

  /**
   * Allows Parser to create a command of a specific type with a list of arguments without knowing
   * the implementation details of the class's constructor.
   *
   * @param commandType type of command class we want to instantiate
   * @param arguments   list of objects that are the arguments for the new command class
   * @return new command object
   * @throws ClassNotFoundException
   * @throws InvocationTargetException
   * @throws NoSuchMethodException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  public Object createCommand(String commandType, List<Object> arguments)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    var clazz = Class.forName(mySymbols.get(commandType));
    Object command = makeClass(clazz, arguments);
    return command;
  }
}
