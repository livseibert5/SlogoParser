package slogo.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Variable class stores the name and value of user defined variables.
 *
 * @author Rachel Luria
 */
public class Variable extends Value {

  private final String name;

  /**
   * Variable constructor takes in a name and value.
   *
   * @param name  name of variable
   * @param value value of variable
   */
  public Variable(String name, double value) {
    super(value);
    this.name = name;
  }

  /**
   * Variable constructor takes in a name when the value is not yet known.
   *
   * @param name name of variable
   */
  public Variable(String name) {
    super(0);
    this.name = name;
  }

  /**
   * Returns name of variable.
   *
   * @return name of variable
   */
  public String getName() {
    return name;
  }

  /**
   * Creates StringProperty from name for table updates.
   *
   * @return StringProperty property wrapping a string value
   * @author Jessica Yang
   */
  public StringProperty nameProperty() {
    StringProperty nameProperty = new SimpleStringProperty(this, "name");
    nameProperty.setValue(name);

    return nameProperty;
  }

  /**
   * Creates DoubleProperty from value for table updates.
   *
   * @return DoubleProperty property wrapping a double value
   * @author Jessica Yang
   */
  public DoubleProperty valueProperty() {
    DoubleProperty valueProperty = new SimpleDoubleProperty(this, "value");
    valueProperty.setValue(getValue());

    return valueProperty;
  }
}
