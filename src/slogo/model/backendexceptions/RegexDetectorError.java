package slogo.model.backendexceptions;

public class RegexDetectorError extends RuntimeException {

  public RegexDetectorError(String message, Object ... values) {
    super(String.format(message, values));
  }
}
