package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;
import java.util.List;

/**
 * This class represents the command that allows the player to
 * hit the target by using the item picked by the player. This command
 * is successful only if the target character and the player is in same room
 * and the player's neighbouring room does not contain any players.
 */
public class AttemptToKillTarget implements PlayingGameCommand {

  @Override
public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion or view cannot be null");
    }
    List<String> playerItems = mansion.getPlayerItems();
    String itemName = view.showCommands(playerItems);
    String result = mansion.attemptToKillTarget(itemName);
    return result;
  }

}

