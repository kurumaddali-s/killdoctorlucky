package doctorlucky.model.impl;

import doctorlucky.model.RoomReading;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Room class is represented by Point topLeft, bottomRight, roomName.
 */
public class Room implements RoomReading {
  private final Point topLeft;
  private final Point bottomRight;
  private final String roomName;
  private final List<Item> items;
  private final List<Player> players;

  /**
   * Room constructor Point objects topleft, bottomright, String roomname.
   * 
   * @param topleft     represents row, column coordinate of the top left corner
   *                    of the room, it is from the Point class.
   * @param bottomright represents row, column coordinate of the bottom right
   *                    corner of the room, it is from the Point class.
   * @param roomname    represents name of the room.
   */
  public Room(Point topleft, Point bottomright, String roomname) {
    if (topleft == null || bottomright == null || roomname == null) {
      throw new IllegalArgumentException("The value should not be negative");
    }
    this.topLeft = topleft;
    this.bottomRight = bottomright;
    this.roomName = roomname;
    this.items = new ArrayList<Item>();
    this.players = new ArrayList<Player>();

  }

  @Override
  public String getRoomName() {
    return this.roomName;
  }

  @Override
  public Point getTopLeft() {
    return this.topLeft;
  }

  @Override
  public Point getBottomRight() {
    return this.bottomRight;
  }

  @Override
  public void additem(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("The value cant be null");
    }
    this.items.add(item);
  }

  @Override
  public List<Item> getItems() {
    List<Item> copy = this.items.stream().collect(Collectors.toList());
    return copy;
  }

  @Override
  public int getTotalItems() {
    return items.size();
  }

  @Override
  public String toString() {

    return String.format("RoomName: %s, items: %s, Players: %s", this.roomName, this.items,
        this.players);

  }

  @Override
  public void addPlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player cannot be null");
    }
    this.players.add(player);
  }

  @Override
  public void removePlayer(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player cannot be null");
    }
    this.players.remove(player);
  }

  @Override
  public void removeItems(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }
    this.items.remove(item);
  }

  @Override
  public List<Player> getPlayers() {
    List<Player> copy = this.players.stream().collect(Collectors.toList());
    return copy;
  }

  @Override
  public int getNumberofPlayers() {
    return this.players.size();
  }
}
