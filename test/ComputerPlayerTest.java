import static org.junit.Assert.assertEquals;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Mansion;
import doctorlucky.model.impl.Pet;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Point;
import doctorlucky.model.impl.RandomPlayer;
import doctorlucky.model.impl.Room;
import doctorlucky.model.impl.Target;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the ComputerPlayer Class.
 */

public class ComputerPlayerTest {
  private Mansion mansiontest;
  private Room roomtest;
  private Item itemtest;
  private Point pointtest1;
  private Point pointtest2;
  private Target targettest;
  private Player playertest;
  private Pet pettest;
  private RandomPlayer randomPlayer;

  /**
   * Setting up the objects.
   **/

  @Before

  public void setUp() throws IOException {

    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Amory");
    roomtest.additem(itemtest);
    targettest = new Target("Doctor Lucky", 50);
    pettest = new Pet("Fortune", "Cat");
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    mansiontest.addRoom(roomtest);
    randomPlayer = new RandomPlayer();
  }

  @Test
  public void testmovecomputerpet() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    randomPlayer = new RandomPlayer(2);
    mansiontest.createComputerPlayer();
    Player player = mansiontest.getPlayerByName("computer");
    // checking the neighbors before moving pet
    assertEquals("move\n" + "\n" + "Room Amory renewed details :\n"
        + " RoomName: Amory, items: [], " + "Players: [PlayerName: computer, "
        + "Player Limit: 2, Player Items: [], Player Location: 0], "
        + "Target : [Absent], Pet : [Present]", mansiontest.computerPlayerstuff());

  }

  // testing pet makes room invisible
  @Test
  public void testlookaroundcomputer() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.createComputerPlayer();
    randomPlayer = new RandomPlayer(1);
    Player player = mansiontest.getPlayerByName("computer");
    // checking the neighbors before moving pet
    assertEquals("lookaround\n" + "[RoomName: Billiard Room, items: [], Players: [], "
        + "Target : [Present], Pet : [Absent]\n"
        + ", RoomName: Billiard Room, items: [], Players: [], "
        + "Target : [Present], Pet : [Absent]\n"
        + ", RoomName: Drawing Room, items: [], Players: [], "
        + "Target : [Absent], Pet : [Absent]\n" + "]", mansiontest.computerPlayerstuff());

  }

  @Test
  public void testpickitem() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.createComputerPlayer();
    randomPlayer = new RandomPlayer(0);
    Player player = mansiontest.getPlayerByName("computer");
    // checking the neighbors before moving pet
    assertEquals("pickitem\n" + "\n" + "Items in Armory are:\n"
        + " ItemName: Revolver, Damage value: 3\n" + "Room Armory renewed details :\n"
        + "RoomName: Armory, items: [], Players: " + "[PlayerName: computer, Player Limit: 2, "
        + "Player Items: [ItemName: Revolver, Damage value: 3], "
        + "Player Location: 0, PlayerName: ONE, Player Limit: 3, "
        + "Player Items: [], Player Location: 0], Target : [Absent], " + "Pet : [Present] \n"
        + "Item Revolver picked by player computer ", mansiontest.computerPlayerstuff());

  }

  @Test
  public void testkillattemptwithpoke() {
    targettest = new Target("Dr Lucky", 1);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    // mansiontest.moveTarget(targettest);
    System.out.println(mansiontest.getTargetRoom(targettest));
    mansiontest.addRoom(roomtest);
    mansiontest.createComputerPlayer();
    randomPlayer = new RandomPlayer(2);
    assertEquals("move\n" + "\n" + "Room Dining Hall renewed details :\n"
        + " RoomName: Dining Hall, items: [], Players: [PlayerName: computer, Player Limit: 2, "
        + "Player Items: [], Player Location: 3], Target : [Absent], Pet : [Absent]\n"
        + "An attempt can be made to kill the target as both player computer and "
        + "target are in the same room Dining Hall and "
        + "no other player can see the player computer\n"
        + "An attempt to kill the target was successful by player computer\n"
        + "Target's renewed health: 49", mansiontest.computerPlayerstuff());

  }

  @Test
  public void testkillattemptwithweapon() {
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.createComputerPlayer();
    // pickitem
    randomPlayer = new RandomPlayer(0);
    mansiontest.computerPlayerstuff();
    mansiontest.moveTarget(targettest);
    // move
    randomPlayer = new RandomPlayer(2);
    mansiontest.computerPlayerstuff();
    mansiontest.moveTarget(targettest);
    assertEquals(
        "move\n" + "\n" + "Room Billiard Room renewed details :\n"
            + " RoomName: Billiard Room, items: [ItemName: Billiard Cue, Damage value: 2], "
            + "Players: [PlayerName: computer, Player Limit: 2, "
            + "Player Items: [ItemName: Revolver, Damage value: 3], "
            + "Player Location: 1], Target : [Present], Pet : [Absent]\n"
            + "An attempt can be made to kill the target as both player "
            + "computer and target are in the same room Billiard Room and "
            + "no other player can see the player computer\n"
            + "An attempt to kill the target was successful by player computer\n"
            + "Target's renewed health: 1\n"
            + "Item used as weapon Revolver removed from the game and used as evidence\n"
            + "Player's renewed details PlayerName: computer, "
            + "Player Limit: 2, Player Items: [], Player Location: 1: ",
        mansiontest.computerPlayerstuff());

  }

}
