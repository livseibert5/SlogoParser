## Refactoring Lab Discussion
### Team 6
### Names
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### Issues in Current Code

#### Method or Class SceneComponents
* Design issues : SceneComponents class is pretty long and needs to be split into classes based on each component.

* Design issue :
SceneComponents needs to be more flexible to handle adding and moving components on the screen.


### Refactoring Plan

* What are the code's biggest issues?

There were a lot of variables in our code that didn't use the final keyword where they should have.
We had some other Intellij warning issues to fix as well, but nothing too major.

* Which issues are easy to fix and which are hard?

The Intellij warnings are very easy to fix, as clicking the error often fixes it automatically.
Overarching design issues are much harder to fix, as they have to do with the actual functionality
of the code.

* What are good ways to implement the changes "in place"?

Most of the changes we implemented were Intellij warning issues. All of these issues are easy to
fix in place as they only involve changes to syntax and don't affect other pieces of the code.


### Refactoring Work

* Issue chosen: Fix and Alternatives

We were missing final keywords in a lot of our instance variables, so we added them in. Using final
keywords makes the code more secure because the initially designed values can never be altered.

* Issue chosen: Fix and Alternatives

We were missing descriptions for lots of the throws tag, so we wrote in what each error that is
being thrown actually does and how it is caused.
