package slogo.model.parser;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import slogo.controller.Controller;
import slogo.model.backendexceptions.RegexDetectorError;

/**
 * RegexDetector takes in a String and determines what type of statement the String is based on the
 * properties files in resources.language.
 *
 * @author Livia Seibert
 */
public class RegexDetector {

  private static final String RESOURCES_PACKAGE = "resources.languages.";
  private final String ERROR_MESSAGE = "RegexDetector didn't find a match for %s.";
  private List<Entry<String, Pattern>> mySymbols;

  /**
   * Constructor for RegexDetector object.
   */
  public RegexDetector() {
    mySymbols = new ArrayList<>();
  }

  /**
   * Lets parser add resource bundles to RegexDetector to be used to match Strings to types.
   *
   * @param syntax name of bundle to be added to RegexDetector
   */
  public void addPatterns(String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  /**
   * Retrieves the pattern match for a String so that Parser can use the type to build its syntax
   * tree.
   *
   * @param text String to determine the type of
   * @return type match of text
   * @throws RegexDetectorError if text doesn't have a pattern match
   */
  public String getSymbol(String text) throws RegexDetectorError {
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    throw new RegexDetectorError(ERROR_MESSAGE, text);
  }

  /**
   * Determines if a String matches the given regex pattern.
   *
   * @param text  String to be matched
   * @param regex potential regex match
   * @return true if String matches regex pattern
   */
  private boolean match(String text, Pattern regex) {
    return regex.matcher(text).matches();
  }
}
