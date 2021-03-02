/**
 * Provides functionality for backend to affect turtle display, as well as control data
 *    flow across front end and back end.
 *
 * @author Jessica Yang
 */
interface FrontEndExternal {
  // animation: Timeline
  // javafx: Stage

  /**
   * Called by backend to move turtle graphic to a specific location.
   *
   * @param x coordinate
   * @param y coordinate
   */
  public void moveTurtle(int x, int y);

  /**
   * Called by backend to rotate turtle graphic by a specific degrees and orientation.
   *
   * @param degrees of rotation
   * @param direction of rotation
   */
  public void turnTurtle(double degrees, boolean counterClockWise);

  /**
   * Called by backend to stop turtle from drawing during movement.
   */
  public void penUp();

  /**
   * Called by backend to tell turtle to draw during movement.
   */
  public void penDown();

  /**
   * Called by backend to specify color of pen using RGB color code
   * @param r red
   * @param g green
   * @param b blue
   */
  public void changePenColor(double r, double g, double b);

  //private buildScene(); - adds everything to stage instantiated in constructor of Control
}