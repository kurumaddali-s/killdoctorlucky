import doctorlucky.control.ControllerImpl;
import doctorlucky.model.Facade;
import doctorlucky.model.impl.Model;
import doctorlucky.model.impl.RandomPlayer;
import doctorlucky.view.View;
import doctorlucky.view.impl.ViewImpl;
import java.io.File;
import java.io.InputStreamReader;

/**
 * This acts as starting point of the game.
 */
public class Driver {

  /**
   * main method.
   * @param args to take cmd input.
   */
  public static void main(String[] args) {
    if (args == null) {
      throw new IllegalArgumentException("It cannot be null");
    }
    String file = args[0];
    File mansionFile = new File(file);
    int maxTurns = Integer.parseInt(args[1]);
    Model model = new Model();
    Facade mansion;

    // process the input file
    mansion = model.processMansionFile(mansionFile, maxTurns);
    // creating view object
    View view = new ViewImpl(mansion);
    // passing view and model to the controller
    ControllerImpl controller = new ControllerImpl(mansion, view);
    // start game
    controller.playGame();
  }

}
