package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.controller.Controller;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Value;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;
import slogo.model.Turtle;

/**
 * Repeat is the Command subclass that runs a code block the given number of times.
 *
 * @author Livia Seibert
 */
public class Repeat implements Command {

  private int count;
  private CommandBlock list;
  private Controller controller;

  /**
   * Repeat constructor needs a controller to execute the command body, an argument to
   * know how many times to run, and a list of commands to run the given number of times.
   *
   * @param controller controller for the current game
   * @param argument number of times to run the code block
   * @param list list of commands to run each time
   */
  public Repeat(Controller controller, Value argument, CommandBlock list) {
    count = (int) argument.getValue();
    this.controller = controller;
    this.list = list;
  }

  /**
   * Determines how many times to run the code block and then executes the loop.
   *
   * @param turtle turtle object to execute command on
   * @return result of last command
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   * @throws MathException
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    int parserOutput = 0;
    Parser parser = new Parser(controller);
    for(int i = 0; i < count; i++){
      parserOutput = parser.parse(list.toString());
    }
    return parserOutput;
  }
}
