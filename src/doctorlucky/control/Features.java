package doctorlucky.control;

/**
 * This interface represents a set of features that the game offers. Each
 * feature is exposed as a function in this interface. This function is used
 * suitably as a callback by the view, to pass control to the controller.
 */

public interface Features {

  /**
     * Execute a single game of killdoctorlucky given a model of the type Facade. When
     * the number of turns is over,the playGame method ends.
     */

  public void playGame();

  /**
     * This method is used to add the player to the game by taking inputs from the view..
     * @param playerLimit represents the number of items a player can carry.
     * @param playerName represents the name of the player.
     * @param playerRoom represents the name of the room the player needs to be added to.
     */

  public void addPlayerInfo(String playerLimit, String playerName, String playerRoom);

  /**
     * Moves the player from its original room to the new room given by the user from view.
     * It is considered an invalid move if the new room isn't a neighbor to the original room
     * in which the player is in.
     * @param row represents y coordinate of the point where the user clicked.
     * @param col represents x coordinate of the point where the user clicked.
     */

  public void movePlayerToNewRoom(int row, int col);

  /**
     * This method is used to change the current world specifications. The path of the file
     * name is sent as input and a new world is build according to this world's
     * specifications.
     * @param filePath represents path of the text file which contains the new world specifications
     */
  public void reuseGame(String filePath);

  /**
     * This method is used to exit the program, it is called whenever the target dies or
     * the maximum turns in the game are over.
     */
  public void exitProgram();

  /**
     * This method is used to show the details of the current player's neighboring rooms
     * and what the rooms contains including players, items and pet.
     */

  public void lookAround();

  /**
     * This method is used to for the current player to pick an item from the room.
     * If no items are present in the world an error message is displayed.
     */

  public void pickItem();

  /**
     * This method is used to move the pet from one room to another given by the user from the view.
     * The user can select a room from the list of rooms.The current player whose turn it is can
     * move the pet to a new room.
     */

  public void movePet();

  /**
     * This method is used to make an attempt to kill the target by selecting an item from the list.
     * The current player whose turn it is makes the attempt to kill target.
     */


  public void killTarget();

  /**
     * This method is used to add the computer player to the game.
     */
  public void addComputerPlayer();

  /**
     * This method is used to retrieve the current player information.
     */
  public void getPlayerInfo();
}
