package doctorlucky.control;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.commands.impl.AddItemstoPlayer;
import doctorlucky.commands.impl.AddPlayertoRoom;
import doctorlucky.commands.impl.AttemptToKillTarget;
import doctorlucky.commands.impl.PetMove;
import doctorlucky.commands.impl.PlayerInfo;
import doctorlucky.commands.impl.PlayerLookOut;
import doctorlucky.commands.impl.PlayerMove;
import doctorlucky.commands.impl.RandomPlayerAddition;
import doctorlucky.model.Facade;
import doctorlucky.view.View;

/**
 * ControllerImpl class implements the Features interface. The ControllerImpl
 * acts as an intermediate between the view and model. The inputs from the view
 * are sent to the controller and controller in turn sends to the model. It also
 * consists of all the features to play the game.
 */
public class ControllerImpl implements Features {

  private final Facade model;
  private final View view;
  private PlayingGameCommand cmd;

  /**
   * Constructor for the controller.
   * 
   * @param model instance of the Facade interface
   * @param view  instance of the View interface
   */
  public ControllerImpl(Facade model, View view) {
    if (model == null || view == null) {
      throw new IllegalArgumentException("It cannot be null");
    }
    this.model = model;
    this.view = view;
    this.cmd = null;
  }

  @Override
  public void playGame() {
    view.setFeatures(this);
    view.makeVisible();
  }

  @Override
  public void addPlayerInfo(String playerLimit, String playerName, String playerRoom) {
    if (!model.isGameover()) {
      try {
        int playerNum = Integer.parseInt(playerLimit);
        cmd = new AddPlayertoRoom(playerRoom, playerName, playerNum);
        view.showMessage(cmd.go(model, view));
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }

  }

  @Override
  public void movePlayerToNewRoom(int row, int col) {
    if (!model.isGameover()) {
      try {
        cmd = new PlayerMove(model.roomCoordFromView(row, col));
        view.showMessage(cmd.go(model, view));
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void reuseGame(String filePath) {
    if (filePath != null) {
      model.resetGame(filePath);
      view.refresh();
    } else {
      view.showMessage("The file path is null");
    }
  }

  @Override
  public void exitProgram() {
    System.exit(0);
  }

  @Override
  public void lookAround() {
    if (!model.isGameover()) {
      try {
        cmd = new PlayerLookOut();
        view.showMessage(cmd.go(model, view));
        // view.showMessage(model.getPlayerWhoseTurnItIs() + "'s turn now\n" +
        // model.playerInfo());
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void pickItem() {
    if (!model.isGameover()) {
      try {
        cmd = new AddItemstoPlayer();
        view.showMessage(cmd.go(model, view));
        // view.showMessage(model.getPlayerWhoseTurnItIs() + "'s turn now\n" +
        // model.playerInfo());
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void movePet() {
    if (!model.isGameover()) {
      try {
        cmd = new PetMove();
        view.showMessage(cmd.go(model, view));
        // view.showMessage(model.getPlayerWhoseTurnItIs() + "'s turn now\n" +
        // model.playerInfo());
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void killTarget() {
    if (!model.isGameover()) {
      try {
        cmd = new AttemptToKillTarget();
        view.showMessage(cmd.go(model, view));
        // view.showMessage(model.getPlayerWhoseTurnItIs() + "'s turn now\n" +
        // model.playerInfo());
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void addComputerPlayer() {
    if (!model.isGameover()) {
      try {
        cmd = new RandomPlayerAddition();
        view.showMessage(cmd.go(model, view));
        view.refresh();
      } catch (IllegalArgumentException e) {
        view.showMessage("Command could not be executed");
      }
    } else {
      view.showMessage(model.messageMaxTurns());
      this.exitProgram();
    }
  }

  @Override
  public void getPlayerInfo() {
    try {
      cmd = new PlayerInfo();
      view.showMessage(cmd.go(model, view));
      view.refresh();
    } catch (IllegalArgumentException e) {
      view.showMessage("Command could not be executed");
    }
  }

}
