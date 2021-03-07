package slogo.model;

import java.lang.reflect.InvocationTargetException;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import slogo.model.movementcommand.Forward;

public class CommandFactory {

  private static final String RESOURCES_PACKAGE = CommandFactory.class.getPackageName() + ".resources.";
  private Map<String, String> mySymbols;

  public CommandFactory() {
    mySymbols = new HashMap<>();
  }

  public void addCommandClasses() {
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
    addCommandClasses();
    var clazz = Class.forName(mySymbols.get(commandType));
    Object command = makeClass(clazz);
    return command;
  }

  /**
  public Command createCommand(String commandType)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    // TODO: create error type for when commandType doesn't exist in map
    //return mySymbols.get(commandType).getClass().getConstructor(Constant.class, Constant.class)
        .newInstance(new Constant(20), new Constant(0));
  }*/
}
