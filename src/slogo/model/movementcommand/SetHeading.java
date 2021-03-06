package slogo.model.movementcommand;

import slogo.model.Command;
import slogo.model.Turtle;

public class SetHeading implements Command {

    double orientation;
    private Command argument;

    public SetHeading(Command argument){
        this.argument = argument;
    }

    @Override
    public int getNumberParameters() {
        return 1;
    }

    @Override
    public int execute(Turtle turtle) {
        this.orientation = argument.execute(turtle);
        turtle.setOrientation(orientation);
        return (int) orientation;
    }
}
