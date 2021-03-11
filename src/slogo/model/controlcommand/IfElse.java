package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.backendexceptions.MathException;
import slogo.model.parser.Parser;
import slogo.model.Turtle;

/**
 * IfElse is the Command subclass that runs one block of commands if the condition passed
 * in is true, and the other block if the condition is false.
 *
 * @author Livia Seibert
 */
public class IfElse implements Command {

  private double value;
  private CommandBlock trueBlock;
  private CommandBlock falseBlock;

  /**
   * Constructor for IfElse command takes in a constant as the condition, a block to
   * execute if the condition is true, and a block to execute if the condition is false.
   *
   * @param value condition for if else statement
   * @param trueBlock block to run if condition is non-zero
   * @param falseBlock block to run if condition is zeero
   */
  public IfElse(Constant value, CommandBlock trueBlock, CommandBlock falseBlock) {
    this.value = value.getValue();
    this.trueBlock = trueBlock;
    this.falseBlock = falseBlock;
  }

  /**
   * Sends true command block to parser if condition is non-zero, sends false block to parser if
   * condition is zero.
   *
   * @param turtle turtle object to execute command on
   * @return
   * @throws ClassNotFoundException
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, MathException {
    Parser parser = new Parser(turtle);
    if (value != 0) {
      parser.parse(trueBlock.toString());
    } else {
      parser.parse(falseBlock.toString());
    }
    return 0;
  }
}
