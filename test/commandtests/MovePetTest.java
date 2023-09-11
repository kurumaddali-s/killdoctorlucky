package commandtests;

import static org.junit.Assert.fail;

import doctorlucky.commands.PlayingGameCommand;
import doctorlucky.commands.impl.PetMove;
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
 * A junit test class for the MovePet command class.
 *
 */

public class MovePetTest {

  private PlayingGameCommand controller;
  private Appendable out;
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
    controller = new PetMove();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullRoomName() {
    controller = new PetMove();

  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullOut() {
    controller = new PetMove();
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyRoom() {
    controller = new PetMove();
  }

  @Test
  public void testMove() {
    try {
      controller = new PetMove();
    } catch (IllegalArgumentException e) {
      fail("Illegal argument passed");

    }

  }

}
