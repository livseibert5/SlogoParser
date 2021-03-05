package slogo.model;

public class CommandFactory {
  public CommandFactory() {

  }

  public Command createCommand(String commandType) {
    return new Forward();
  }
}
