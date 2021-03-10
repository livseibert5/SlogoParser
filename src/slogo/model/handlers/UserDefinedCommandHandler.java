package slogo.model.handlers;

import java.util.ArrayList;
import java.util.List;
import slogo.model.UserDefinedCommand;

public class UserDefinedCommandHandler {

  List<UserDefinedCommand> userDefinedCommandList;

  public UserDefinedCommandHandler() {
    userDefinedCommandList = new ArrayList<>();
  }

  public void addCommand(UserDefinedCommand command) {
    userDefinedCommandList.add(command);
  }

  public UserDefinedCommand getCommand(String commandName) {
    for (UserDefinedCommand command: userDefinedCommandList) {
      if (command.getCommandName().equals(commandName)) {
        return command;
      }
    }
    return null;
  }

  public boolean containsCommand(String commandName) {
    for (UserDefinedCommand command: userDefinedCommandList) {
      if (command.getCommandName().equals(commandName)) {
        return true;
      }
    }
    return false;
  }
}
