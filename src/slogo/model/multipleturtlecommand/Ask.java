package slogo.model.multipleturtlecommand;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;

/**
 * Ask is the Command that executes commands on a list of turtles.
 *
 * @author Livia Seibert
 */
public class Ask implements Command {

  private final Controller controller;
  private final CommandBlock turtles;
  private final CommandBlock commands;

  /**
   * Constructor for Ask command, requires controller to execute command block.
   *
   * @param controller controller for current game
   * @param turtles ids of turtles to run command on
   * @param commands commands to run
   */
  public Ask(Controller controller, CommandBlock turtles, CommandBlock commands) {
    this.controller = controller;
    this.turtles = turtles;
    this.commands = commands;
  }

  /**
   * Updates the active turtles list, executes the command block for those turtles, then sets
   * the active turtles list back to its previous value.
   *
   * @param turtle turtle object to execute command on
   * @return last parser output
   * @throws ClassNotFoundException class not found in command factory
   * @throws NoSuchMethodException constructor doesn't exist in command factory
   * @throws InvocationTargetException issue invoking constructor
   * @throws InstantiationException issue instantiating command
   * @throws IllegalAccessException illegal access to command
   * @throws MathException illegal math command
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    String[] turtleList = turtles.toString().split(" ");
    Parser parser = new Parser(controller);
    int parserOutput;
    List<Turtle> prevActiveTurtles = controller.getTurtleHandler().getActiveTurtles();
    List<Turtle> turtleObjects = new ArrayList<>();
    for (String turtleItem: turtleList) {
      turtleObjects.add(controller.getTurtleHandler().getTurtle(Integer.parseInt(turtleItem)));
    }
    controller.getTurtleHandler().setActiveTurtles(turtleObjects);
    parserOutput = parser.parse(commands.toString());
    controller.getTurtleHandler().setActiveTurtles(prevActiveTurtles);
    return parserOutput;
  }
}
