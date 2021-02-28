# API Lab Discussion
# SLogo API Design

## Names and NetIDs
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### Planning Questions

* What behaviors (methods) should the turtle have?

The turtle should have a move forward message, as well as a turn method that
changes the orientation of the turtle. The turtle should also have pen up and
pen down methods so that it can start and stop drawing as it moves.

* When does parsing need to take place and what does it need to start
  properly?
  
Parsing needs to take place once the user hits enter after typing a command
into the text editor. It needs a command and a parameter in order to determine
what to make the turtle do.

* What is the result of parsing and who receives it?

Parsing takes the input and breaks it down into the core pieces. Based on these
pieces of the command, the parser sends the data to the controller so that it can
handle the command and display the result on the front end.

* When are errors detected and how are they reported?

Errors will be detected after parsing and will be reported by an Exceptions class.

* What do commands know, when do they know it, and how do they get it?

The commands need to know parameters, for example if it's a movement method it needs
to know how far to move. They will know this information when a command is parsed and
the method is called and passed the data. 

* How is the GUI updated after a command has completed execution?

When a command is entered, it is parsed and sent to the controller, which calls the
turtle command, which then passes the start and end location to the GUI so it can
animate the turtle moving from its start to end position.

* What behaviors does the result of a command need to have to be used
  by the front end?
  
The result of a command needs to specify the start and the end location of the turtle
so that the GUI can animate its motion.

### APIs

#### Backend External API

int[] getLocation()

double getOrientation()

double[] getPenColor()

#### Frontend External API

getUserInput()

#### Backend Internal API

parse(String command)

#### Frontend Internal API

moveTurtle(int x, int y)

turnTurtle(double degrees)

Color changePenColor(double r, double g, double b)

createTerminal()

animateMovement()

createScene()


### Design

#### Backend Design CRCs

Parser
parse(String command)

Exceptions
getErrorMessage

Turtle
int[] getLocation()
double getOrientation()
double[] getPenColor()

#### Frontend Design CRCs

TurtleGraphic
moveTurtle(int x, int y)
turnTurtle(double degrees)
Color changePenColor(double r, double g, double b)

View
createTerminal()
animateMovement()
createScene()

#### Use Cases

* The user types 'fd 50' in the command window, sees the turtle move in the display window leaving a trail, and has the command added to the environment's history.

When the user types the command, it is sent to the parser, which breaks it into its components, fd and 50. The Turtle will calculate the next location based on
its orientation and the distance set to move. The front end will then be passed the turtles start location and its end location and animate
the view based on this information. The view will also display the old command in the log on the front end.

* The user types '50 fd' in the command window and sees an error message that the command was not formatted correctly.

The parser will receive the command from the front end and realize that it is not in a form that it knows how to use. This will generate an
exception from our Exceptions class that will be displayed on the front end.

* The user types 'pu fd 50 pd fd 50' in the command window and sees the turtle move twice (once without a trail and once with a trail).

The parser will take the command and divide it into two commands based on the parameters. For the first command, the Turtle will put the
pen up so that it doesn't mark the screen while it moves. Then it will put the pen back down before executing the other one.

* The user changes the color of the environment's background.

When the new pen color is specified, the Turtle will change the color on the back end and make it accessible to the front end to use to draw new lines.

