package slogo.model.displaycommand;

import slogo.controller.Controller;
import slogo.model.Color;
import slogo.model.Command;
import slogo.model.Turtle;
import slogo.model.Value;

/**
 * SetPalette is the command subclass that lets the user define a color by inputting
 * the rbg values and an index for the color to be added to
 *
 * @author Rachel Luria
 */
public class SetPalette implements Command {

  private Controller controller;
  private double index;
  private double r;
  private double b;
  private double g;

  /**
   * SetPenColor constructor takes a value that corresponds to the index of the new
   * pen color.
   *
   * @param controller controller for the current game
   * @param index of the list the rbg color will be added to
   * @param r value of rbg scale
   * @param g value of rbg scale
   * @param b value of rbg scale
   */
  public SetPalette(Controller controller, Value index, Value r, Value g, Value b) {
    this.controller = controller;
    this.index = index.getValue();
    this.r = r.getValue();
    this.g = g.getValue();
    this.b = b.getValue();
  }

  /**
   * Sets the turtle's list of color's at a specified index to the specified
   * rgb color
   *
   * @param turtle turtle object to execute command on
   * @return index of new pen color
   */
  @Override
  public double execute(Turtle turtle) {
    Color color = new Color((int) r, (int) g, (int) b);
    controller.getColorHandler().addColor((int) index, color);
    return index;
  }

}
