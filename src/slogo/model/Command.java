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
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException    can't make new object from command factory
   * @throws IllegalAccessException    trying to make object in command factory without access
   * @throws MathException             illegal math command
   */
  double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException;
}
