package doctorlucky.model;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Pet;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Room;
import doctorlucky.model.impl.Target;
import doctorlucky.view.ReadOnlyModel;
import java.util.List;

/**
 * A Facade represents all the methods required to run the HumanController These
 * methods are implemented by the mansion class which primarily acts as the
 * model. and also in the MockModel class to enable testing A Facade was
 * implemented so that the actual Mansion class is abstracted from clients.
 */

public interface Facade extends ReadOnlyModel {

  /**
   * Moves the player from its original room to the room present in the String
   * roomMove.
   * 
   * @param roomMove represents the name of the room the player should be moved
   *                 to.
   * @return a string that is new room details to confirm if the player moved or
   *         not.
   */
  public String playerMove(String roomMove);

  /**
   * Add a player object to List of player objects of the room the player was
   * added to.
   * 
   * @param roomName    represents the roomName to which the player was added
   * @param playerName  represents name of the player that is playing the game
   * @param playerLimit represents the total number of items player can carry
   * @return string displaying player added to the room
   */
  public String addPlayertoRoom(String roomName, String playerName, int playerLimit);

  /**
   * Adds an item into the List of item objects the player class has.
   * 
   * @param itemName represents the name of the item
   * @return string of the room and player objects confirming the transfer of
   *         items
   */
  public String addItemstoPlayer(String itemName);

  /**
   * To get a player object based on its name.
   * 
   * @param playerName represents the name of the player.
   * @return player represents Player class object.
   */
  public Player getPlayerbyname(String playerName);

  /**
   * To create a computer player object that is based on random values.
   * 
   * @return string displaying computer playing created
   */
  public String createComputerPlayer();

  /**
   * Get the Room object of a room based on the input roomName.
   * 
   * @param roomName which is a String and the input roomName
   * @return Room class object
   */
  public Room getRoomByName(String roomName);

  /**
   * Get the Room object of the room in which player is currently in.
   * 
   * @param player represents Player class object
   * @return String contining name of the room
   */
  public String getPlayerCurrentRoom(Player player);

  /**
   * Decrements the playerLimit by 1 as it keeps picking items.
   * 
   * @param player represents Player class object
   */
  public void resetPlayerLimit(Player player);

  /**
   * Get a list of roomNames which are neighbors of the room the player object
   * currently. is in.
   * 
   * @return string that is list of roomNames
   */
  public String getneighborsforPlayermovement();

  /**
   * Sets the position of the player as it moves from one room to another.
   * 
   * @param rooomName represents the new room player is moving into.
   * @param player    represents Player class object.
   */
  public void setPlayerPosition(String rooomName, Player player);

  /**
   * Gets list of players that are wothin a particular room.
   * 
   * @param roomNameforPlayer represents the name of the room.
   * @return list of player objects present inside the room.
   */
  public List<Player> getParticularRoomPlayers(String roomNameforPlayer);

  /**
   * Adds the input player object into the list of Player objects present inside
   * Mansion.
   * 
   * @param player object of Player class
   */
  public void addPlayertoPlayerList(Player player);

  /**
   * Returns the newly created player object.
   * 
   * @param roomName    represents name of the room that player needs to be added
   *                    to
   * @param playerLimit represents the total number of items player can carry
   * @return a player object that was added to the game and the world
   */
  public Player setPlayer(String roomName, int playerLimit);

  /**
   * Returns items present inside a particular room.
   * 
   * @param roomNameforItem represents name of the room
   * @return a list of item class objects
   */
  public List<Item> getParticularRoomItems(String roomNameforItem);

  /**
   * Adds the input Room object into the mansion world.
   * 
   * @param room object
   */
  public void addRoom(Room room);

  /**
   * Get a list of roomNames which are neighbors of the input roomName.
   * 
   * @param roomName which is a String and the input roomName
   * @return list of roomNames
   */
  public List<String> getNeighbors(String roomName);

  /**
 * This method is used to retrieve the list of player objects.
 * @return list of player objects
 */
  public List<Player> getPlayerList();

  /**
   * Gets the Target object that is created while initiating the constructor of
   * the Mansion class.
   * 
   * @return Target class object
   */
  public doctorlucky.model.impl.Target getTarget();

  /**
   * Get the Room object of a room based on the index value.
   * 
   * @param id of type integer
   * @return Room class object
   */
  public Room getRoomById(int id);

  /**
   * Get the Room object of a room based on the input roomName.
   * 
   * @param playerName which is a String and the input roomName
   * @return Room class object
   */
  public Player getPlayerByName(String playerName);

