package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Parser;
import slogo.model.Turtle;
import slogo.model.Variable;

public class DoTimes implements Command {

  private Controller controller;
  private CommandBlock limit;
  private CommandBlock commandBlock;

  public DoTimes(Controller controller, CommandBlock limit, CommandBlock commandBlock) {
    this.controller = controller;
    this.limit = limit;
    this.commandBlock = commandBlock;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String[] doTimesPieces = limit.toString().split(" ");
    String varName = doTimesPieces[0];
    String newCommand = "set " + limit.toString();
    Parser parser = new Parser(controller);
    int limitVar = parser.parse(newCommand);
    Variable variable = controller.getVariableHandler().getVariableWithName(varName);
    System.out.println(variable);
    for (int i = 1; i < limitVar; i++) {
      variable.setValue(i);
      parser.parse(commandBlock.toString());
    }
    return 0;
  }
}
