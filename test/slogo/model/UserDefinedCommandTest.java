package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for UserDefinedCommand Class
 *
 * @author Rachel Luria
 */
public class UserDefinedCommandTest {

  UserDefinedCommand command;
  CommandBlock block;

  @Test
  void testGetCommandName() {
    block = new CommandBlock("fd");
    command = new UserDefinedCommand("move", 2, new String[]{"sum", "difference"}, block);
    assertEquals("move", command.getCommandName());
  }

  @Test
  void testGetNumberParameters() {
    block = new CommandBlock("fd");
    command = new UserDefinedCommand("move", 2, new String[]{"sum", "difference"}, block);
    assertEquals(2, command.getNumberParameters());
  }

  @Test
  void testGenerateCommand() {
    List<Object> runtimeParameters = new ArrayList<>();
    runtimeParameters.add(new Constant(5));
    runtimeParameters.add(new Constant(3));
    block = new CommandBlock("fd");
    command = new UserDefinedCommand("move", 2, new String[]{"sum", "difference"}, block);
    assertEquals("fd", command.generateCommand(runtimeParameters));
  }

}
