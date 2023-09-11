import static org.junit.Assert.assertEquals;

import doctorlucky.control.ControllerImpl;
import doctorlucky.model.Facade;
import doctorlucky.view.View;
import org.junit.Test;


/**
 * A JUnit test class for the Model Class.
 */
public class ControllerTest {
  StringBuilder viewLog = new StringBuilder();
  StringBuilder modelLog = new StringBuilder();
  int modelUniqueCode = 123;
  int viewUniqueCode = 145;
  Facade mockModel = new MockModel(modelLog, modelUniqueCode);
  View mockView = new MockView(viewLog, viewUniqueCode);

  Facade mockErrorModel = new MockModelError(modelLog, modelUniqueCode);
  View mockErrorView = new MockViewError(viewLog, viewUniqueCode);

  /*
   * public ControllerTest(StringBuilder viewLog, StringBuilder modelLog, int
   * modelUniqueCode) { this.viewLog = viewLog; this.modelLog = modelLog;
   * this.modelUniqueCode = modelUniqueCode; }
   */

  @Test
  public void testAddComputerPlayer() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show Message called unique code \n"
        + "145 Refresh method called unique code \n" + "145";

    controller.addComputerPlayer();

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "Computer player created 123\n";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testAddInvalidComputerPlayer() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.addComputerPlayer();

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "could not create a computer player 123\n";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testAddHumanPlayer() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show Message called unique code \n"
        + "145 Refresh method called unique code \n" + "145";

    controller.addPlayerInfo("9", "Anushka", "Armory");

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nPlayer Information: name - Anushka; "
        + "RoomName - Armory; itemLimit - 9. UniqueCode: 123\n";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testInvalidAddHumanPlayerInvalidItemLimit() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.addPlayerInfo("9", "Anushka", "Armory");

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid player name - Anushka; invalid room name - "
        + "Armory; invalid itemLimit - 9. UniqueCode: 123\n";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testInvalidAddHumanPlayerInvalidRoom() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.addPlayerInfo("9", "Anushka", "Anushka");

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid player name - Anushka; invalid room name - "
        + "Armory; invalid itemLimit - 9. UniqueCode: 123\n";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testplayGame() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Features called unique code: \n"
        + "145Make visible called unique code \n" + "145";

