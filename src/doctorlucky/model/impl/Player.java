package doctorlucky.model.impl;

import doctorlucky.model.PlayerReading;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * A Player class represented by playerName, playerLimit, playerLocation,
 * playerItems.
 */
public class Player implements PlayerReading {

  private String playerName;
  private int playerLimit;
  private int playerLocation;
  private final List<Item> playerItems;

  /**
   * Player constructor consists of string playerName, int playerLimit.
   * 
   * @param playerName  represents name of the player that is playing the game
   * @param playerLimit represents the total number of items player can carry
   */

  public Player(String playerName, int playerLimit) throws IllegalArgumentException {
    if (playerLimit < 0 || playerName == null) {
      throw new IllegalArgumentException("The value shouldn't be negative");
    }
    this.playerName = playerName;
    this.playerLimit = playerLimit;
    this.playerItems = new ArrayList<Item>();
  }

  @Override
  public String getPlayerName() {
    return this.playerName;
  }

  @Override
  public int getPlayerLimit() {
    return this.playerLimit;
  }

  @Override
  public String toString() {
    return String.format("PlayerName: %s, Player Limit: %s, Player Items: %s, Player Location: %s",
        this.playerName, this.playerLimit, this.getPlayerItems(), this.getPlayerLocation());
  }

  @Override
  public void addItemstoPlayer(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }
    this.playerItems.add(item);
  }

  @Override
  public List<Item> getPlayerItems() {

    List<Item> copy = this.playerItems.stream().collect(Collectors.toList());
    return copy;
  }

  @Override
  public int getPlayerLocation() {
    return this.playerLocation;
  }

  @Override
  public void setPlayerPosition(int playerLocation) {
    if (playerLocation < 0) {
      throw new IllegalArgumentException("This value cannot be null");
    }
    this.playerLocation = playerLocation;
  }

  @Override
  public void decrementPlayerLimit() {
    this.playerLimit -= 1;
  }

  @Override
  public void removeItems(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("This value cannot be null");
    }
    this.playerItems.remove(item);
  }
}
