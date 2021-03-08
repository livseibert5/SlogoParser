package slogo.model.controlcommand;

import java.lang.reflect.InvocationTargetException;
import slogo.model.Command;
import slogo.model.CommandBlock;
import slogo.model.Constant;
import slogo.model.Parser;
import slogo.model.Turtle;

public class Repeat implements Command {

  private int count;
  private CommandBlock list;

  public Repeat(Constant argument, CommandBlock list) {
    count = (int) argument.getValue();
    this.list = list;
  }

  @Override
  public double execute(Turtle turtle)
      throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    Parser parser = new Parser(turtle);
    for(int i = 0; i < count; i++){
      return parser.parse(list.toString());
    }
    return 0;
  }
}
