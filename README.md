A. The Environment

The ants’ environment should be represented using a 27 x 27square grid.
Each square in the grid represents a discrete location in theenvironment.
Eight directions of movement are possible (see diagrambelow).
The entrance to the colony is represented by a single squarelocated in the center of the grid. The queen is located in thissquare.
The remaining squares will initially be unexploredterrain.
Certain ants (scout ants) will be capable of revealing theareas that have not been explored.
All other types of ants will only be allowed to move intosquares that have been revealed by scout ants.
Each square can contain one or more of the following, in anycombination:
Zero or more enemy ants
Zero or more friendly ants
Zero or more units of food
Zero or more units of pheromone
B. Ant Types

There are five types of ants in the simulation:

Queen
Forager
Scout
Soldier
Bala
Each ant type is described in more detail below.

C. Characteristics Common to All Ant Types

Each ant should be identified by a unique integer ID. The queenant should have an ID value of 0. Other ants should be numbered inascending order as they are hatched.
All ant types (except for the queen) have a maximum life spanof 1 year.
Dead ants should be removed from the simulation.
All ants are limited to one action per turn, with someexceptions that will be discussed later.
All ants except Bala ants may only move in squares that havebeen revealed by scout ants; Bala ants may also move into squaresthat have not been revealed by scout ants.
When moving, all ant types should move no more than 1 squareper turn.
D. The Queen Ant

The queen ant is responsible for hatching new ants. The specificrequirements for the queen ant are:

The queen never moves from her square (i.e., she remains in thesame square for the entire simulation).
The queen’s maximum lifespan is 20 years.
The queen hatches new ants at a constant rate of 1 ant/day(i.e., 1 ant every 10 turns).
New ants should always be hatched on the first turn of eachday.
The type of ant that is hatched should be determined randomlyaccording to the initial frequencies listed below. You may changethese frequencies as you see fit — these are simply suggestions fora starting point. a. Forager – 50%
Scout – 25%
Soldier – 25%
The queen should consume 1 unit of the food in her chamber oneach turn, including the turn in which she hatches a new ant.
If the food level in the queen’s square is zero when the queentries to eat, the queen dies of starvation.
If the queen dies, either by starvation or by a Bala attack,the simulation should end immediately.
E. Foragers

Foragers are responsible for bringing food to the queen. Theyhave two primary modes of behavior: forage mode andreturn-to-nest mode. The specific requirements for theforager ant are:

Forage Mode
Foragers are considered to be in forage mode whenever they arenot carrying food.
In Forage Mode, foragers should always move to the adjacentsquare containing the highest level of pheromone, except:
If more than one adjacent square has the same level ofpheromone they should randomly pick one of those squares. ii. Whenfollowing a pheromone trail a forager should never move into thesquare it just came from unless it has no other choice.
iii. Depending on how you implement your movement algorithm, itis possible for a forager to get stuck in a loop, traveling roundand round the same squares without getting anywhere. Try to detectwhen this happens, and prevent the endless looping.

Foragers should maintain a history of their movement, to beused when they need to return to the nest.
When a forager enters a square containing food, it should pickup 1 unit of food, unless it is already carrying food.
When a forager picks up a unit of food, it entersreturn-to-nest mode.
Foragers should never pick up food from the squarecontaining the queen.
After a forager has picked up 1 unit of food, it should notmove again until the next turn.
Return-to-nest Mode
When a forager is carrying food, it should retrace its stepsexactly back to the colony entrance; i.e., it should backtrackwhatever path it took to get to the food.
Foragers should ignore pheromone in this mode; i.e., theyshould not move to the adjacent square containing thehighest level of pheromone.
Foragers should not move randomly in this mode.
Foragers should deposit 10 units of pheromone in each squarealong the way back to the colony entrance, including thesquare in which the food was found, but excluding thecolony entrance (the queen’s square).
Foragers should only deposit pheromone in a given square if thecurrent pheromone total in the square is < 1000.
A forager may deposit pheromone in one square, and move to anew square in the same turn.
When a forager reaches the colony entrance, it should add thefood it is carrying to the food supply in that square, in the sameturn in which it entered the colony entrance.
Foragers should not move out of the colony entrance on the sameturn they deliver food there.
If a forager dies while carrying food, the food it was carryingshould remain in the square in which the forager died.
When a forager has deposited food at the nest, the foragerre-enters forage mode, and its movement history should bereset.
F. Scouts

Scouts are responsible for enlarging the foraging area availableto the foragers. The specific requirements for the scout antare:

