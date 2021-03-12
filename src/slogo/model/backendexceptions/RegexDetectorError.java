package slogo.model.backendexceptions;

/**
 * Error class for when the RegexDetector class cannot find a pattern
 * match for a String.
 *
 * @author Livia Seibert
 */
public class RegexDetectorError extends RuntimeException {

  /**
   * Throws a new error with the given message and value.
   *
   * @param message message to be thrown upon regex error
   * @param values value that caused the regex error
   */
  public RegexDetectorError(String message, Object ... values) {
    super(String.format(message, values));
  }
}
