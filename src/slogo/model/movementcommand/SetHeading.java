package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Constant;
import slogo.model.Turtle;

/**
 * SetHeading is the Command subclass that lets the user set a new orientation for
 * the Turtle object.
 *
 * @author Livia Seibert and Rachel Luria
 */
public class SetHeading implements Command {

    double orientation;
    private Command argument;

    /**
     * Constructor for the SetHeading command, takes a constant as an argument
     * and later gets its value for the new orientation by executing the argument.
     *
     * @param argument Constant command containing value for new orientation
     */
    public SetHeading(Constant orientation){
        this.orientation = orientation.getValue();
    }

    /**
     * Allows access to number of parameters SetHeading requires so Parser can determine when
     * the proper argument is available to execute the set heading command.
     *
     * @return 1, as SetHeading's only parameter is the new orientation
     */
    @Override
    public int getNumberParameters() {
        return 1;
    }

    /**
     * Set's turtle's orientation to be the new orientation specified by the argument.
     *
     * @param turtle turtle object to change orientation of
     * @return new orientation
     */
    @Override
    public int execute(Turtle turtle) {
        turtle.setOrientation(orientation);
        return (int) orientation;
    }
}
