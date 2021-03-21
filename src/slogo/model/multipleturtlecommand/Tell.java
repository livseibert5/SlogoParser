package slogo.model.multipleturtlecommand;

import java.util.List;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Turtle;
import slogo.model.backendexceptions.MathException;

/**
 * Tell is the Command that sets the turtles that will
 * execute the following commands.
 *
 * @author Livia Seibert
 */
public class Tell implements Command {

  private final CommandBlock turtles;
  private final Controller controller;

  /**
   * Tell constructor requires controller to set new active turtles list.
   *
   * @param controller controller for current game
   * @param turtles turtles to set as active
   */
  public Tell(Controller controller, CommandBlock turtles) {
    this.controller = controller;
    this.turtles = turtles;
  }

  /**
   * Sets active turtles list to turtles specified by command.
   *
   * @param turtle turtle object to execute command on
   * @return last turtle id
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
    String[] turtleIDs = turtles.toString().split(" ");
    List<Turtle> turtleList = new ArrayList<>();
    for (String id: turtleIDs) {
      turtleList.add(controller.getTurtleHandler().getTurtle(Integer.parseInt(id)));
    }
    controller.getTurtleHandler().setActiveTurtles(turtleList);
    return Integer.parseInt(turtleIDs[turtleIDs.length - 1]);
  }
}
