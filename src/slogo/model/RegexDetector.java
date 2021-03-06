package slogo.model;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class RegexDetector {
  private static final String RESOURCES_PACKAGE = RegexDetector.class.getPackageName() + ".resources.languages.";
  private List<Entry<String, Pattern>> mySymbols;

  public RegexDetector() {
    mySymbols = new ArrayList<>();
  }

  public void addPatterns (String syntax) {
    ResourceBundle resources = ResourceBundle.getBundle(RESOURCES_PACKAGE + syntax);
    for (String key : Collections.list(resources.getKeys())) {
      String regex = resources.getString(key);
      mySymbols.add(new SimpleEntry<>(key, Pattern.compile(regex, Pattern.CASE_INSENSITIVE)));
    }
  }

  public String getSymbol (String text) {
    final String ERROR = "NO MATCH";
    for (Entry<String, Pattern> e : mySymbols) {
      if (match(text, e.getValue())) {
        return e.getKey();
      }
    }
    // FIXME: perhaps throw an exception instead
    return ERROR;
  }

  private boolean match (String text, Pattern regex) {
    return regex.matcher(text).matches();
  }
}
