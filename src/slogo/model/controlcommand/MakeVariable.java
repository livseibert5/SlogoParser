package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Value;
import slogo.model.Variable;
import slogo.model.Command;
import slogo.model.Turtle;

/**
 * MakeVariable is the Command subclass that lets the user define a command and save the value for
 * later use.
 *
 * @author Livia Seibert
 */
public class MakeVariable implements Command {

  Controller controller;
  Variable name;
  double value;

  /**
   * MakeVariable constructor takes in a variable name and value, as well as a reference to the
   * controller so it can add the variable to the VariableHandler.
   *
   * @param controller Controller is needed to access the VariableHandler
   * @param name       Variable object with the name set
   * @param value      value to put in the variable object
   */
  public MakeVariable(Controller controller, Variable name, Value value) {
    this.controller = controller;
    this.name = name;
    this.value = value.getValue();
  }

  /**
   * Set value of user-defined variable and add it to the variable list in VariableHandler.
   *
   * @param turtle turtle object to execute command on
   * @return value variable is being set to
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    name.setValue(value);
    controller.getVariableHandler().addVariable(name);
    return value;
  }
}
