package slogo.model.multipleturtlecommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

/**
 * AskWith is the Command subclass that runs a command block on turtles matching the
 * given condition.
 *
 * @author Livia Seibert
 */
public class AskWith implements Command {

  private Controller controller;
  private CommandBlock condition;
  private CommandBlock commands;

  public AskWith(Controller controller, CommandBlock condition, CommandBlock commands) {
    this.controller = controller;
    this.condition = condition;
    this.commands = commands;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    Parser parser = new Parser(controller);
    List<Turtle> prevTurtles = controller.getTurtleHandler().getActiveTurtles();
    List<Turtle> currentActiveTurtles = controller.getTurtleHandler().getAllTurtles().stream().filter(turtleObj -> {
      try {
        controller.getTurtleHandler().setActiveTurtles(new ArrayList<>(Arrays.asList(turtleObj)));
        return parser.parse(condition.toString()) == 1;
      } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | MathException e) {
        return false;
      }
    }).collect(Collectors.toList());
    System.out.println(currentActiveTurtles);
    controller.getTurtleHandler().setActiveTurtles(currentActiveTurtles);
    int parserOutput = parser.parse(commands.toString());
    controller.getTurtleHandler().setActiveTurtles(prevTurtles);
    return parserOutput;
  }
}
