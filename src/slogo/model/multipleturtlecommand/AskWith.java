package slogo.model.multipleturtlecommand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
 * AskWith is the Command that runs a command block on turtles matching the given condition.
 *
 * @author Livia Seibert
 */
public class AskWith implements Command {

  private final Controller controller;
  private final CommandBlock condition;
  private final CommandBlock commands;

  /**
   * Constructor for AskWith command, requires controller to run command block for given turtles.
   *
   * @param controller controller for current game
   * @param condition  condition for which turtles to run
   * @param commands   commands to run for turtles that meet the criteria
   */
  public AskWith(Controller controller, CommandBlock condition, CommandBlock commands) {
    this.controller = controller;
    this.condition = condition;
    this.commands = commands;
  }

  /**
   * Updates active turtles list to be the list of turtles that meets the condition criteria, runs
   * the commands on these turtles, then reverts the active list to its previous state.
   *
   * @param turtle turtle object to execute command on
   * @return last parser output
   * @throws ClassNotFoundException    class not found in command factory
   * @throws NoSuchMethodException     constructor doesn't exist in command factory
   * @throws InvocationTargetException issue invoking constructor
   * @throws InstantiationException    issue instantiating command
   * @throws IllegalAccessException    illegal access to command
   * @throws MathException             illegal math command
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    Parser parser = new Parser(controller);
    List<Turtle> prevTurtles = controller.getTurtleHandler().getActiveTurtles();
    List<Turtle> currentActiveTurtles = controller.getTurtleHandler().getAllTurtles().stream()
        .filter(turtleObj -> {
          try {
            controller.getTurtleHandler().setActiveTurtles(new ArrayList<>(
                Collections.singletonList(turtleObj)));
            return parser.parse(condition.toString()) == 1;
          } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException | MathException e) {
            return false;
          }
        }).collect(Collectors.toList());
    controller.getTurtleHandler().setActiveTurtles(currentActiveTurtles);
    int parserOutput = parser.parse(commands.toString());
    controller.getTurtleHandler().setActiveTurtles(prevTurtles);
    return parserOutput;
  }
}
