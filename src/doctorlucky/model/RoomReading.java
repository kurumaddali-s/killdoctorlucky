package doctorlucky.model;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Point;
import java.util.List;


/**
 * A RoomReading represents the various properties of a room has  within in
 * the mansion. Classes that implement this interface will vary depending
 * upon the type of room they are trying to implement.
 */
public interface RoomReading {


  /**
     * Returns the name of the room.
     * @return Room Name of the current room
     */
  public String getRoomName();

  /**
   * Get the top left coordinates of the room.
   * @return point object
   */
  public Point getTopLeft();

  /**
   * Get the bottom right coordinates of the room.
   * @return point object
   */
  public Point getBottomRight();


  /**
   * Get the Total number of items in a rooom.
   * @return list items's size in a particular room
   */
  public int getTotalItems();

  /**
   * Adds the input item object into the room.
   * @param item object.
   */
  public void additem(Item item);

  /**
   * Gets list of items in room.
   * @return List of item objects
   */
  public List<Item> getItems();

  /**
   * Adds the input player object to the list of player objects
   * in the room class.
   * @param player object is the player that needs to be added to this room.
   */
  public void addPlayer(Player player);

  /**
   * Removes the input player object to the list of player objects
   * in the room class as players keep moving from one room to another.
   * @param player object is the player that needs to be removed from the
   *     room class.
   */
  public void removePlayer(Player player);

  /**
   * Removes the input Item object to the list of Item objects
   * in the room class as items keep getting picked by players.
   * @param item object is the item that needs to be removed from the
   *     room class's list of item objects
   */
  public void removeItems(Item item);

  /**
   * Gets list of player objects that are currently present in room.
   * @return List of copy of player objects
   */
  public List<Player> getPlayers();

  /**
   * Gets number of players that are currently present in room.
   * @return the number of players in the room class
   */
  public int getNumberofPlayers();

}


