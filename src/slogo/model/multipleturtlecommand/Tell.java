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
 * Tell is the Command subclass that sets the turtles that will
 * execute the following commands.
 *
 * @author Livia Seibert
 */
public class Tell implements Command {

  private final CommandBlock turtles;
  private final Controller controller;

  public Tell(Controller controller, CommandBlock turtles) {
    this.controller = controller;
    this.turtles = turtles;
  }

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
