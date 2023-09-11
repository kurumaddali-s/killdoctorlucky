package doctorlucky.commands.impl;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.model.Facade;
import doctorlucky.model.impl.RandomPlayer;
import doctorlucky.view.View;

/**
 * This class represents addition of computer player to the game.
 */

public class RandomPlayerAddition implements PlayingGameCommand {

  @Override
  public String go(Facade mansion, View view) throws IllegalArgumentException {
    if (mansion == null || view == null) {
      throw new IllegalArgumentException("mansion, view cannot be null");
    }
    return mansion.createComputerPlayer();

  }
}
