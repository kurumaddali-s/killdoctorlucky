package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;
import java.util.List;

/**
 * This class represents the command that allows the player to move the pet from
 * one room to another.
 */

public class PetMove implements PlayingGameCommand {

  @Override
  public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion or view cannot be null");
    }
    List<String> roomsPresent = mansion.getRoomNames();
    String roomMove = view.showCommands(roomsPresent);
    String result = mansion.petMove(roomMove);
    return result;
  }

}
