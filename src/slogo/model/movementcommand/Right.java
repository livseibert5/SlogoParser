package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * Right class is the Command subclass that determines the end location of
 * the turtle when moved right by a certain number of pixels.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class Right extends MovementCommand implements Command {

    private int pixels;

    /**
     * Constructor for the Right command, takes a constant as an argument
     * and gets its value by executing the Constant command.
     *
     * @param pixels Constant containing the number of pixels to move the turtle
     */
    public Right(Constant pixels) {
        this.pixels = pixels.getValue();
    }

    /**
     * Turns turtle to the right, then calculates the end location of the turtle based on the
     * number of pixels passed as an argument.
     *
     * @param turtle turtle object to execute command on
     * @return number of pixels moved
     */
    public double execute(Turtle turtle) {
        turtle.setOrientation(turtle.getOrientation() - 90);
        turtle.setLocation(determineLocation(turtle.getLocation(), Math.toRadians(turtle.getOrientation()), pixels));
        return pixels;
    }
}
