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

  private static final String RESOURCES_PACKAGE = "resources.CommandFactory.properties";
  private Map<String, String> mySymbols;

  public CommandFactory() {
    mySymbols = new HashMap<>();
  }

  public void addCommandClasses() {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE);
    for (String key : Collections.list(resources.getKeys())) {
      String className = resources.getString(key);
      mySymbols.put(key, className);
    }
  }

  /**
  public Command createCommand(String commandType)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
    // TODO: create error type for when commandType doesn't exist in map
    //return mySymbols.get(commandType).getClass().getConstructor(Constant.class, Constant.class)
        .newInstance(new Constant(20), new Constant(0));
  }*/
}
