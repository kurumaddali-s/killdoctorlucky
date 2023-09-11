package doctorlucky.model;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Player;
import java.util.List;


/**
 * A PlayerReading represents the various properties of a player within in
 * the mansion. Classes that implement this interface will vary depending
 * upon the type of player they are trying to implement.
 */
public interface PlayerReading {


  /**
     * Returns the name of the player.
     * @return current player name
     */
  public String getPlayerName();

  /**
  * Get the limit of items the player can carry.
  * @return the number of items the player can carry
  */
  public int getPlayerLimit();

  /**
  * Gets the current position of the player.
  * @return the room index in which the player currently is in.
  */
  public int getPlayerLocation();

  /**
  * This method adds items into the List of Item class objects of Player class.
  * @param item is the object of Item class which being added to the player's list
   *             of items.
  */
  public void addItemstoPlayer(Item item);

  /**
  * Returns the list of all players available inside the Player.
  * @return List of copy of Item class objects is the list of all items in the player
  */
  public List<Item> getPlayerItems();

  /**
  * Sets the position of the player as it moves from one room to another.
  * @param playerLocation is the position to which it is updated to.
  */
  public void setPlayerPosition(int playerLocation);

  /**
  * Decrements the playerLimit by 1 as it keeps picking items.
  */
  public void decrementPlayerLimit();

  /**
   * This method removes items from the List of Item class objects of Player class.
   * as players keep using those items as weapons
   * @param item is the object of Item class that needs to be removed
   *      from the list of items objects in the player class.
   */
  public void removeItems(Item item);

}

