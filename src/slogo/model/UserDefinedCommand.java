package slogo.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDefinedCommand {

  String commandName;
  int numberParameters;
  String[] parameters;
  CommandBlock body;
  Map<String, Integer> runtimeArgs;

  public UserDefinedCommand(String commandName, int numberParameters, String[] parameters, CommandBlock body) {
    this.commandName = commandName;
    this.numberParameters = numberParameters;
    this.parameters = parameters;
    this.body = body;
  }

  public String getCommandName() {
    return commandName;
  }

  public int getNumberParameters() {
    return numberParameters;
  }

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
    StringBuilder result = new StringBuilder();
    for (String commandItem: commandBody) {
      result.append(commandItem);
      result.append(" ");
    }
    result.deleteCharAt(result.length() - 1);
    return result.toString();
  }
}