    controller.playGame();
    assertEquals(expectedViewLog, viewLog.toString());

  }

  @Test
  public void testInvalidplaygame() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = "Invalid Features unique code: \n"
        + "145 Invalid Make visible called unique code \n" + "145";

    controller.playGame();

    assertEquals(expectedViewLog, viewLog.toString());
  }

  @Test
  public void testmovePlayerNewRoom() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show Message called unique code \n"
        + "145Show Message called unique code \n" + "145 Refresh method called unique code \n"
        + "145";

    controller.movePlayerToNewRoom(400, 556);

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nRoom Coords from view Unique code: 123RoomName: \n"
        + "Room Coords from view Unique code: 123, UniqueCode: 123\n" + "\n"
        + "Player whose turn it is Unique code: 123\n" + "Player Details: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidmovePlayerNewRoom() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.movePlayerToNewRoom(-400, -556);

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\n" + "Room Coords Invalid: 123Invalid room name: \n"
        + "Room Coords Invalid: 123, UniqueCode: 123\n" + "\n" + "Invalid player: 123\n"
        + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidoutofboundmovePlayerNewRoom() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.movePlayerToNewRoom(1000000, 20000);

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\n" + "Room Coords Invalid: 123Invalid room name: \n"
        + "Room Coords Invalid: 123, UniqueCode: 123\n" + "\n" + "Invalid player: 123\n"
        + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidmovewrongroomPlayerNewRoom() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.movePlayerToNewRoom(980, 789);

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\n" + "Room Coords Invalid: 123Invalid room name: \n"
        + "Room Coords Invalid: 123, UniqueCode: 123\n" + "\n" + "Invalid player: 123\n"
        + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testreusegame() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = " Refresh method called unique code \n" + "145";

    controller.reuseGame("hello/where/are/you");

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInside resetGame method: 123 hello/where/are/you";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidreusegame() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Refresh method called unique code \n" + "145";

    controller.reuseGame("hello/where/are/you");

    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid resetGame method: 123 hello/where/are/you";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testexitProgram() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Refresh method called 145\n";

    controller.exitProgram();

    assertEquals(expectedViewLog, viewLog.toString());

  }

  @Test
  public void testinvalidexitProgram() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = "Invalid Refresh method called 145\n";

    controller.exitProgram();

    assertEquals(expectedViewLog, viewLog.toString());

  }

  @Test
  public void testlookaround() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show Message called unique code \n"
        + "145Show Message called unique code \n" + "145 Refresh method called unique code \n"
        + "145";

    controller.lookAround();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nPlayer whose turn it is Unique code: 123\n"
        + "Player Details: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidlookaround() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.lookAround();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\n" + "Invalid player: 123\n" + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testpickitem() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show commands called unique code \n"
        + "145Show Message called unique code \n" + "145Show Message called unique code \n"
        + "145 Refresh method called unique code \n" + "145";

    controller.pickItem();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nAdd items to player:Show commands called unique code \n"
        + "145itemName . UniqueCode: 123\n" + "\n" + "Player whose turn it is Unique code: 123\n"
        + "Player Details: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidpickitem() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show commands called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.pickItem();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid itemname to add to player: "
        + "Invalid commands called unique code \n" + "145itemName . UniqueCode: 123\n" + "\n"
        + "Invalid player: 123\n" + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidroomwithnoitemspickitem() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show commands called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.pickItem();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid itemname to add to player: "
        + "Invalid commands called unique code \n" + "145itemName . UniqueCode: 123\n" + "\n"
        + "Invalid player: 123\n" + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testmovepet() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show commands called unique code \n"
        + "145Show Message called unique code \n" + "145Show Message called unique code \n"
        + "145 Refresh method called unique code \n" + "145";

    controller.movePet();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nMoving pet to roomName - " + "Show commands called unique code \n"
        + "145 UniqueCode: 123\n" + "\n" + "Player whose turn it is Unique code: 123\n"
        + "Player Details: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidmovepet() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show commands called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.movePet();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid roomName, couldnt move pet -  "
        + "Invalid commands called unique code \n" + "145 UniqueCode: 123\n" + "\n"
        + "Invalid player: 123\n" + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testkilltarget() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show commands called unique code \n"
        + "145Show Message called unique code \n" + "145Show Message called unique code \n"
        + "145 Refresh method called unique code \n" + "145";

    controller.killTarget();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nPlayer Information: attempt to kill target :"
        + "Show commands called unique code \n" + "145UniqueCode: 123\n" + "\n"
        + "Player whose turn it is Unique code: 123\n" + "Player Details: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testInvalidkilltarget() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show commands called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.killTarget();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\n" + "Could not make the attempt invalid itemname : "
        + "Invalid Show commands called unique code \n" + "145UniqueCode: 123\n" + "\n"
        + "Invalid player: 123\n" + "Invalid Player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testplayerInfo() {
    ControllerImpl controller = new ControllerImpl(mockModel, mockView);

    String expectedViewLog = "Show Message called unique code \n"
        + "145Show Message called unique code \n" + "145 Refresh method called unique code \n"
        + "145";

    controller.getPlayerInfo();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nPlayer Details: 123\n"
        + "Player whose turn it is Unique code: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

  @Test
  public void testinvalidplayerInfo() {
    ControllerImpl controller = new ControllerImpl(mockErrorModel, mockErrorView);

    String expectedViewLog = " Invalid Show Message called unique code \n"
        + "145 Invalid Show Message called unique code \n"
        + "145 Invalid Refresh method called unique code \n" + "145";

    controller.getPlayerInfo();
    assertEquals(expectedViewLog, viewLog.toString());

    String expectedmodellog = "\nInvalid Player: 123\n" + "Invalid player: 123";

    assertEquals(expectedmodellog, modelLog.toString());
  }

}
