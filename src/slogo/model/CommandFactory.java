package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
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

  private Object makeClass(Class<?> clazz)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    return clazz.getDeclaredConstructor(Constant.class).newInstance(new Constant(5));
  }

  public Object createCommand(String commandType)
      throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    var clazz = Class.forName(mySymbols.get(commandType));
    Object command = makeClass(clazz);
    return command;
  }
}
