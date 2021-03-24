SLogo
====

This project implements a development environment that helps users write programs to draw using a turtle.

Names:
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### Timeline

Start Date: February 25, 2021

Finish Date: March 21, 2021

Hours Spent: 250 hours

### Primary Roles

Livia - I worked on the backend of the project with Rachel. Initially, we planned to split it by having
one of us work on commands and one of us work on the parser, but we ended up collaborating on most pieces
of the back end.

Rachel - I worked on the backend of the project with Livia. I collaborate with her on any issues she was
having with the parser and also worked on the commands, handlers and rest of backend.

### Resources Used

[Reflections](https://docs.oracle.com/javase/tutorial/reflect/index.html)
[Design Patterns](http://www.oodesign.com)

### Running the Program

Main class: Main is the class to run to start the program.

Data files needed: 03_21_21_12_07.xml for FromXMLTest class

Features implemented:
- All basic commands
- Display commands
- Multiple turtle commands
- Letting users save user defined commands and variables to XML
- Letting users load in an XML file to set up user defined commands and variables
- Recursion
- Grouping


### Notes/Assumptions

Assumptions or Simplifications:

An assumption that was made for grouping was that only one set of parenthesis
could be used. When there are multiple parenthesis grouping only works on certain 
conditions and not entirely correctly.

Interesting data files:

The XML files in the repo show the layout for how user defined commands and variables are loaded
in to the simulation.

Known Bugs:

I accidentally used the Constant typecast instead of the Value typecast in UserDefinedCommand. For
that reason, the parser doesn't work when you pass a user defined command a variable as input. To fix
it, switch (Constant) in line 84 of UserDefinedCommand to (Value).

Extra credit:


### Impressions
I think that this project was a really important learning experience due to how many new concepts we
were able to apply to it. Design patterns and reflections were completely new to me at the beginning
of this project, and it was cool to see how using them made our project's design much more elegant.
