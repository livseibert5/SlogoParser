package slogo.model.handlers;

import java.util.List;
import javafx.collections.FXCollections;
import slogo.model.UserDefinedCommand;

/**
 * UserDefinedCommandHandler stores a list of user defined commands so that the back end can
 * retrieve them when they are used. The front end can also access these commands and display them.
 *
 * @author Livia Seibert
 */
public class UserDefinedCommandHandler {

  List<UserDefinedCommand> userDefinedCommandList;

  /**
   * Constructor for UserDefinedCommandHandler declares a list to hold the commands.
   */
  public UserDefinedCommandHandler() {
    userDefinedCommandList = FXCollections.observableArrayList();
  }

  /**
   * Allows parser to add a command to the handler when a new command is created.
   *
   * @param command command to be added to the list
   */
  public void addCommand(UserDefinedCommand command) {
    userDefinedCommandList.add(command);
  }

  /**
   * Returns the list of all user defined commands.
   *
   * @return list of all user defined commands
   */
  public List<UserDefinedCommand> getAllCommands() {
    return userDefinedCommandList;
  }

  /**
   * Retrieves the command from the list that has the given name.
   *
   * @param commandName name of command to retrieve
   * @return UserDefinedCommandObject with given name
   */
  public UserDefinedCommand getCommand(String commandName) {
    for (UserDefinedCommand command : userDefinedCommandList) {
      if (command.getCommandName().equals(commandName)) {
        return command;
      }
    }
    return null;
  }

  /**
   * Determines if a command with the given name exists.
   *
   * @param commandName name of command to determine existence of
   * @return true if command is in list, false otherwise
   */
  public boolean containsCommand(String commandName) {
    for (UserDefinedCommand command : userDefinedCommandList) {
      if (command.getCommandName().equals(commandName)) {
        return true;
      }
    }
    return false;
  }
}
