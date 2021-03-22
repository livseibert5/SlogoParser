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

/**
 * For is the Command that runs for each value specified in the range start-end going by increment.
 *
 * @author Livia Seibert
 */
public class For implements Command {

  private final Controller controller;
  private final CommandBlock header;
  private final CommandBlock body;

  /**
   * For constructor needs a controller to parse the command body of the for loop, a header to
   * determine the bounds and increment of the for loop, and a body block to execute.
   *
   * @param controller controller of current game
   * @param header     command block with information on the bounds and increment of the loop
   * @param body       block of code to execute on each loop
   */
  public For(Controller controller, CommandBlock header, CommandBlock body) {
    this.controller = controller;
    this.header = header;
    this.body = body;
  }

  /**
   * Configures the loop variables and then runs the commandBlock the given number of times,
   * updating the variable on each loop.
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
