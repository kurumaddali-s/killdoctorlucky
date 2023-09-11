package doctorlucky.model;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Room;
import java.util.List;


/**
 * A MansionReading represents the basic structure of the world where the
 * mansion is built. Classes that implement this interface will vary depending
 * upon the type of world they are trying to implement.
 */
public interface MansionReading {

  /**
   * Returns the total number of rows in the mansion.
   * @return the maximum number of rows present in the world
   */
  public int getTotalRows();

  /**
   * Get the Total Number of columns in the world.
   * @return maximum number of columns present in the world
   */
  public int getTotalColumns();

  /**
   * Get the Name of the mansion.
   * @return name of the world in which the game is set
   */
  public String getMansionName();

  /**
   * Get the Total Number of rooms in the world.
   * @return the total number of rooms in the mansion
   */
  public int getTotalRooms();

  /**
   * Get the Total Number of items in the world.
   * @return total number of items present in total in the world
   */
  public int getTotalItems();

  /**
   * Get the Room object of a room based on the index value.
   * @param id of type integer
   * @return Room object that has the corresponding index number
   */
  public Room getRoomById(int id);

  /**
   * Get the Room object of a room based on the input roomName.
   * @param roomName which is a String and the input roomName
   * @return Room object that has the corresponding room name
   */
  public Room getRoomByName(String roomName);

  /**
   * Get a list of roomNames which are neighbors of the input roomName.
   * @param roomName which is a String and the input roomName
   * @return list of room names which are neighbors to input roomname
   */
  public List<String> getNeighbors(String roomName);

  /**
   * Adds the input Room object into the mansion world.
   * @param room object that needs to be added to the world
   */
  public void addRoom(Room room);

  /**
   * Gets list of items in room.
   * @param roomNameforItem which is a String and the input room name
   * @return List of item which are present in the current room
   */
  public List<Item> getParticularRoomItems(String roomNameforItem);



}
