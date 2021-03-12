package slogo.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for CommandBlock Class
 *
 * @author Rachel Luria
 */
public class CommandBlockTest {

  CommandBlock commandBlock;

  @Test
  void testCommandBlocktoString() {
    commandBlock = new CommandBlock("fd");
    assertEquals("fd", commandBlock.toString());
  }

}
