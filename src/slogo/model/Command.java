package slogo.model;

import java.lang.reflect.InvocationTargetException;

public interface Command {
  double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;
}
