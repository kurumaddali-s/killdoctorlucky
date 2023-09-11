package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.model.impl.Player;
import doctorlucky.view.View;

/**
 * AddPlayertoRoom class is represented by player name, the number of items
 * player can carry and string containing room name the player needs to be added
 * to. It ensures player is added to a room.
 */
public class AddPlayertoRoom implements PlayingGameCommand {
  private final String roomName;
  private final String playerName;
  private final int playerLimit;

  /**
   * AddPlayertoRoom constructor consists of roomName, player object.
   * 
   * @param roomName    represents name of the room.
   * @param playerName  represents name of the player.
   * @param playerLimit represents number of items player can carry.
   */
  public AddPlayertoRoom(String roomName, String playerName, int playerLimit)
      throws IllegalArgumentException {
    if (roomName == null || playerLimit < 0 || playerName == null) {
      throw new IllegalArgumentException("The values cannot be null");
    }
    this.roomName = roomName;
    this.playerName = playerName;
    this.playerLimit = playerLimit;
  }

  @Override
  public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion, view cannot be null");
    }
    return mansion.addPlayertoRoom(roomName, playerName, playerLimit);

  }
}
