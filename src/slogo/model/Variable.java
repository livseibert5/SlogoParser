package slogo.model;

/**
 * Variable class stores the name and value of user defined variables.
 *
 * @author Rachel Luria
 */
public class Variable extends Expression {

  private String name;

  /**
   * Variable constructor takes in a name and value.
   *
   * @param name name of variable
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
}
