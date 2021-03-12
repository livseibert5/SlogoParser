package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.StreamSupport;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.Variable;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

public class For implements Command {

  private Controller controller;
  private CommandBlock header;
  private CommandBlock body;

  public For(Controller controller, CommandBlock header, CommandBlock body) {
    this.controller =  controller;
    this.header = header;
    this.body = body;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    String[] headerComponents = header.toString().split(" ");
    String variable = headerComponents[0];
    int start = Integer.parseInt(headerComponents[1]);
    int end = Integer.parseInt(headerComponents[2]);
    int increment = Integer.parseInt(headerComponents[3]);
    Variable var = new Variable(variable);
    controller.getVariableHandler().addVariable(var);
    Parser parser = new Parser(controller);
    int parserOutput = 0;
    for (int i = start; i < end; i += increment) {
      var.setValue(i);
      parserOutput = parser.parse(body.toString());
    }
    controller.getVariableHandler().removeVariableWithName(variable);
    return parserOutput;
  }
}
