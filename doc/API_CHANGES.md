## SLogo API Changes
### Team 6
### Names
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### Changes to the Initial APIs

#### Backend External

* Method changed: getParserResult()

    * Why was the change made? This method was not included at all. We just had the parse()
    function return its result directly.

    * Major or Minor (how much they affected your team mate's code) Minor, if anything this
    change made our code easier because it eliminated an extra function call.

    * Better or Worse (and why) I think that having parse return its result directly is better
    because it is a much more simple implementation.


* Method changed: getVariables(), getTurtle(), getUserCommands(), setLanguage()

    * Why was the change made? We changed these methods to be accessed through a controller
    instead of directly through the back end.

    * Major or Minor (how much they affected your team mate's code) Minor, instead of just
    turtleHandler.getTurtle() the front end has to call controller.getTurtleHandler().getTurtle().

    * Better or Worse (and why) Better, the controller bundles all the back end handlers
    into one place so that the front end and back end can both access the same objects.


#### Backend Internal

* Method changed: execute()

    * Why was the change made? We added this method after the design phase. The parser needs to
    execute commands directly, so execute is a back end internal API method that lets the parser
      trigger commands.

    * Major or Minor (how much they affected your team mate's code) Minor, while this is crucial to
    the functionality of the parser, we didn't have to change anything to implement it, we just hadn't
      thought through that piece of the code yet while we were writing the plan.

    * Better or Worse (and why) Better, we need this method to execute functions.


* Method changed: determineLocation

    * Why was the change made? determineLocation was meant to be a Turtle function, but it actually
    made more sense to just set the turtle's location directly from each command. That way, the determineLocation
      calculations are done within the command instead of within the turtle.

    * Major or Minor (how much they affected your team mate's code) Minor, this change only affected
    a small piece of the back end because the front end doesn't need to be concerned with the implementation
      details of how the turtle's location is updated.

    * Better or Worse (and why) Better, each movement function can update the turtle as it sees fit
    without relying on a generalized determineLocation function in the turtle.


#### Frontend External

* Method changed:  the back end doesn't use any front end external methods

    * Why was the change made? Instead of having the back end call front end external commands
    directly, we used the Observable pattern to allow the front end to detect back end changes
      itself and implement the proper display functionality.

    * Major or Minor (how much they affected your team mate's code) Major, the back end no longer
    has to worry about updating the display at all and instead can just update the properties of
      the backend turtle object.

    * Better or Worse (and why) Better, helps keep the front end and back end separate as the back
    end no longer depends on the front end.


#### Frontend Internal

* Method changed: moveTurtle(), turnTurtle() --> updateTurtleView()

    * Why was the change made? This change was made to condense the turtle update functionality
    into one method.

    * Major or Minor (how much they affected your team mate's code) Minor, doesn't affect anything
    outside of this function.

    * Better or Worse (and why) Better, moves the method into a specialized class meant for turtle
    views specifically.


* Method changed: changePenColor()

    * Why was the change made? This is now part of buttonHandler, and it just calls the Observable.

    * Major or Minor (how much they affected your team mate's code) Minor, uses observables instead
    of just calling methods directly.

    * Better or Worse (and why) Better, as changes are being synchronized more efficiently.