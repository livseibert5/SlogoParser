package slogo.model;

import java.lang.reflect.InvocationTargetException;
import slogo.model.backendexceptions.MathException;

/**
 * Command is the interface that all other Command classes implement.
 *
 * @author Livia Seibert and Rachel Luria
 */
public interface Command {

  /**
   * Calling execute on any command type will run the action of the command it's called on.
   *
   * @param turtle turtle object to execute command on
   * @return double result of executing command
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException;
}
