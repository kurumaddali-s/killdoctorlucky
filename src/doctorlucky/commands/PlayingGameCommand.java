package doctorlucky.commands;

import doctorlucky.model.Facade;
import doctorlucky.view.View;

/**
 * Acts as a common interface for all the action classes and declares the turn() method
 * to play the turns for the player.
 */
public interface PlayingGameCommand {

  /**
     * Executes the turn selected by the user.
     * @return String to display the type of turn Move, Pickup or LookAround
     */

  String go(Facade mansion, View view);

}
