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

public class MovePetAttempttokill {
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
    targettest = new Target("Doctor Lucky", 50);
    pettest = new Pet("Fortune", "Cat");
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
  }

  // testing pet makes room invisible neighbor
  @Test
  public void testmovepet() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 3);
    // checking the neighbors before moving pet
    assertEquals(
        "[RoomName: Billiard Room, items: [], Players: [], "
            + "Target : [Present], Pet : [Absent]\n"
            + ", RoomName: Dining Hall, items: [], Players: [], "
            + "Target : [Absent], Pet : [Absent]\n"
            + ", RoomName: Drawing Room, items: [], Players: [], "
            + "Target : [Absent], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());
    mansiontest.petMove("Billiard Room");
    // neighbors after moving the pet
    assertEquals(
        "[RoomName: Dining Hall, items: [], Players: [], " + "Target : [Absent], Pet : [Absent]\n"
            + ", RoomName: Drawing Room, items: [], Players: [], "
            + "Target : [Present], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());

  }

  // testing invalid room while moving pet
  @Test(expected = java.lang.NullPointerException.class)
  public void testinvalidroommovepet() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    playertest = new Player("Iron Man", 13);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 3);
    mansiontest.petMove("Restroom");

  }

  // testing successful poke
  @Test
  public void testkillattemptbypokenoplayersinneigboringrooms() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 3);
    assertEquals("\nAn attempt can be made to kill the target as both player Iron Man and target "
        + "are in the same room Billiard Room and no other player can see the "
        + "player Iron Man\n" + "An attempt to kill the target was successful by player Iron Man\n"
        + "Target's renewed health: 49", mansiontest.attemptToKillTarget("poke"));

  }

  // testing unsuccessful poke when players in neighboring rooms
  @Test
  public void testkillattemptbypokeplayersinneigboringrooms() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Dining Hall", "Iron Man", 3);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    assertEquals("\nAttempt unsuccessful as players in neigbouring rooms",
        mansiontest.attemptToKillTarget("poke"));

  }

  // testing unsuccessful poke when player and target in different rooms
  @Test
  public void testkillattemptbypokeplayertargetindifferentrooms() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Dining Hall", "Iron Man", 3);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    mansiontest.addPlayertoRoom("Billiard Room", "Captain America", 3);
    assertEquals("Attempt cannot be made as target and player Iron Man in different rooms",
        mansiontest.attemptToKillTarget("poke"));

  }

  // testing unsuccessful attempt when player target in different rooms
  @Test
  public void testkillattemptbyitemplayertargetindifferentrooms() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Revolver", 3);
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
    // adding players into the game
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    // player picks item
    mansiontest.addItemstoPlayer("Revolver");
    assertEquals("Attempt cannot be made as target and player Hulk in different rooms",
        mansiontest.attemptToKillTarget("Revolver"));

  }

  // testing unsuccessful attempt by item when player in neighboring rooms
  @Test
  public void testkillattemptbyitemplayersinneighbors() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(28, 0);
    pointtest2 = new Point(35, 5);
    roomtest = new Room(pointtest1, pointtest2, "Carriage");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Drawing Room", "Iron Man", 3);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    // Adding items to player
    mansiontest.addItemstoPlayer("Letter Opener");
    // System.out.println(mansiontest.getTargetRoom(targettest));
    assertEquals("\nAttempt unsuccessful as players in neigbouring rooms",
        mansiontest.attemptToKillTarget("poke"));

  }

  // testing successful kill when one item
  @Test
  public void testkillattemptbyitemonlyone() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Drawing Room", "Iron Man", 3);
    // Adding items to player
    mansiontest.addItemstoPlayer("Letter Opener");
    System.out.println(mansiontest.getTargetRoom(targettest));
    assertEquals(
        "\nAn attempt can be made to kill the target as " + "both player Iron Man and target "
            + "are in the same room Drawing Room and no other "
            + "player can see the player Iron Man\n"
            + "An attempt to kill the target was successful by player Iron Man\n"
            + "Target's renewed health: 48\n" + "Item used as weapon Letter Opener removed "
            + "from the game and used as evidence\n"
            + "Player's renewed details : PlayerName: Iron Man, Player Limit: 2, "
            + "Player Items: [], " + "Player Location: 2",
        mansiontest.attemptToKillTarget("Letter Opener"));

  }

  // testing successful kill when more than one item
  @Test
  public void testkillattemptbyitemmorethanone() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(28, 0);
    pointtest2 = new Point(35, 5);
    roomtest = new Room(pointtest1, pointtest2, "Carriage");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    itemtest = new Item("Broomstick", 5);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Drawing Room", "Iron Man", 3);
    // Adding items to player
    mansiontest.addItemstoPlayer("Letter Opener");
    mansiontest.addItemstoPlayer("Broomstick");
    // System.out.println(mansiontest.getTargetRoom(targettest));
    assertEquals(
        "\nAn attempt can be made to kill the target as both player Iron Man and "
            + "target are in the same room Drawing Room and no other player "
            + "can see the player Iron Man\n"
            + "An attempt to kill the target was successful by player Iron Man\n"
            + "Target's renewed health: 45\n"
            + "Item used as weapon Broomstick removed from the game and used as evidence\n"
            + "Player's renewed details : PlayerName: Iron Man, Player Limit: 1, "
            + "Player Items: [ItemName: Letter Opener, Damage value: 2], " + "Player Location: 3",
        mansiontest.attemptToKillTarget("Broomstick"));

  }

  // testing kill attempt when player target pet in
  // same room and having players in neighboring rooms
  @Test
  public void testkillattemptpettargetplayersameroom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(28, 0);
    pointtest2 = new Point(35, 5);
    roomtest = new Room(pointtest1, pointtest2, "Carriage");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    mansiontest.addRoom(roomtest);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Dining Hall", "Iron Man", 3);
    // adding player in neighboring room
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    // move pet
    mansiontest.petMove("Dining Hall");
    assertEquals("\nAn attempt can be made to kill the target as both player Iron Man "
        + "and target are in the same room Dining Hall and no other player can see the "
        + "player Iron Man\n" + "An attempt to kill the target was successful by player Iron Man\n"
        + "Target's renewed health: 49", mansiontest.attemptToKillTarget("poke"));

  }

  // testing kill attempt when player target in same
  // room and having player and pet in neighboring room
  @Test
  public void testkillattemptpettargetplayerroom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(28, 0);
    pointtest2 = new Point(35, 5);
    roomtest = new Room(pointtest1, pointtest2, "Carriage");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    mansiontest.addRoom(roomtest);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Dining Hall", "Iron Man", 3);
    // adding player in neighboring room
    mansiontest.addPlayertoRoom("Armory", "Hulk", 4);
    // move pet
    mansiontest.petMove("Billiard Room");
    assertEquals("\nAttempt unsuccessful as players in neigbouring rooms",
        mansiontest.attemptToKillTarget("poke"));

  }

  // kill attempt with invalid weapon name
  @Test
  public void testkillattemptinvalidweapon() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    // adding player 1
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 3);
    assertEquals("\nAttempt unsuccessful invalid weapon name",
        mansiontest.attemptToKillTarget("Gun"));

  }

  // kill attempt with valid weapon name but unsuccessful attempt removing item
  // used as evidence
  @Test
  public void testkillattemptvalidweapon() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    // adding player 1
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 3);
    // picking item
    mansiontest.addItemstoPlayer("Letter Opener");
    assertEquals(
        "\nAttempt unsuccessful as player and target in different rooms"
            + "Player's renewed details : PlayerName: Iron Man, Player Limit: 2, "
            + "Player Items: [], Player Location: 0",
        mansiontest.attemptToKillTarget("Letter Opener"));

  }

  // successful kill target and win player with a weapon
  @Test
  public void testkilltarget() throws IOException {
    targettest = new Target("Dr Lucki", 2);
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Drawing Room", "Iron Man", 3);
    // Adding items to player
    mansiontest.addItemstoPlayer("Letter Opener");
    assertEquals("\nTarget's renewed health: 0\n" + "GAME OVER TARGET IS DEAD\n"
        + "WINNER PLAYER : Iron Man", mansiontest.attemptToKillTarget("Letter Opener"));

  }

  // testing successful kill with poke
  @Test
  public void testkilltargetviapoke() throws IOException {
    targettest = new Target("Dr Lucki", 1);
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 3);
    assertEquals("\nTarget's renewed health: 0\n" + "GAME OVER TARGET IS DEAD\n"
        + "WINNER PLAYER : Iron Man", mansiontest.attemptToKillTarget("poke"));

  }

  // testing unsuccessful attempt by weapon when in same room
  @Test
  public void testkillattemptbyitemplayersinsame() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(28, 0);
    pointtest2 = new Point(35, 5);
    roomtest = new Room(pointtest1, pointtest2, "Carriage");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // adding players into the game
    mansiontest.addPlayertoRoom("Drawing Room", "Iron Man", 3);
    mansiontest.addPlayertoRoom("Drawing Room", "Hulk", 4);
    // Adding items to player
    mansiontest.addItemstoPlayer("Letter Opener");
    // System.out.println(mansiontest.getTargetRoom(targettest));
    assertEquals("\nAttempt unsuccessful as players in neigbouring rooms",
        mansiontest.attemptToKillTarget("poke"));

  }

  // testing target health reduction
  @Test
  public void testtargethealthreduction() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 3);
    assertEquals(50, targettest.getHealth());
    mansiontest.attemptToKillTarget("poke");
    assertEquals(49, targettest.getHealth());

  }

}
