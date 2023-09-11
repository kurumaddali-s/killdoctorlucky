package doctorlucky.view;

import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Room;
import java.util.List;

/**
 * A ReadOnlyModel interface represents the functionalities of the
 * model to be used by the view only.This interface consists of methods that
 * the view can access, it is separated from the Facade interface in order to
 * isolate the view from the model.
 */
public interface ReadOnlyModel {

  /**
 * This method is used to retrieve the room names from the model.
 * @return list of room names which are all strings that present in the mansion.
 */
  public List<String> getRoomNames();

  /**
 * This method is used to retrieve the player who is currently playing.
 * @return name of the player who needs to execute a turn.
 */
  public String getPlayerWhoseTurnItIs();

  /**
 * This method is used to retrieve the current player information.
 * @return string displaying player information
 */
  public String playerInfo();

  /**
 * Gets the name of the room in which the target currently is in.
 * @return name of the room in which the target currently is in.
 */
  public String getTargetRoomName();

  /**
 * Gets the name of the room in which the pet currently is in.
 * @return name of the room in which the pet currently is in.
 */
  public String getPetRoomNormal();

  /**
 * This method is used to retrieve the room objects list from the model to access the coordinates.
 * @return list of room objects present in the mansion
 */
  public List<Room> getRooms();

  /**
 *  This method is used to retrieve the player objects list
   *  from the model to access the coordinates.
 * @return list of player objects present in the mansion
 */
  public List<Player> getPlayerList();

  /**
 * Gets the player object when a player name is passed as input.
 * @param currPlayerName is the name of the player whose Player object needs to
 *                       be retrieved.
 * @return list of player objects present in the mansion
 */
  public Player getPlayerByName(String currPlayerName);

  /**
 * Gets the name of the room in which the player object is currently present.
 * @param playerByName is the Player
 * @return list of player objects present in the mansion
 */
  String getPlayerCurrentRoom(Player playerByName);

  /**
 * Get the Room object of a room based on the input roomName.
 * @param playerCurrentRoom which is a String and the input roomName
 * @return Room class object which has the playerCurrentRoom name
 */
  Room getRoomByName(String playerCurrentRoom);

  /**
 * Get the Room object of a room based on the index value.
 * @param targetRoom is the index of the room
 * @return Room class object which has the index as the input targetRoom
 */
  Room getRoomById(int targetRoom);
}
