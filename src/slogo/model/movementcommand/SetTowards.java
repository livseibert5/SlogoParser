package slogo.model.movementcommand;

import slogo.model.Command;

public class SetTowards implements Command {

    int x;
    int y;
    public SetTowards(Command argument1, Command argument2){
        this.x = argument1.execute();
        this.y = argument2.execute();
    }

    @Override
    public int getNumberParameters() {
        return 2;
    }

    @Override
    public int execute() {
        int xdiff = x - turtle.getX();
        int ydiff = y - turtle.getY();
        int degree = (int) Math.atan(ydiff/xdiff);
        turtle.setOrientation(degree);
        return degree;
    }
}
