package slogo.model;

import slogo.model.movementcommand.Forward;

public class CommandFactory {
  public CommandFactory() {

  }

  public Command createCommand(String commandType) {
    return new Forward(new Constant(50));
  }
}
