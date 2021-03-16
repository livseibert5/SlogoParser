package slogo.model.multipleturtlecommand;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

/**
 * Ask is the Command subclass that executes commands on a list of turtles.
 *
 * @author Livia Seibert
 */
public class Ask implements Command {

  private Controller controller;
  private CommandBlock turtles;
  private CommandBlock commands;

  public Ask(Controller controller, CommandBlock turtles, CommandBlock commands) {
    this.controller = controller;
    this.turtles = turtles;
    this.commands = commands;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    List<String> turtleList = Arrays.asList(turtles.toString().split(" "));
    Parser parser = new Parser(controller);
    int parserOutput = 0;
    for (String turtleItem: turtleList) {
      controller.getTurtleHandler().setActiveTurtle(Integer.parseInt(turtleItem));
      parserOutput = parser.parse(commands.toString());
    }
    return parserOutput;
  }
}
