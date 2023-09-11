import static org.junit.Assert.assertEquals;

import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Mansion;
import doctorlucky.model.impl.Pet;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Point;
import doctorlucky.model.impl.Room;
import doctorlucky.model.impl.Target;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * A JUnit test class for the Model Class.
 */

public class KillDoctorLuckyTest {
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

  @Test
  public void testgettotalrows() {

    assertEquals(36, mansiontest.getTotalRows());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidrows() throws IOException {
    Mansion mansiontest = new Mansion(-36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    assertEquals(-23, mansiontest.getTotalRows());
  }

  @Test
  public void testgettotalcolumns() {

    assertEquals(30, mansiontest.getTotalColumns());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidcolumns() throws IOException {
    Mansion mansiontest = new Mansion(36, -30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    assertEquals(-30, mansiontest.getTotalColumns());
  }

  @Test
  public void testgettotalhealth() {

    assertEquals(50, targettest.getHealth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidhealth() throws IOException {
    Target targetest = new Target("lucky", -50);
    assertEquals(-50, targetest.getHealth());
  }

  @Test
  public void testgettotalrooms() {
    mansiontest.addRoom(roomtest);
    assertEquals(1, mansiontest.getTotalRooms());
  }

  @Test
  public void testgettotalitems() {
    roomtest.additem(itemtest);
    assertEquals(1, roomtest.getTotalItems());
  }

  @Test
  public void testvalidPoints() {
    assertEquals("Point(x, y): (22, 19)", pointtest1.toString());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidPoints() throws IOException {
    Point pointinvalidtest = new Point(-12, -23);
    assertEquals(-12, pointtest1.toString());
  }

  @Test
  public void testonegetneighbors() throws IOException {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    itemtest = new Item("Chain Saw", 4);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    List<String> answers = new ArrayList<String>();
    answers.add("Armory");
    assertEquals(answers, mansiontest.getNeighbors("Billiard Room"));
  }

  @Test
  public void testgetmultipleneighborsanotherroom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    itemtest = new Item("Chain Saw", 4);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // assertEquals(2, mansiontest.getTotalRooms());
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    itemtest = new Item("Letter Opener", 2);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    // assertEquals(4, mansiontest.getAllRooms());
    List<String> answers = new ArrayList<String>();
    answers.add("Billiard Room");
    answers.add("Dining Hall");
    answers.add("Drawing Room");
    assertEquals(answers, mansiontest.getNeighbors("Armory"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testinvalidRoomName() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    assertEquals("Armory", mansiontest.getNeighbors("Billiard Room"));
  }

  @Test
  public void testgetoneitem() {
    // adding room and item
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    String s = String.valueOf(mansiontest.getParticularRoomItems("Armory"));
    assertEquals("[ItemName: Revolver, Damage value: 3]", s);
  }

  @Test
  public void testgetmanyitems() {
    // adding room and item
    pointtest1 = new Point(16, 3);
    pointtest2 = new Point(21, 10);
    itemtest = new Item("Crepe Pan", 3);
    Item itemtest1 = new Item("Sharp Knife", 3);
    roomtest = new Room(pointtest1, pointtest2, "Kitchen");
    roomtest.additem(itemtest);
    roomtest.additem(itemtest1);
    mansiontest.addRoom(roomtest);
    String s = String.valueOf(mansiontest.getParticularRoomItems("Kitchen"));
    assertEquals(
        "[ItemName: Crepe Pan, Damage value: 3, " + "ItemName: Sharp Knife, Damage value: 3]", s);
  }

  @Test
  public void testgetPositionTarget() {
    assertEquals(0, mansiontest.getTargetPosition(targettest));
    mansiontest.moveTarget(targettest);
    assertEquals(1, mansiontest.getTargetPosition(targettest));
  }

  @Test
  public void testgetPositionafterlastRoomTarget() throws IOException {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    assertEquals(0, mansiontest.getTargetPosition(targettest));
    for (int i = 0; i < 2; i++) {
      mansiontest.moveTarget(targettest);
    }
    assertEquals(0, mansiontest.getTargetPosition(targettest));
  }

  @Test
  public void testgetTargetroomname() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    assertEquals("Armory", mansiontest.getTargetRoom(targettest));
  }

  @Test
  public void testaddPlayer() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    playertest = new Player("Iron Man", 13);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    String s = String.valueOf(mansiontest.getParticularRoomPlayers("Armory"));
    assertEquals(
        "[PlayerName: Iron Man, Player Limit: 13, Player Items: [], " + "Player Location: 0]", s);
  }

  @Test(expected = NullPointerException.class)
  public void testaddPlayerInvalidRoomName() throws IOException {
    playertest = new Player("Iron Man", 13);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    String s = String.valueOf(mansiontest.getParticularRoomPlayers("Armory"));
    assertEquals("[PlayerName: Iron Man, Player Limit: 13]", s);
  }

  @Test
  public void testremovePlayerfromRoom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    playertest = new Player("Iron Man", 13);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    mansiontest.getRoomByName("Armory").removePlayer(playertest);
    String s = String.valueOf(mansiontest.getRoomByName("Armory").getPlayers());
    assertEquals("[]", s);
  }

  @Test
  public void testadditemstoPlayer() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    playertest = new Player("Iron Man", 13);
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    playertest.addItemstoPlayer(itemtest);
    roomtest.removeItems(itemtest);
    String s = String.valueOf(playertest.getPlayerItems());
    String s1 = String.valueOf(roomtest.getItems());
    assertEquals("[][ItemName: Revolver, Damage value: 3]", s1 + s);
  }

  @Test
  public void setPlayerPosition() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    playertest = new Player("Iron Man", 13);
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.getRoomByName("Armory").addPlayer(playertest);
    playertest.addItemstoPlayer(itemtest);
    mansiontest.setPlayerPosition("Amory", playertest);
    int i = playertest.getPlayerLocation();
    assertEquals(0, i);
  }

  @Test
  public void testplayermove() {
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
    mansiontest.playerMove("Billiard Room");
    int i = playertest.getPlayerLocation();
    assertEquals("Billiard Room", mansiontest.getRoomById(i).getRoomName());
  }

  @Test
  public void testplayerlookaround() {
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
    assertEquals("[Billiard Room, Dining Hall, Drawing Room]",
        mansiontest.getneighborsforPlayermovement());
  }

  @Test
  public void testtargetmovementwithplayeraddition() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
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
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 13);
    assertEquals(1, targettest.getTargetPosition());
  }

  @Test
  public void testplayerlookaroundtargetturn() {
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
    mansiontest.getneighborsforPlayermovement();
    // assertEquals("[Billiard Room, Dining Hall, Drawing Room]",
    assertEquals(1, targettest.getTargetPosition());
  }

  @Test
  public void testplayermovetargetmove() {
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
    mansiontest.playerMove("Billiard Room");
    int i = playertest.getPlayerLocation();
    mansiontest.getRoomById(i).getRoomName();
    assertEquals(1, targettest.getTargetPosition());
    // assertEquals("Billiard Room", mansiontest.getRoomById(i).getRoomName());
  }

  // Target player position details in player display
  @Test
  public void testplayerdisplayinfo() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "RoomName: Armory, items: [], "
            + "Players: [PlayerName: Iron Man, Player Limit: 4, Player Items: [], "
            + "Player Location: 0], " + "Target : [Present], Pet : [Present]",
        mansiontest.roomInfo("Armory"));

  }

  // modified lookaround along with pet, target, players and items in it, no
  // players
  // in same space, players in neighbors, items in neighbors, target in neighbor
  @Test
  public void testplayerlookaroundmodified() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    itemtest = new Item("Sharp Knife", 2);
    roomtest.additem(itemtest);
    itemtest = new Item("Crepe Pan", 7);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Billiard Room", "Hulk", 5);
    mansiontest.addPlayertoRoom("Dining Hall", "Hulk", 2);
    mansiontest.addPlayertoRoom("Drawing Room", "Hulk", 1);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "[RoomName: Billiard Room, items: [ItemName: Sharp Knife, Damage value: 2, "
            + "ItemName: Crepe Pan, Damage value: 7], Players: [PlayerName: Hulk, Player Limit: 5, "
            + "Player Items: [], Player Location: 1], Target : [Present], Pet : [Absent]\n"
            + ", RoomName: Dining Hall, items: [], Players: [PlayerName: Hulk, Player Limit: 2, "
            + "Player Items: [], Player Location: 2], Target : [Absent], Pet : [Absent]\n"
            + ", RoomName: Drawing Room, items: [], Players: [PlayerName: Hulk, "
            + "Player Limit: 1, Player Items: [], Player Location: 3], "
            + "Target : [Absent], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());
  }

  // modified lookaround 2 players in same room
  @Test
  public void testplayerlookaroundtwoinsameroom() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    itemtest = new Item("Sharp Knife", 2);
    roomtest.additem(itemtest);
    itemtest = new Item("Crepe Pan", 7);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 5);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "RoomName: Armory, items: [ItemName: Revolver, Damage value: 3], "
            + "Players: [PlayerName: Iron Man, Player Limit: 4, Player Items: [], "
            + "Player Location: 0, PlayerName: Hulk, Player Limit: 5, Player Items: [], "
            + "Player Location: 0], Target : [Absent], Pet : [Present]",
        mansiontest.roomInfo("Armory"));
  }

  // modified lookaround no players in neighbors
  @Test
  public void testplayerlookaroundnoplayersinneighbor() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    itemtest = new Item("Sharp Knife", 2);
    roomtest.additem(itemtest);
    itemtest = new Item("Crepe Pan", 7);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(12, 11);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "RoomName: Armory, " + "items: [ItemName: Revolver, Damage value: 3], "
            + "Players: [PlayerName: Iron Man, Player Limit: 4, "
            + "Player Items: [], Player Location: 0], Target : [Absent], Pet : [Present]",
        mansiontest.roomInfo("Armory"));
  }

  // modified lookaround items in same space
  @Test
  public void testplayerlookarounditemsinsamespace() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "RoomName: Armory, " + "items: [ItemName: Revolver, Damage value: 3], "
            + "Players: [PlayerName: Iron Man, Player Limit: 4, "
            + "Player Items: [], Player Location: 0], Target : [Absent], Pet : [Present]",
        mansiontest.roomInfo("Armory"));
  }

  // modified lookaround no items in same space, pet in same space
  @Test
  public void testplayerlookaroundnoitemsinsamespace() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "RoomName: Armory, " + "Players: [PlayerName: Iron Man, Player Limit: 4, "
            + "Player Items: [], Player Location: 0], Target : [Absent], Pet : [Present]",
        mansiontest.roomInfo("Armory"));
  }

  // modified lookaround no items in neighbors space, target present
  @Test
  public void testplayerlookaroundnoitemsinneighbor() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "[RoomName: Billiard Room, items: [], Players: [], "
            + "Target : [Present], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());
  }

  // modified lookaround items in neighbor space
  @Test
  public void testplayerlookarounditemsinneighbor() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    itemtest = new Item("Revolver", 3);
    roomtest.additem(itemtest);
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "[RoomName: Billiard Room, " + "items: [ItemName: Revolver, Damage value: 3], "
            + "Players: [], Target : [Present], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());
  }

  // target not in same or neighbor space
  @Test
  public void testlookaroundtargetnowhere() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(31, 31);
    pointtest2 = new Point(21, 20);
    roomtest = new Room(pointtest1, pointtest2, "Dining Hall");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(22, 13);
    pointtest2 = new Point(25, 18);
    roomtest = new Room(pointtest1, pointtest2, "Drawing Room");
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    assertEquals(
        "[RoomName: Billiard Room, items: [ItemName: Sharp Knife, Damage value: 2, "
            + "ItemName: Crepe Pan, Damage value: 7], Players: [PlayerName: Hulk, Player Limit: 5, "
            + "Player Items: [], Player Location: 1], Target : [Absent], Pet : [Absent]\n"
            + ", RoomName: Dining Hall, items: [], Players: [PlayerName: Hulk, Player Limit: 2, "
            + "Player Items: [], Player Location: 2], Target : [Absent], Pet : [Absent]\n"
            + ", RoomName: Drawing Room, items: [], Players: [PlayerName: Hulk, "
            + "Player Limit: 1, Player Items: [], Player Location: 3], "
            + "Target : [Absent], Pet : [Absent]\n" + "]",
        mansiontest.getneighborsforPlayermovement());
  }

  // determine if player A can see player B
  @Test
  public void testplayerAcanseeplayerB() {
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
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Billiard Room", "Hulk", 5);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "[\n" + "RoomName: Billiard Room, items: [], Players: [PlayerName: Hulk, Player Limit: 5, "
            + "Player Items: [], Player Location: 1], Target : [Present]\n]",
        mansiontest.getneighborsforPlayermovement());
  }

  // determine if player A can see player B in same space without pet
  @Test
  public void testplayerAcanseeplayerBsamespace() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 5);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "RoomName: Armory, items: [], " + "Players: [PlayerName: Iron Man, Player Limit: 4,"
            + " Player Items: [], Player Location: 0, " + "PlayerName: Hulk, Player Limit: 5, "
            + "Player Items: [], Player Location: 0], Target : [Absent], Pet : [Absent]",
        mansiontest.roomInfo("Armory"));
  }

  // determine if player A can see player B in same space with pet
  @Test
  public void testplayerAcanseeplayerBsamespacewithpet() {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    mansiontest.addRoom(roomtest);
    pointtest1 = new Point(16, 21);
    pointtest2 = new Point(21, 28);
    roomtest = new Room(pointtest1, pointtest2, "Billiard Room");
    mansiontest.addRoom(roomtest);
    mansiontest.addPlayertoRoom("Armory", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Armory", "Hulk", 5);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "RoomName: Armory, items: [], " + "Players: [PlayerName: Iron Man, Player Limit: 4,"
            + " Player Items: [], Player Location: 0, " + "PlayerName: Hulk, Player Limit: 5, "
            + "Player Items: [], Player Location: 0], Target : [Absent], Pet : [Present]",
        mansiontest.roomInfo("Armory"));
  }

  // determine if player A can see player B in neighbor space no pet
  @Test
  public void testplayerAcanseeplayerBneighborspacewithoutpet() {
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
    mansiontest.addPlayertoRoom("Billiard Room", "Iron Man", 4);
    mansiontest.addPlayertoRoom("Dining Hall", "Hulk", 5);
    mansiontest.getneighborsforPlayermovement();
    assertEquals(
        "[RoomName: Dining Hall, items: [], "
            + "Players: [PlayerName: Hulk, Player Limit: 5, Player Items: [],"
            + " Player Location: 2], Target : [Absent], Pet : [Absent]",
        mansiontest.getneighborsforPlayermovement());
  }

}
