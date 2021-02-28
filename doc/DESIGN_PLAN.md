# SLogo Design Plan
### Team Number
### Names

#### Examples

Here is a graphical look at my design:

![This is cool, too bad you can't see it](online-shopping-uml-example.png "An initial UI")

made from [a tool that generates UML from existing code](http://staruml.io/).


Here is our amazing UI:

![This is cool, too bad you can't see it](29-sketched-ui-wireframe.jpg "An alternate design")

taken from [Brilliant Examples of Sketched UI Wireframes and Mock-Ups](https://onextrapixel.com/40-brilliant-examples-of-sketched-ui-wireframes-and-mock-ups/).


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


## Design Considerations


## Team Responsibilities

* Team Member #1

* Team Member #2

* Team Member #3

* Team Member #4