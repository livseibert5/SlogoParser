package slogo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * UserDefinedCommand class stores information about a user defined command so it can be executed
 * again.
 *
 * @author Livia Seibert
 */
public class UserDefinedCommand {

  String commandName;
  int numberParameters;
  String[] parameters;
  CommandBlock body;
  Map<String, Integer> runtimeArgs;

  /**
   * Constructor for UserDefinedCommand class.
   *
   * @param commandName      name of command to define
   * @param numberParameters number of parameters of new command
   * @param parameters       list of parameter names
   * @param body             body of block to be executed
   */
  public UserDefinedCommand(String commandName, int numberParameters, String[] parameters,
      CommandBlock body) {
    this.commandName = commandName;
    this.numberParameters = numberParameters;
    this.parameters = parameters;
    this.body = body;
  }

  /**
   * Retrieves the name of the command.
   *
   * @return name of command
   */
  public String getCommandName() {
    return commandName;
  }

  /**
   * Retrieves the number of parameters the command has.
   *
   * @return number of parameters of command
   */
  public int getNumberParameters() {
    return numberParameters;
  }

  /**
   * Returns the command block for the command.
   *
   * @return command block with function commands
   */
  public CommandBlock getBody() {
    return body;
  }

  /**
   * Returns the parameters for the command.
   *
   * @return list of parameters for command
   */
  public String[] getParameters() {
    return parameters;
  }

  /**
   * Creates an executable command with the parameters that the user specifies at runtime.
   *
   * @param runtimeParameters arguments to be passed into execution of this command
   * @return String that can be parsed to execute user defined command
   */
  public String generateCommand(List<Object> runtimeParameters) {
    runtimeArgs = new HashMap<>();
    for (int i = 0; i < runtimeParameters.size(); i++) {
      runtimeArgs.put(parameters[i], (int) ((Constant) runtimeParameters.get(i)).getValue());
    }
    String[] commandBody = body.toString().split(" ");
    for (int i = 0; i < commandBody.length; i++) {
      if (runtimeArgs.containsKey(commandBody[i])) {
        commandBody[i] = runtimeArgs.get(commandBody[i]).toString();
      }
    }
    return buildString(commandBody);
  }

  /**
   * Turns array of commands back into a command string.
   *
   * @param commandBody list of commands to include in the string
   * @return new command string
   */
  private String buildString(String[] commandBody) {
    StringBuilder result = new StringBuilder();
    for (String commandItem : commandBody) {
      result.append(commandItem);
      result.append(" ");
    }
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }

  /**
   * Creates StringProperty from commandName for table updates.
   *
   * @return StringProperty property wrapping a string value
   * @author Jessica Yang
   */
  public StringProperty commandNameProperty() {
    StringProperty commandNameProperty = new SimpleStringProperty(this, "commandName");
    commandNameProperty.setValue(commandName);

    return commandNameProperty;
  }

  /**
   * Creates StringProperty from body for table updates.
   *
   * @return StringProperty property wrapping a string value
   * @author Jessica Yang
   */
  public StringProperty bodyProperty() {
    StringProperty bodyProperty = new SimpleStringProperty(this, "body");
    bodyProperty.setValue(body.toString());

    return bodyProperty;
  }
}
