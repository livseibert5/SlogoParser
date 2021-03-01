# SLogo Design Plan
### Team Number
### Names
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

## Introduction


## Overview

Front End Internal
The front end internal API would handle display functionality that does not have to do with the turtle.
This includes the IDE, the display of command history, and the layout of the UI as a whole.

createTerminal()
createScene()
addToLog()

One way we are thinking of creating the IDE is through using the JavaFX text input box. Once the user
types a command, the command will execute, and a new text box will become available on the next line.
We are thinking that the command could either appear in the terminal itself as history or in a box off to
the side with all past commands. The terminal will appear as split screen with the canvas containing the turtle.

Front End External
The front end external API will contain functions that handle displaying the turtle and its movement
across the screen. It will need to contain functions such as penUp, penDown, changeColor, moveTurtle, etc.

moveTurtle(int x, int y)
turnTurtle(double degrees)
penUp()
penDown()
Color changePenColor(double r, double g, double b)

Once the back end parses a command and determines what kind of action needs to be taken, it can use these
front end external commands to implement that action on the screen. That way, the data would only have to
be handled once in the backend rather than passed all the way back to the front end again after parsing.

Another way that we thought about handling the command on the front end was by creating a class that had instance
variables such as turtleStartLocation, turtleEndLocation, turtleOrientation, etc. Then the front end could
use this information to update the view. However, we think that this would be inefficient when the back end
could just call the front end external commands itself.

Back End Internal
The back end internal API will handle parsing commands and determining how they should be executed.

int[] determineLocation()
double determineOrientation()

We will divide up the commands into classes based on their type. There will be a parent Command class,
and then subclasses for each general type of command, which include both
visual and movement turtle command types, as well as math operations, boolean operations, user-defined
commands, etc. We think this will be an efficient way to divide up our classes because it encourages
flexibility, as the parent Command class will let us extend its functionality to add more types of commands
without altering already-existing classes.

Another potential idea for structuring the command classes is to use an interface. As each type of command
will have different functionality, it will be hard to create a parent class that defines common functionality
for every type of command. Therefore, an interface would let us declare methods that each command type
should have without including any behavior.

Back End External
The back end external will API will contain the parse function so that the front end can pass a String
containing the command to the back end to be handled.

parse(String command)
getParserResult()

One way that we thought about returning the command to the front end was as a String. This String would
contain the result of parsing and executing the command on the back end and would then be displayed in the
terminal.

## User Interface

The main components of the front end will be the IDE and the canvas with the turtle on it. The IDE will
use text input fields to take in commands and then add them to a log of past commands. The canvas will just
be a blank pane with the turtle on it that responds to the results of commands. When unrecognized commands
are entered into the IDE, an error box will pop up on the front end telling the user that their command was
invalid.

## Design Details

Since the front end consists of two major components, the IDE and the canvas, we will have a class for each 
of those two features and then another main front end class that ties the canvas class and the IDE class together
and displays them on the main screen. That way, the IDE class can handle sending the input command to the backend,
and the backend can then send calls to the canvas class to update its view. This means that the IDE class and
the main front end class are part of the front end internal API, and the canvas class is part of the front end
external API.

When the IDE class registers that the user clicked the enter button, it will call the parse function from the
back end external API and pass the command in as a string. Then, the parser will determine what type of command
it has received and pass it into a Command class, which is part of the back end internal API, to be handled.

Once the command has been parsed and the command type has been determined, the Command class can then call the front
end external API methods as needed to update the front end according to that command. These front end external
API calls will alter the layout of the canvas on the front end, but not interfere with the IDE or general layout
of the view at all.

## Test Plan

Front End External
Possible Issue: Move command is being called, but the turtle is staying still. We can test this by calling the
move function manually from a test file, then making sure that the turtle's end location is where we would expect it
to be. This wouldn't throw an  Exception, but it is a bug that would impact the functionality of the project.

Possible Issue: Coordinate system is off and the turn turtle function is giving the turtle the wrong orientation.
We can test this by calling the turn function on the turtle with a known degree input. We can then check the resulting
orientation of the turtle and make sure it is what we expect.

Possible Issue: Turtle is being moved outside the grid. To check this, we can start the turtle at the edge of the
grid and have it move forward an amount that would push it off the edge of the grid. The expected behavior would be
for the turtle to move as far as it could, and then stop before exiting its grid.

Front End Internal
Possible Issue: A command is being entered into the IDE but not appearing in the history log. We can test this by
having the test file pass a command to the IDE, then checking to make sure the text corresponding to the command
is found in the command history box.

Possible Issue: IDE isn't being built when the program is started. We can test this by creating an instance of the
main view class in the test file and ensuring that the IDE class is being instantiated inside it.

Possible Issue: The IDE text box isn't actually taking in commands. We can test this by putting known commands
into the box and checking that inputting these commands is causing an action rather than just adding them to the log
and doing nothing.

Back End External
Possible Issue: Parse command is passed a String, but the String is in a format it doesn't recognize (for example,
it has extra spaces). We could check this by calling the parse command on commands with extra spaces and other
fixable syntax commands, and making sure that these issues are handled properly so that the command can still be read.

Possible Issue: The parser isn't passing back the appropriate result of the command. To test this, we can put
known commands in our test file and then ensure that the expected output is being printed to the terminal.

Possible Issue: Correctly formatted command isn't working. To test this, we can put properly formatted commands into
our test files and make sure that they are being handled on the back end without exceptions. We can also check to
make sure that the resulting turtle motion has occurred and the correct result has been printed in the terminal.

Back End Internal
Possible Issue: Command is executing improperly, and the front end external API commands are not getting called at all.
We could test this by sending known commands to the Command classes and then making sure the turtle's end location/position
match their expected end location/position.

Possible Issue: The parser receives a command it does not know. When this happens, we want the parser to display a
text box with the error. We could test this by putting an incorrect command into the parser in the test file and
making sure that the box is displayed instead of just throwing an error to the command line.

Possible Issue: The parser is passed a command in other language that it should know, but it does not recognize the
command. This would be an issue with the way language resource bundles are handled on the back end. To test for this
to ensure it doesn't happen, we could write a test file with commands in each of the different languages our project
supports and then check that each resulted in the turtle moving to the appropriate location.

## Design Considerations

One major design consideration we had was how the data should flow through the project. One option was to instantiate
a Parser object on the front end so that the parse command from the backend external API could be called in the front end.
We were thinking that could cause encapsulation issues to have this piece of the backend in the front end, so we discussed
using a Controller class as a middle man to allow the front end and the back end to use each other's external APIs.
We also discussed whether the back end should call the front end external APIs when it is executing the command or
whether it should pass information about the result of the command back to the front end to be handled there. We think
that the back end should just call the front end external APIs while it is executing the command, as having the front end
parse the result of the command is overlapping functionality between the front end and the back end. Simply handling
execution in the back end eliminates this extra step and keeps the front end and back end more separate from each other.

## Team Responsibilities

* Team Member #1 Jessica Yang - main Graphics / Control classes

* Team Member #2 Livia Seibert - back end / parser

* Team Member #3 Montana Lee - front end / IDE

* Team Member #4 Rachel Luria - back end / command handling