  /**
   * Get the string returned by attemptToKillTarget method. This method is used to
   * decide which item to kill the target with in case of a computer player's turn
   * and it falls in the same room as the target
   * 
   * @return String returned by attemptToKillTarget() method
   */
  public String randomattemptToKillTarget();

  /**
   * Get the output of an attempt to kill the target by a player It can either
   * return if the attempt was successful or not This method checks all the
   * various scenarios if the input player can attempt to kill the target with the
   * input itemName.
   * 
   * @param itemName is the name of the item that will be used as a weapon by the
   *                 playerName to kill the target.
   * @return String that declares if the attempt was a successful one or not.
   */
  public String attemptToKillTarget(String itemName);

  /**
   * Gets the details of the input room It returns the items, players present in
   * the room currently. It also returns if the pet and target are present or
   * absent in the room.
   * 
   * @param roomName is the name of the room for which details need to be
   *                 retrieved.
   * @return String that contains the room details
   */
  public String roomInfo(String roomName);

  /**
   * Gets the string that contains which room the pet was in previously and to
   * which room the pet was moved to now.
   * 
   * @param roomName is the name of the room to which the pet should be moved to
   *                 now.
   * @return String that contains the details of the pet's previous and current
   *         location.
   */
  public String petMove(String roomName);

  /**
   * Gets the room object in which the pet currently is in.
   * 
   * @param pet is the pet object for which the room object must be returned.
   * @return Room object in which the pet currently is in.
   */

  public Room getPetRoom(Pet pet);

  /**
   * Moves the input target object from one room to another.
   * 
   * @param target object
   */
  public void moveTarget(Target target);

  /**
   * Gets the room name the input target is in currently.
   * 
   * @param target object
   * @return name of the room in which the target currently is in
   */
  public String getTargetRoom(Target target);

  /**
   * Gets the current room number the target is in currently.
   * 
   * @param target object
   * @return index
   */
  public int getTargetPosition(Target target);

  /**
   * Gets the details of the input player It returns the player's name , items it
   * has the damage each item can cause, where the player is in which room and
   * that room's index.
   * 
   * @return String that contains the player details
   */
  public String playerInfo();

  /**
   * It checks if the target's health is zero or not If it is 0 or less than 0 it
   * returns true, else returns false.
   * 
   * @return if the target is dead or not.
   */
  public boolean isTargetDead();

  /**
   * This method contains all the commands a computer player can execute. The
   * computer player can execute - pickitem, move and lookaround Each command is
   * chosen randomly at every turn and then the subsequent command is executed.
   * 
   * @return String that contains the command the computer player. executed and
   *         output of executing the command.
   */
  public String computerPlayerstuff();

  /**
   * This method is used to retrieve the list of room objects.
   * 
   * @return list of rooms object
   */
  public List<Room> getRooms();

  /**
   * This method is used to retrieve the room name from the given coordinates.
   * 
   * @param row x coordinate of the point.
   * @param col y coordinate of the point.
   * @return room name for the given coordinate
   */
  public String roomCoordFromView(int row, int col);

  /**
   * This method is used to retrieve the current player name who's turn it is.
   * 
   * @return Current player name
   */
  public String getPlayerWhoseTurnItIs();

  /**
   * This method is used to retrieve the list of room names in the mansion.
   * 
   * @return list of room names
   */
  public List<String> getRoomNames();

  /**
   * This method is used to retrieve the items present in the current player's
   * room.
   * 
   * @return list items in room
   */
  public List<String> getRoomItemsCurrentPlayer();

  /**
   * This method is used to retrieve the name of items that the player has picked.
   * 
   * @return list of items of current player
   */
  public List<String> getPlayerItems();

  /**
   * This method is used to check if the game is over or not.
   * 
   * @return true is game is over else false
   */
  public boolean isGameover();

  /**
   * This method is used to reset the maximum turns after every turn is executed.
   */
  public void resetMaxTurns();

  /**
   * This method is used to display that game has ended due to target died or the
   * max turns are over.
   * 
   * @return string displaying game ended
   */
  public String messageMaxTurns();

  /**
   * This method is used to reset the game with new world specification.
   * 
   * @param filePath new filename given to the model
   */
  public void resetGame(String filePath);

  /**
   * This method is used to retrieve the pet object.
   * 
   * @return pet object
   */
  public Pet getPet();

  /**
   * This method is used to return the pet's current room name.
   * 
   * @return name of the pet room
   */
  public String getPetRoomNormal();

  /**
   * This method is used to retrieve the room name of the target character.
   * 
   * @return target character's room name
   */
  String getTargetRoomName();

}
