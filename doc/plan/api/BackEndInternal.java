/**
 * The purpose of this interface is for the backend to properly execute the commands given from the user.
 * The commands are parsed by the backend external in a way that successfully allows for the backend
 * internal to return the correct values to the frontend.
 */
interface BackEndInternal {

    /**
     * determines the location of the turtle
     *
     * @param direction direction turtle is moving in
     * @param steps     number of steps the turtle is moving
     * @return coordinate pair
     */
    public int[] determineLocation(String direction, int steps);

    /**
     * determines the angle orientation of the turtle
     *
     * @param degrees the turtle is turning
     * @return degrees the turtle is facing
     */
    public double determineOrientation(double degrees);

    /**
     * turns the turtle towards the coordinates in the parameters
     *
     * @param x coordinate
     * @param y coordinate
     */
    public void turnTurtle(int x, int y);

    /**
     * Sets the pen up and down
     *
     * @param penUp true if pen should be put up
     */
    public void setPen(boolean penUp);

    /**
     * Determines whether or not to show the turle on the screen
     *
     * @param show true if the turtle should be shown on the screen
     */
    public void showTurtle(boolean show);

    /**
     * Adds expr1 and expr2 together
     *
     * @param expr1
     * @param expr2
     * @return sum
     */
    public double addValues(boolean add, double expr1, double expr2);

    /**
     * Multiplies expr1 and expr2 together
     *
     * @param expr1
     * @param expr2
     * @return quotient
     */
    public double multiplyValues(double expr1, double expr2);

    /**
     * Finds the mod of expr1 and expr2
     *
     * @param expr1
     * @param expr2
     * @return mod
     */
    public double modValues(double expr1, double expr2);

    /**
     * Returns a random value under a threshold
     *
     * @param max value of the random number
     * @return random number
     */
    public double getRandom(double max);

    /**
     * Performs trigonmetric operations
     *
     * @param degrees to go into trig function
     * @return answer
     */
    public double doTrig(double degrees);

    /**
     * Finds the log of expr
     *
     * @param expr
     * @return log
     */
    public double log(double expr);

    /**
     * finds base to the power of the exponent
     *
     * @param base
     * @param exponent
     * @return answer
     */
    public double pow(double base, double exponent);

    /**
     * Finds the value of pi
     *
     * @return pi
     */
    public double getPi();

    /**
     * Finds if expr1 is less than expr2
     *
     * @param expr1
     * @param expr2
     * @return 1 if expr1 is less than expr2, otherwise 0
     */
    public int lessThan(double expr1, double expr2);

    /**
     * Finds if expr1 is equal to expr2
     *
     * @param expr1
     * @param expr2
     * @return 1 if expr1 is equal to expr2, otherwise 0
     */
    public int equal(double expr1, double expr2);

    /**
     * ANDs test1 and test2
     *
     * @param test1
     * @param test2
     * @return 1 if test1 and test2 are non-zero, otherwise 0
     */
    public int and(double test1, double test2);

    /**
     * ORs test1 and test2
     *
     * @param test1
     * @param test2
     * @return 1 if test1 and test2 are non-zero, otherwise 0
     */
    public int or(double test1, double test2);

    /**
     * NOTs test
     *
     * @param test
     * @return 1 if test is non-zero, otherwise 0
     */
    public int not(double test);

    /**
     * Makes expr equal to existing or new variable
     *
     * @param expr
     * @param variable
     * @return expr
     */
    public double makeAndSet(double expr, double variable);

    public double repeat()

}