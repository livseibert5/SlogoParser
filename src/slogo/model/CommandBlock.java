package slogo.model;

/**
 * CommandBlock contains a series of commands to be stored for later use. Specifically,
 * this class is useful for the control commands to execute a block of commands within a
 * loop or if statement.
 *
 * @author Livia Seibert
 */
public class CommandBlock {

  private String commandBlock;

  /**
   * Constructor for CommandBlock, takes a String of commands.
   *
   * @param commandBlock String with sequence of commands in it
   */
  public CommandBlock(String commandBlock) {
    this.commandBlock = commandBlock;
  }

  /**
   * Returns the String of commands stored inside CommandBlock.
   *
   * @return String with commands
   */
  @Override
  public String toString() {
    return commandBlock;
  }
}