Scouts should always randomly pick one of the eight possibledirections of movement when it is their turn to do something.
If the chosen square is open, the scout should simply move intothat square.
If the chosen square is closed, the scout should move into thatsquare and the contents of that square should be revealed.
Whenever a closed square is revealed, there is a chance ofthere being food in the square, according to the followingfrequency:
There is a 25% chance that the square will contain a randomamount of food between 500 and 1000 units. b. The other 75% of thetime the square is empty.
You can predetermine the contents of all the squares at thebeginning of the simulation, or you can dynamically determine thecontents of each square as it is opened.
G. Soldier Ants

Soldiers are responsible for protecting the colony by fightingthe enemy Bala ants. Soldier ants have two primary modes ofbehavior: scout mode and attack mode. Thespecific requirements for the soldier ant are:

Scout Mode
A soldier is in scout mode when it is in a square that does notcontain any Bala ants.
While in scout mode:
If there are one or more Bala ants in one or more of thesquares adjacent to the square the soldier is in, the soldiershould move into any one of the squares containing a Bala ant.
If there are no Bala ants in any of the adjacent squares, thesoldier should move randomly.
Attack Mode
A soldier is in attack mode when it is in a square thatcontains one or more Bala ants. Attack mode takes precedence overscout mode.
While in attack mode, a soldier should attack any Bala antspresent.
If there are multiple Bala ants present, only one of themshould be attacked.
During an attack, there is a 50% chance the soldier kills theenemy ant; otherwise, the soldier misses and the enemy antsurvives.
H. Bala ants

Bala ants are enemies of the colony. They should enter only atthe periphery of the colony (i.e., they should not simply pop up inthe middle of the colony). Once in the colony they may move aroundfreely. Assume they never leave the colony once they enter it. Thespecific requirements for the Bala ant are:

Each turn there is a 3% chance one Bala ant will appear in oneof the squares at the boundary of the colony. You may choose tohave Bala ants always enter at the same square (e.g., upper leftcorner), or you may have them enter randomly at any of the 106squares on the edge of the colony.
Once a Bala appears, it should remain in the environment untilit is killed, or dies of old age.
Bala ants should always move randomly.
Bala ants may move into squares that have not yet been revealedby scout ants.
If a Bala ant is in a square containing one or more friendlyants (scout, forager, soldier, queen), the Bala should attack oneof those ants. The ant that is attacked can be selected at random,or you can pick which ant gets attacked.
During an attack, there is a 50% chance a Bala kills the ant itattacks; otherwise, the Bala misses and the ant that is attackedsurvives.
K. Initial State of the Simulation

Your simulation should begin with the center square of theenvironment and its adjacent squares open (see figure below).

The center square represents the entrance to the colony. Itshould contain the following:

the queen ant
10 soldier ants
50 forager ants
4 scout ants
1000 units of food
Ending State of the Simulation
The simulation should end immediately after the queen dies,either from starvation, from old age, or from an attack.

M. Controlling the Simulation

Since this is a fairly complex project, it is useful to have twodifferent modes of execution:

Continuous Execution
In this mode the simulation simply runs non-stop until theending state is reached.

Stepwise Execution
In this mode the simulation can be stepped forward one turn at atime, either at the click of a button or by a key press. This ishelpful for observing what happens from one turn to the next. Thismode is extremely valuable for testing purposes.

N. User Interface

You must use a graphical user interface (GUI) for this project.A GUI will allow you to more easily interpret what is happening inthe simulation as you test and debug your project. You have twooptions:

You can use the GUI I have provided. The challenge here is thatyou will need to design your simulation to use it. The GUI has allthe necessary methods for your simulation to update it. You willneed to understand how those methods work in order to connect yoursimulation to the interface.
You may build your own GUI.
Below is a screenshot from the prototype project I’ve built,which uses the GUI I have provided. The squares containing texthave been revealed by scout ants. The gray square near the centeris the colony entrance, and contains the queen ant (thegold-colored ant in the upper right corner of the square). Scoutants are blue, soldier ants are black, and forager ants are green.The violet squares represent the pheromone trail that has been laiddown by the foragers that found a food supply in square 9,5. Thecolor changes to the various colors of the spectrum to reflect thestrength of the pheromone concentration (violet = lowest, red =highest).

If you decide to use the GUI I have provided, you only need tomake sure the following buttons are implemented:

Normal Setup – Clicking this button shouldinitialize the simulation (see “Initial State of the Simulation”,above). This button should be clicked prior to clicking either theRun or Step buttons.
Run – After the simulation has been set up,clicking this button will run the simulation continuously.
Step – After the simulation has been set up,clicking this button will run the simulation one turn at atime.
