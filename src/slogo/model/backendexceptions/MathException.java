package slogo.model.backendexceptions;

/**
 * Error class for when an invalid input is entered by the user for a math command
 *
 * @author Rachel Luria
 */
public class MathException extends Exception {

  /**
   * Throws a new error with the given message and value.
   *
   * @param message message to be thrown upon regex error
   */
  public MathException(String message) {
    super(String.format(message));
  }
}
