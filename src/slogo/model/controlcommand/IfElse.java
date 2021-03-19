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
 * IfElse is the Command subclass that runs one block of commands if the condition passed in is
 * true, and the other block if the condition is false.
 *
 * @author Livia Seibert
 */
public class IfElse implements Command {

  private final double value;
  private final Controller controller;
  private final CommandBlock trueBlock;
  private final CommandBlock falseBlock;

  /**
   * Constructor for IfElse command takes in a constant as the condition, a block to execute if the
   * condition is true, and a block to execute if the condition is false.
   *
   * @param value      condition for if else statement
   * @param trueBlock  block to run if condition is non-zero
   * @param falseBlock block to run if condition is zeero
   */
  public IfElse(Controller controller, Value value, CommandBlock trueBlock,
      CommandBlock falseBlock) {
    this.controller = controller;
    this.value = value.getValue();
    this.trueBlock = trueBlock;
    this.falseBlock = falseBlock;
  }

  /**
   * Sends true command block to parser if condition is non-zero, sends false block to parser if
   * condition is zero.
   *
   * @param turtle turtle object to execute command on
   * @return result of executing the true or false block
   * @throws ClassNotFoundException class not found in command factory
   * @throws NoSuchMethodException method not found in command factory
   * @throws InvocationTargetException can't invoke target
   * @throws InstantiationException can't make new object from command factory
   * @throws IllegalAccessException trying to make object in command factory without access
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    Parser parser = new Parser(controller);
    int parserOutput;
    if (value != 0) {
      parserOutput = parser.parse(trueBlock.toString());
    } else {
      parserOutput = parser.parse(falseBlock.toString());
    }
    return parserOutput;
  }
}
