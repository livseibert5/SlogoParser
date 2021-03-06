package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

/**
 * SetTowards is the Command subclass that lets the user specify a coordinate that it
 * wants the turtle to face, and then sets the turtle's orientation to face this location.
 *
 * @author Rachel Luria and Livia Seibert
 */
public class SetTowards implements Command {

    int x;
    int y;
    Command argument1;
    Command argument2;

    public SetTowards(Command argument1, Command argument2){
        this.argument1 = argument1;
        this.argument2 = argument2;
    }

    @Override
    public int getNumberParameters() {
        return 2;
    }

    @Override
    public int execute(Turtle turtle) {
        this.x = argument1.execute(turtle);
        this.y = argument2.execute(turtle);
        int xdiff = x - turtle.getXCoordinate();
        int ydiff = y - turtle.getYCoordinate();
        int degree = (int) Math.atan(ydiff/xdiff);
        turtle.setOrientation(degree);
        return degree;
    }
}
