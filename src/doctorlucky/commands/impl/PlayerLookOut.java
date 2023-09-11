package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.view.View;

/**
 * This class represents the command to looks around the details
 * of current room to display the neighbour room details
 * and the players present in them.
 */
public class PlayerLookOut implements PlayingGameCommand {
  @Override
public String go(Facade mansion, View view) {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion or view cannot be null");
    }
    return mansion.getneighborsforPlayermovement();
  }
}
