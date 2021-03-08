package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Parser;
import slogo.model.Turtle;

public class If implements Command {

  private int value;
  private CommandBlock trueBlock;

  public If(Constant value, CommandBlock trueBlock) {
    this.value = value.getValue();
    this.trueBlock = trueBlock;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    if (value != 0) {
      Parser parser = new Parser(turtle);
      parser.parse(trueBlock.toString());
    }
    return 0;
  }
}
