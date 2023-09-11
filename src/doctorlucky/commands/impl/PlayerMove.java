package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;


/**
 * This class represents the command to moves the player from current room
 * to neighbouring room given by the player.
 */
public class PlayerMove implements PlayingGameCommand {

  private final String roomMove;

  /**
     * Constructs the player move command object.
     * @param roomMove name of the room in which player moves
     */
  public PlayerMove(String roomMove) {
    if (roomMove == null) {
      throw new IllegalArgumentException("This value cannot be null");
    }
    this.roomMove = roomMove;
  }


  @Override
public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion or view cannot be null");
    }

    return mansion.playerMove(roomMove);
  }

}

