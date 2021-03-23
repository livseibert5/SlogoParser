# DESIGN Document for SLogo

## NAME(s)
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

## Role(s)

Livia - I worked on the backend of the project with Rachel. Initially, we planned to split it by having
one of us work on commands and one of us work on the parser, but we ended up collaborating on most pieces
of the back end.

Rachel - I worked on the backend of the project with Livia. I collaborate with her on any issues she was
having with the parser and also worked on the commands, handlers and rest of backend.

## Design Goals

The main goal of the back end design was to make the functionality as extensible as possible. We inferred
that for the complete implementation there would be many more types of commands to implement, meaning that
adding a new command needed to be as simple as possible. We were able to achieve this by creating a Command
interface that all command classes implemented. That way, the parser could handle any objects that implement
the Command interface and use their polymorphic execute function to make them run.

Another design goal was good communication between the front end and the back end. The execution of commands
on the back end needed to initiate a change in the view, and variables and user defined commands needed to be
displayed. We handled this communication with a Controller class that allowed the front end and the back end
to both access the same turtle handler, variable handler, and user defined command handler. We also used the
Observable pattern to help with front end/back end communication, which helped a lot with updating the display.

## High-Level Design

As mentioned previously, the core of the back end is the Command classes and the parser. The Command interface
made it possible to create dozens of command classes, each with their own functionality. Since all the command
classes share the same execute function, the Parser adheres to the Liskov Substitution principle and uses the
Command type to handle every different type of command. Command classes each have their own types of parameters,
which are usually Values (Variables or Constants), or CommandBlocks. Some command types also need the controller,
such as the multiple turtle commands. In order to create the correct command types and pass these commands the
proper types of parameters, we created the CommandFactory. The CommandFactory uses reflections to create the correct
command type class based on the name of the command as detected by the RegexDetector class. The CommandFactory can
also detect the number of arguments a command class is expecting and what type they are. Using the functions provided
by the CommandFactory, the Parser can create these new types and pass them parameters from the command input. When
the commands are executed, they update the turtle objects on the back end.

Once the turtle object's data is updated on the back end, it needs to be passed to the front end. One way this happens
in our project is through the Controller. The Controller holds references to the TurtleHandler, ColorHandler, VariableHandler,
and UserDefinedCommandHandler. The front end creates the Controller class and passes it to the Parser so that the front
end and back end refer to the same controller and therefore the same handlers. That way, when the back end updates
a turtle, adds a new command, etc., the front end can tell that the object has changed and update the view accordingly.
We also used the Observable pattern to help with those updates, as the front end can then detect every time the turtle's
location changes instead of just waiting to see its location at the end of a complete execution. The Observable pattern
also helped with the second week extension, as we then had to add a way for the user to move the turtle with manual front
end input. Since we already had the front end updating from back end changes using the Observable pattern, it was easy to
add listeners for the back end model to change locations based on key input from the front end, which in turn updated
the turtle graphic.

The back end also has functionality to write a current set of user defined variables and commands to an XML file, and
then to read in an XML file of user defined variables and commands and use them in the program. The classes that create
this functionality don't impact other pieces of the code and are enacted through a button on the front end, so they were
a very simple addition to the design.

## Assumptions or Simplifications

An assumption that was made for grouping was that only one set of parenthesis
could be used. When there are multiple parenthesis grouping only works on certain
conditions and not entirely correctly.

## Changes from the Plan

Our design changed a decent amount from the plan. Since we were new to the concept of APIs, we weren't really sure about
how to divide the project into four APIs in a way that made sense. Due to that lack of clarity, we envisioned that the Turtle
would make sense as a front end class and that the back end would use a bunch of front end external API calls from the
front end turtle to update the view. However, after more discussions amongst ourselves and with our TAs, we quickly realized
that putting the turtle class on the front end didn't make much sense. The back end is where data should be held, and the front
end should react to changes in that data, otherwise the two ends get too tightly coupled and have a very complicated back and
forth with the data. Once we realized this, we decided that we could get rid of the front end external API altogether. Instead, we
use the Observable pattern to update the front end based on changes to the model from the execution of commands. Similarly, we
thought that we'd have to use a lot more back end external API commands, but we were able to use the controller instead as an
intermediary between the front end and the back end data. We also ended up deciding to make every Command its own class instead
of grouping them together by type in order to use polymorphism and the Liskov Substitution Principle.

## Adding New Commands

To add a new command, we first need to make a new class for the command that implements the Command interface.
All the functionality of the command is written within the .execute method from the Command interface, and a
constructor should be defined and passed the proper argument types that the command expects. Then, tge class path
for this command would have to be added to the ExpressionFactory properties file, and the command itself would have
to be put in the English properties file. The parser will not have to be altered at all for new command types.
