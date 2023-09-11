package doctorlucky.view;

import doctorlucky.control.Features;
import java.util.List;

/**
 * This interface represents the functionalities used in the GUI for the game.
 * It includes displaying appropriate messages to the player, refreshing the screen,
 * resetting focus and calling appropriate methods of the controller for each feature.
 */
public interface View {

  /**
 * This method is used to pass the inputs to the controller for each
 * feature being implemented from the view. The features include moving
 * the player, pick item, kill attempt and moving the pet.
 * @param f features object used to pass the access to the controller
 */
  public void setFeatures(Features f);

  /**
 * This method is used to add the components in view for adding the player.
 */
  public void setOutput();


  /**
 * This method is used to take the game to next panel where the player
 * can play using keyboard keys and mouse click.
 */
  public void playTurn();

  /**
 * This method is used to display appropriate message on the dialog box
 * sent from the controller.
 * @param message string to be display in the dialog box
 */
  public void showMessage(String message);

  /**
 * This method is used to reset the visibility of all components true.
 */
  public void makeVisible();

  /**
 * This method is used to reset the focus of all the components.
 */
  public void resetFocus();

  /**
 * This method is used to show the list of items in a dialog box and
 * get the input from the player.
 * @param items list of items being displayed in dialog box
 * @return String returned by the dialog box confirmation
 */
  public String showCommands(List<String> items);

  /**
 * This method is used to repaint all the components in the view.
 */
  public void refresh();
}
