# CS 5010 Semester Project

This repo represents the coursework for CS 5010, the Fall 2022 Edition!

**Names:** Anushka Jain 
                   Sri Sai Sushmitha Kurumaddali

**Email:** jain.anusk@northeastern.edu  
                   kurumaddali.s@northeastern.edu

**Preferred Name:** Anushka
                                  Sushmitha


### About/Overview

The overview of Milestone 4 is -  
- It parses a given input file and creates a world which contains rooms, rooms contain items. In this world the target keeps moving for every turn in a fixed order.
- Players added can be of two types - human , computer
- Players can pick items from a room and attempt to kill the target.
- An attempt is successful only when the target and player are in the same room and there are no players in the neighboring rooms to see the player.
- If any of the above condition fails then the attempt is unsuccesful and the item is removed from the world.
- A pet exists which can be moved by any human player to any room in the world.
- The room in which the pet exists becomes invisible and wont be seen during lookaround as a room's neighbor.
- The model also has an additional method that returns the name of the room when two coordinates are sent to it.
-The players can enter into the game using the GUI, enter it's details and use the instruction play the game.
-The players can use mouse click and key board keys to play the game.

### List of Features

- Can parse the input text file
- Can build the world 
- Can retrieve neighbors of any space
- Can retrieve items and its damage value for a given space
- Can add players into the world
- Includes a computer player
- HumanController class to execute all the various commands a player can execute.
- Implementation of Command Design Pattern
- Using Facade
- Attempt to kill target and checks wheras scenarios when the attempt can be successful and when cannot
- When a pet is moved to a room that room becomes invisible
- The game is over whenever the target's health becomes zero or the number of turns becomes zero.
- A player is declared winner when he/she attempts to kill the target and that leads to target's death.
- Creates a visual representation of the world, players can be added, and their commands can be executed.
- The view supports addition of computer player
- The view supports all the commands a player can execute and shows the target moving.
- Whenever the game ends a pop up box is shown saying why the game ended.
- A new world can be created , the user can select a text file and that world is created.

### How to Run

To run the program when need to use this command on the terminal
java -jar .\KillDoctorLucky.jar C:\Users\Hp\CS5010\Projects\KillDoctorLucky\res\mansiontextfile.txt 15 
Where C:\Users\Hp\CS5010\Projects\KillDoctorLucky\res\mansiontextfile.txt is the location of the text file
15 is the maximum number of turns.
After running a screen opens up which contains a button saying "click here to start the game", after clicking on that a screen is displayed
which contains the mansion image and a button to add players.
### How to Use the Program

To use the program first we need to provide the location of the text file required to build the mansion, then give maximum number of turns the game can play.
After running a screen opens up which contains a button saying "click here to start the game", after clicking on that a screen is displayed
which contains the mansion image and a button to add players. Add players one by one, by entering the number of items they can carry 
and their name and select which room they should enter. The player if added in the same room as the pet then it wont be seen in the mansion. Click on
the add Computer player button to add a computer player. Then start the game by clicking on play button, after that select p to pickitem, m to movepet,
l to lookaround and click on a room to move the player to it.

### Example Runs
A video submission will be made

### Design/Model Changes
Added methods to model so that the turns can be calculated there itself. Also refactored the model to reduce the maximum number of turns
as the game progresses. Added a method getroomfromcoords(intx , int y), which returns the name of the room based the coords sent to it.
Added viewimpl and an interface View to execute the visual representation of the world.

### Assumptions

- We need to add atleast one human player before adding the computer player.
- For every unsuccessful attempt to kill a turn is considered but items are removed from the mansion only when the turn is successful.
- Before every turn when room and player details are displayed the target detail is present showing if the target is present or absent in the room and also
if the pet is present or absent.
- The computer player cannot move the pet its a design decision.
- The computer player has 3 commands to execute pickitem, move, lookaround the killtarget command is called whenever both computer and target are in the same
room.
- Its a design decision to not display neighbors of neighbors in lookaround, for a current room its neighbors and the details within it will be displayed.

### Limitations

I could not implement a moving pet through dfs , the pet remains stationary in the room the player adds it to.
Could have ensured the view looked more attractive.

### Citations

I didnt use many resources just https://stackoverflow.com/ for basic java syntax 

### Changes to model submitted in Milestone3
Made the model completed isolated from the controller. Validated parameters, corrected javadocs.