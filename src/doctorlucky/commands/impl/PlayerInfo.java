package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;

/**
 * This class represents the command that retrieves the current player details.
 */
public class PlayerInfo implements PlayingGameCommand {

  @Override
public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion or view cannot be null");
    }
    return mansion.playerInfo();
  }


}
