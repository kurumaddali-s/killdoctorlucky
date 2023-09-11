import static org.junit.Assert.assertEquals;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Mansion;
import doctorlucky.model.impl.Pet;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Point;
import doctorlucky.model.impl.Room;
import doctorlucky.model.impl.Target;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Model Class.
 */

public class MansionTest {
  private Mansion mansiontest;
  private Room roomtest;
  private Item itemtest;
  private Point pointtest1;
  private Point pointtest2;
  private Target targettest;
  private Player playertest;
  private Pet pettest;

  /**
   * Setting up the objects.
   **/

  @Before

  public void setUp() throws IOException {

    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Amory");
    targettest = new Target("Doctor Lucky", 1);
    pettest = new Pet("Fortune", "Cat");
    int maximumTurns = 15;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
  }

  // testing if the method returns the room name in which the pet currently is in
  @Test
  public void testgetPetRoomNormal() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    assertEquals("Armory", mansiontest.getPetRoomNormal());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidgetPetRoomNormal() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    assertEquals("ABCD", mansiontest.getPetRoomNormal());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testemptygetPetRoomNormal() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    assertEquals("", mansiontest.getPetRoomNormal());

  }

  @Test
  public void testgetPetRoomNormalwhenpetindifferentroom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(10, 19);
    pointtest2 = new Point(21, 23);
    roomtest = new Room(pointtest1, pointtest2, "Sun Room");
    mansiontest.addRoom(roomtest);
    mansiontest.petMove("Sun Room");
    assertEquals("Sun Room", mansiontest.getPetRoomNormal());

  }

  // testing if GAME ENDS when commands are executed one after other
  @Test
  public void testGameOver() {
    int maximumTurns = 5;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Sharp Knife", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addItemstoPlayer("Sharp Knife");
    mansiontest.getneighborsforPlayermovement();
    mansiontest.playerMove("Billiard Room");
    mansiontest.petMove("Dining Hall");
    mansiontest.attemptToKillTarget("Sharp Knife");

    assertEquals(true, mansiontest.isGameover());
  }

  @Test
  public void testresetMaxTurns() {
    int maximumTurns = 2;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Sharp Knife", 2);
    mansiontest.resetMaxTurns();
    mansiontest.resetMaxTurns();
    assertEquals(true, mansiontest.isGameover());
  }

  @Test
  public void testmessagemaxturnswhenturnsreached() {
    int maximumTurns = 5;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Sharp Knife", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addItemstoPlayer("Sharp Knife");
    mansiontest.getneighborsforPlayermovement();
    mansiontest.playerMove("Billiard Room");
    mansiontest.petMove("Dining Hall");
    mansiontest.attemptToKillTarget("Sharp Knife");
    assertEquals("\nMAXIMUM TURNS REACHED GAMEOVER\n", mansiontest.messageMaxTurns());
  }

  @Test
  public void testmessagemaxturnswhentargetdiesfirst() {
    int maximumTurns = 5;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.attemptToKillTarget("poke");
    assertEquals("\nTARGET DEAD GAME OVER\n", mansiontest.messageMaxTurns());
  }

  @Test
  public void testmessagemaxturnswhentargetdiesandmaximumturnsreached() {
    int maximumTurns = 1;
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, maximumTurns);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.attemptToKillTarget("poke");
    assertEquals("\nGAME OVER MAXIMUM TURNS REACHED AND TARGET DEAD GAMEOVER\n",
        mansiontest.messageMaxTurns());
  }

  // test reset file method
  @Test
  public void testresetfile() {
    String filepath = "C:\\Users\\Hp\\CS5010\\Projects\\KillDoctorLucky\\res\\mansiontextfile2.txt";
    mansiontest.resetGame(filepath);
    assertEquals(mansiontest.getTarget().getTargetname(), " SUMEDHA");
  }

  @Test
  public void testresetfileinvalidfiletype() {
    String filepath = "C:\\Users\\Hp\\CS5010\\Projects\\KillDoctorLucky\\res\\mansiontextfile2.pdf";
    mansiontest.resetGame(filepath);
    assertEquals(mansiontest.getTarget().getTargetname(), "File could not be processed");
  }

  @Test
  public void testresetfiledoesntexist() {
    String filepath = "C:\\Users\\Hp\\CS5010\\Projects\\KillDoctorLucky\\res\\hello.pdf";
    mansiontest.resetGame(filepath);
    assertEquals(mansiontest.getTarget().getTargetname(), "File could not be processed");
  }

  @Test
  public void testgetroomnamefromcoords() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    assertEquals("Billiard Room", mansiontest.roomCoordFromView(555, 471));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testgetroomnamefrominvalidcoords() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    assertEquals("Billiard Room", mansiontest.roomCoordFromView(-555, -471));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testgetroomnamefromcoordsoutofbound() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    assertEquals("Billiard Room", mansiontest.roomCoordFromView(100000, 30000));
  }

  @Test
  public void testplayerwhoseturn() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Billiard Room", "Hulk", 4);
    assertEquals("Iron Man", mansiontest.getPlayerWhoseTurnItIs());
    mansiontest.attemptToKillTarget("poke");
    assertEquals("Hulk", mansiontest.getPlayerWhoseTurnItIs());
  }

}
