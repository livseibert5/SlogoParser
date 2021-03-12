package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.UserDefinedCommand;

/**
 * MakeUserInstruction is the Command subclass that lets users define their own commands
 * with a name, arguments, and body. These user-defined commands are then able to be run
 * by name from the command line.
 *
 * @author Livia Seibert
 */
public class MakeUserInstruction implements Command {

  private Controller controller;
  private String name;
  private CommandBlock arguments;
  private CommandBlock function;

  public MakeUserInstruction(Controller controller, String name, CommandBlock arguments, CommandBlock function) {
    this.controller = controller;
    this.name = name;
    this.arguments = arguments;
    this.function = function;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    String[] argumentList = arguments.toString().split(" ");
    int numberParameters = argumentList.length;
    controller.getUserDefinedCommandHandler().addCommand(new UserDefinedCommand(name, numberParameters, argumentList, function));
    return 1;
  }
}
