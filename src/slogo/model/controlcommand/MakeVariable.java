package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.controller.Variable;
import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

public class MakeVariable implements Command {

  Controller controller;
  String name;
  int value;

  public MakeVariable(Controller controller, String name, Constant value) {
    this.controller = controller;
    this.name = name;
    this.value = value.getValue();
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    controller.getVariableHandler().addVariable(new Variable(name, value));
    return 0;
  }
}
