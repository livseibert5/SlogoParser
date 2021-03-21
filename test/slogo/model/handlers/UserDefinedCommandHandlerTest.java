package slogo.model.handlers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import slogo.model.CommandBlock;
import slogo.model.UserDefinedCommand;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDefinedCommandHandlerTest {

  private UserDefinedCommandHandler commandHandler;

  @BeforeEach
  void setUp() {
    commandHandler = new UserDefinedCommandHandler();
  }

  @Test
  void testAddCommand() {
    CommandBlock block = new CommandBlock("fd");
    UserDefinedCommand command = new UserDefinedCommand("move", 2,
        new String[]{"sum", "difference"}, block);
    commandHandler.addCommand(command);
    assertTrue(commandHandler.containsCommand("move"));
    assertNotNull(commandHandler.getCommand("move"));
  }

  @Test
  void commandDoesntExist() {
    assertNull(commandHandler.getCommand("zebra"));
  }
}
