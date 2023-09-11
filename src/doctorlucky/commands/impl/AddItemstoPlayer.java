package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;
import java.util.List;

/**
 * This class represents the command that allows the player to pick up an item
 * from the current room given by the player.
 */
public class AddItemstoPlayer implements PlayingGameCommand {

  @Override
  public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion, view cannot be null");
    }

    // The listener will ask the controller for items list and again sends it back
    // to the view to display.
    List<String> roomItems = mansion.getRoomItemsCurrentPlayer();
    if (roomItems.size() > 0) {
      String itemName = view.showCommands(roomItems);
      String result = mansion.addItemstoPlayer(itemName);
      return result;
    } else {
      return mansion.addItemstoPlayer("No Items");
    }
  }

}
