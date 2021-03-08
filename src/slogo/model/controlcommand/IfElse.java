package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Parser;
import slogo.model.Turtle;

public class IfElse implements Command {

  private int value;
  private CommandBlock trueBlock;
  private CommandBlock falseBlock;

  public IfElse(Constant value, CommandBlock trueBlock, CommandBlock falseBlock) {
    this.value = value.getValue();
    this.trueBlock = trueBlock;
    this.falseBlock = falseBlock;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(turtle);
    if (value != 0) {
      parser.parse(trueBlock.toString());
    } else {
      parser.parse(falseBlock.toString());
    }
    return 0;
  }
}
