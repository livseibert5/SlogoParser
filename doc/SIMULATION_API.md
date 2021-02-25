# API Lab Discussion
# Cell Society API Discussion

## Names and NetIDs
Jessica Yang (JQY2)
Rachel Luria (rl213)
Montana Lee (mal115)
Livia Seibert (las120)

### MODULE_NAME API Motivation/Analogies

The motivation for the configuration API is to contain all of the XML parsing functionality and error handling.

#### External
createGrid
pause
start
next
fast
slow
Controller needs these methods to update the simulation.

#### Internal

There are no internal API methods because the data from configuration needs to be used in other places to make the simulation work.

### MODULE_NAME API Classes/Methods

The motivation of the view API is to display the simulation as it updates.

#### External
resetButton


#### Internal
createButton

### MODULE_NAME API Classes/Methods

The motivation of the Model API is to display each cell with its proper state.

#### External
viewGrid
updateCells
getState

#### Internal
findPotentialMoves
isValidState
prepareNextState
updateState
receiveUpdate
getBaseState
setMaxStateValue
getNextState
setNextState
