package slogo.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

public class CommandFactory {

  private static final String RESOURCES_PACKAGE = CommandFactory.class.getPackageName() + ".resources.";
  private Map<String, String> mySymbols;

  public CommandFactory() {
    mySymbols = new HashMap<>();
    addCommandClasses();
  }

  private void addCommandClasses() {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + "CommandFactory");
    for (String key : Collections.list(resources.getKeys())) {
      String className = resources.getString(key);
      mySymbols.put(key, className);
    }
  }

  private Object makeClass(Class<?> clazz, List<Constant> arguments)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    if (arguments.size() == 2) {
      return clazz.getDeclaredConstructor(Constant.class, Constant.class).newInstance(arguments.get(0), arguments.get(1));
    } else {
      return clazz.getDeclaredConstructor(Constant.class).newInstance(arguments.get(0));
    }
  }

  public int determineNumberParameters(String commandType) throws ClassNotFoundException {
    var clazz = Class.forName(mySymbols.get(commandType));
    Constructor[] constructors = clazz.getConstructors();
    return constructors[0].getParameterCount();
  }

  public Object createCommand(String commandType, List<Constant> arguments)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    var clazz = Class.forName(mySymbols.get(commandType));
    Object command = makeClass(clazz, arguments);
    return command;
  }
}
