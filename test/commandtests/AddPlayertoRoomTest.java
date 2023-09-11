package commandtests;

import static org.junit.Assert.fail;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.commands.impl.AddPlayertoRoom;
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
 * A junit test class for the PlayerLookout command class.
 *
 */

public class AddPlayertoRoomTest {

  private PlayingGameCommand controller;
  private Mansion mansiontest;
  private Room roomtest;
  private Item itemtest;
  private Point pointtest1;
  private Point pointtest2;
  private Target targettest;
  private Player playertest;
  private Pet pettest;

  @Before
  public void setup() throws IOException {
    pointtest1 = new Point(22, 19);
    pointtest2 = new Point(23, 26);
    itemtest = new Item("Revolver", 3);
    roomtest = new Room(pointtest1, pointtest2, "Armory");
    targettest = new Target("Doctor Lucky", 50);
    pettest = new Pet("Fortune", "Cat");
    playertest = new Player("one", 3);
    mansiontest = new Mansion(36, 30, "Doctor Lucky's Mansion", targettest, pettest, 15);
    controller = new AddPlayertoRoom("Armory", "one", 2);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRoomName() {
    controller = new AddPlayertoRoom(null, "one", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullPlayerrName() {
    controller = new AddPlayertoRoom("Armory", null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPlayerLimit() {
    controller = new AddPlayertoRoom("Armory", "one", -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRoom() {
    controller = new AddPlayertoRoom("", "one", 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyPlayer() {
    controller = new AddPlayertoRoom("Armory", "", 2);
  }

  @Test
  public void testAddPlayertoRoom() {
    try {
      controller = new AddPlayertoRoom("Armory", "one", 2);
    } catch (IllegalArgumentException e) {
      fail("Illegal argument passed");
    }
  }

}
