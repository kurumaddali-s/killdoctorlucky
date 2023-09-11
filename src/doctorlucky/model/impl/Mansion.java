package doctorlucky.model.impl;

import doctorlucky.model.Facade;
import doctorlucky.model.MansionReading;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This class represents the world and implements the Facade and MansionReading
 * interface. The world is composed of rooms, items, players, target character
 * and a pet. This class represents multiple functionalities to play the game
 * such as retrieving the room, player, pet and the target character
 * information.
 */
public class Mansion implements Facade, MansionReading {

  private int totalRows;
  private int totalColumns;
  private String mansionName;
  private List<Room> rooms;
  private List<Player> playerList;
  private Target target;
  private RandomPlayer randomPlayer;
  private Pet pet;
  private Room petRoom;
  private int turn;
  private int maxTurns;

  /**
   * Mansion constructor consists of totalrows,totalcolumns.
   *
   * @param totalRows    represents total rows in the mansion.
   * @param totalColumns represents total columns in the mansion.
   * @param mansionName  represents name of the mansion.
   * @param target       represents target object.
   */

  public Mansion(int totalRows, int totalColumns, String mansionName, Target target, Pet pet,
      int maxTurns) throws IllegalArgumentException {
    if (totalRows < 0 || totalColumns < 0 || target == null || pet == null || mansionName == null
        || maxTurns < 0) {
      throw new IllegalArgumentException("Invalid parameters");
    }
    this.totalRows = totalRows;
    this.totalColumns = totalColumns;
    this.mansionName = mansionName;
    this.rooms = new ArrayList<Room>();
    this.playerList = new ArrayList<Player>();
    this.target = target;
    this.pet = pet;
    this.turn = 0;
    this.randomPlayer = new RandomPlayer();
    this.maxTurns = maxTurns;
  }

  @Override
  public int getTotalRows() {
    return this.totalRows;
  }

  @Override
  public int getTotalColumns() {
    return this.totalColumns;
  }

  @Override
  public String getMansionName() {
    return this.mansionName;
  }

  @Override
  public int getTotalRooms() {
    return rooms.size();
  }

  @Override
  public int getTotalItems() {
    int count = 0;
    for (Room room : rooms) {
      for (Item item : room.getItems()) {
        count = count + 1;
      }
    }
    return count;
  }

  @Override
  public List<Room> getRooms() {
    List<Room> copy = this.rooms.stream().collect(Collectors.toList());
    return copy;
  }

  @Override
  public List<Player> getPlayerList() {
    List<Player> copy = this.playerList.stream().collect(Collectors.toList());
    return copy;
  }

  @Override
  public Target getTarget() {
    return this.target;
  }

  @Override
  public Room getRoomById(int id) {
    if (id < 0) {
      throw new IllegalArgumentException("This value shouldn't be negative");
    }
    return this.rooms.get(id);
  }

  @Override
  public Room getRoomByName(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("This value shouldn't be null");
    }
    for (Room room : rooms) {
      if (room.getRoomName().equals(roomName)) {
        return room;
      }
    }
    return null;
  }

  @Override
  public List<String> getNeighbors(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("This value cannot be null");
    }
    Room room = this.getRoomByName(roomName);
    if (room == null) {
      throw new IllegalArgumentException("There is no such room in the mansion");
    }
    int r1 = room.getTopLeft().getPointRow();
    int c1 = room.getTopLeft().getPointColumn();
    int r2 = room.getBottomRight().getPointRow();
    int c2 = room.getBottomRight().getPointColumn();
    List<String> neighbours = new ArrayList<String>();
    for (Room room1 : rooms) {
      String room1Name = room1.getRoomName();
      int rc1 = room1.getTopLeft().getPointRow();
      int cc1 = room1.getTopLeft().getPointColumn();
      int cc2 = room1.getBottomRight().getPointColumn();
      int confirm = 0;
      int confirm1 = 0;
      if (c1 == cc2 + 1) {
        confirm1 = confirm1 + 1;
      }
      if (rc1 == r2 + 1) {
        confirm = confirm + 1;
      }
      if (c1 == cc1 + 1) {
        confirm1 = confirm1 + 1;
      }
      int rc2 = room1.getBottomRight().getPointRow();
      if (r1 == rc2 + 1) {
        confirm = confirm + 1;
      }
      if (cc1 == c2 + 1) {
        confirm1 = confirm1 + 1;
      }

      for (int j = r1; j <= r2; j++) {
        for (int i = rc1; i <= rc2; i++) {
          if (i == j) {
            confirm = confirm + 1;
          }
        }
      }
      for (int j = c1; j <= c2; j++) {
        for (int i = cc1; i <= cc2; i++) {
          if (i == j) {
            confirm1 = confirm1 + 1;
          }
        }
      }
      if (confirm >= 1 && confirm1 >= 1) {
        if (!roomName.equals(room1Name)) {
          neighbours.add(room1Name);
        }
      }
    }
    return neighbours;
  }

  @Override
  public void addRoom(Room room) {
    if (room == null) {
      throw new IllegalArgumentException("The item cannot be null");
    }
    this.rooms.add(room);
  }

  @Override
  public void moveTarget(Target target) {
    if (target == null) {
      throw new IllegalArgumentException("The target cannot be null");
    }
    target.incrementTargetPosition();
    if (target.getTargetPosition() == rooms.size()) {
      target.resetTargetPosition(0);
    }
  }

  @Override
  public String getTargetRoom(Target target) {
    if (target == null) {
      throw new IllegalArgumentException("The target cannot be null");
    }
    this.target = target;
    Room targetRoom = this.rooms.get(target.getTargetPosition());
    return targetRoom.getRoomName();
  }

  @Override
  public int getTargetPosition(Target target) {
    if (target == null) {
      throw new IllegalArgumentException("The target cannot be null");
    }
    return target.getTargetPosition();
  }

  @Override
  public List<Item> getParticularRoomItems(String roomNameforItem) {
    if (roomNameforItem == null) {
      throw new IllegalArgumentException("The roomname cannot be null");
    }
    List<Item> itemArrayList = new ArrayList<Item>();
    Room r = getRoomByName(roomNameforItem);
    for (Item item : r.getItems()) {
      itemArrayList.add(item);
    }
    return itemArrayList;
  }

  @Override
  public String toString() {
    return String.format("Mansion Name: %s \n total rows: %s \n total columns: %s\n rooms: %s",
        this.mansionName, this.totalRows, this.totalColumns, this.rooms);
  }

  @Override
  public Player setPlayer(String roomName, int playerLimit) {
    if (roomName == null || playerLimit < 0) {
      throw new IllegalArgumentException("The values cant be null or negative");
    }
    Player player = new Player(roomName, playerLimit);
    return player;
  }

  @Override
  public void addPlayertoPlayerList(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player cannot be null");
    }
    playerList.add(player);

  }

  @Override
  public Player getPlayerbyname(String playerName) {
    if (playerName == null) {
      throw new IllegalArgumentException("The player name HERE cannot be null");
    }
    for (Player player : playerList) {
      if (player.getPlayerName().equals(playerName)) {
        return player;
      }
    }
    return null;
  }

  @Override
  public List<Player> getParticularRoomPlayers(String roomNameforPlayer) {
    if (roomNameforPlayer == null) {
      throw new IllegalArgumentException("The roomName cannot be null");
    }
    List<Player> playerArrayList = new ArrayList<Player>();
    Room r = getRoomByName(roomNameforPlayer);
    if (r == null) {
      throw new IllegalArgumentException("RoomName is invalid");
    }
    for (Player player : r.getPlayers()) {
      playerArrayList.add(player);
    }
    return playerArrayList;
  }

  @Override
  public void setPlayerPosition(String rooomName, Player player) {
    if (player == null || rooomName == null) {
      throw new IllegalArgumentException("This value cannot be null");
    }
    Room room = getRoomByName(rooomName);
    for (int i = 0; i < rooms.size(); i++) {
      if (room == getRoomById(i)) {
        player.setPlayerPosition(i);
      }
    }
  }

  @Override
  public String getPlayerCurrentRoom(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player name cannot be null");
    }
    Room playerRoom = this.rooms.get(player.getPlayerLocation());
    return playerRoom.getRoomName();
  }

  @Override
  public String getneighborsforPlayermovement() {

    String playerName1 = playerList.get(turn % playerList.size()).getPlayerName();

    Player player = getPlayerByName(playerName1);
    String currentRoomPlayer = this.getPlayerCurrentRoom(player);
    String string = null;
    List<String> playerNeighborslist = new ArrayList<String>();
    if (this.pet.getPetPosition() == 0) {
      this.petRoom = getRoomById(0);
    }
    playerNeighborslist.add(roomInfo(currentRoomPlayer) + "\n");
    for (String roomNameneighbors : getNeighbors(currentRoomPlayer)) {
      if (roomNameneighbors.equals(getPetRoom(this.pet).getRoomName())) {
        continue;
      }
      playerNeighborslist.add(roomInfo(roomNameneighbors) + "\n");
    }
    string = String.valueOf(playerNeighborslist);
    moveTarget(this.target);
    turn = turn + 1;
    resetMaxTurns();
    String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
    if ("computer".equals(isComputer)) {
      String computerTurnOutput = computerPlayerstuff();
      return String.format("%s\n\n%s", string, computerTurnOutput);
    }
    return string;
  }

  @Override
  public void resetPlayerLimit(Player player) {
    if (player == null) {
      throw new IllegalArgumentException("The player name cannot be null");
    }
    if (player.getPlayerLimit() != 0) {
      player.decrementPlayerLimit();
    }
  }

  @Override
  public String createComputerPlayer() {
    int randomPlayerLimit = randomPlayer.nextInt(5);
    Player computerPlayer = new Player("computer", randomPlayerLimit);
    Room roomRandom = getRoomById(randomPlayer.nextInt(getTotalRooms() - 1));
    addPlayertoRoom(roomRandom.getRoomName(), "computer", randomPlayerLimit);
    return "Computer player added to room " + roomRandom.getRoomName();
  }

  @Override
  public String addItemstoPlayer(String itemName) {
    if (itemName == null) {
      throw new IllegalArgumentException("The value name cannot be null");
    }

    String playerName1 = playerList.get(turn % playerList.size()).getPlayerName();
    Player player = getPlayerByName(playerName1);
    String roomName = getPlayerCurrentRoom(player);
    if (getRoomByName(roomName).getTotalItems() == 0) {
      moveTarget(getTarget());
      turn = turn + 1;
      resetMaxTurns();
      String noItems = "No items present in this room";
      String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
      if ("computer".equals(isComputer)) {
        String computerTurnOutput = computerPlayerstuff();
        return String.format("%s\n\nComputer's Turn\n%s", noItems, computerTurnOutput);
      }
      return noItems;
    }

    String string = "";
    if (player.getPlayerLimit() > 0) {
      for (Item item : getParticularRoomItems(roomName)) {
        if (itemName.equals(item.getItemName())) {
          getRoomByName(roomName).removeItems(item);
          player.addItemstoPlayer(item);
          resetPlayerLimit(player);
          break;
        }
      }
    } else {
      string = "\nItems cannot be added  as player limit has been crossed\n";
      moveTarget(this.target);
      turn = turn + 1;
      resetMaxTurns();
      String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
      if ("computer".equals(isComputer)) {
        String computerTurnOutput = computerPlayerstuff();
        return String.format("%s\n\nComputer's Turn\n%s", string, computerTurnOutput);
      }

      return string;
    }

    string = String.format("\nRoom %s renewed details :\n%s", getPlayerCurrentRoom(player),
        roomInfo(roomName));
    String sf1 = String.format("Item %s picked by player %s", itemName, playerName1);
    String sf2 = String.format("%s \n%s", string, sf1);
    moveTarget(this.target);
    turn = turn + 1;
    resetMaxTurns();
    String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
    if ("computer".equals(isComputer)) {
      String computerTurnOutput = computerPlayerstuff();
      return String.format("%s\n\nComputer's Turn\n%s", sf2, computerTurnOutput);
    }
    return sf2;

  }

  @Override
  public String addPlayertoRoom(String roomName, String playerName, int playerLimit) {
    if (roomName == null || playerName == null || playerLimit < 0) {
      throw new IllegalArgumentException("The value name cannot be null");
    }
    Player player = setPlayer(playerName, playerLimit);
    setPlayerPosition(roomName, player);
    addPlayertoPlayerList(player);
    getRoomByName(roomName).addPlayer(player);
    return playerName + " added to room " + roomName;
  }

  @Override
  public String playerMove(String roomMove) {
    if (roomMove == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    String playerName1 = playerList.get(turn % playerList.size()).getPlayerName();

    Player player = getPlayerByName(playerName1);
    List<String> neighborArrayList = new ArrayList<String>();
    String currentRoomPlayer = getPlayerCurrentRoom(player);

    if (currentRoomPlayer.equals(roomMove)) {
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(player.toString());
      stringBuilder.append(", ");
      stringBuilder.append(currentRoomPlayer);
      return String.valueOf(stringBuilder);
    }

    neighborArrayList = getNeighbors(currentRoomPlayer);
    String string = null;
    int count = 0;
    String currentRoom = getPlayerCurrentRoom(player);
    for (int i = 0; i < neighborArrayList.size(); i++) {
      String room = neighborArrayList.get(i);
      if (neighborArrayList.get(i).equals(roomMove)) {
        count = count + 1;
        setPlayerPosition(room, player);
        getRoomByName(room).addPlayer(player);
        getRoomByName(currentRoom).removePlayer(player);
        break;
      } else {
        continue;
      }
    }
    if (count < 1) {
      string = "Invalid neighbor";
      moveTarget(getTarget());
      turn = turn + 1;
      resetMaxTurns();
      String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
      if ("computer".equals(isComputer)) {
        String computerTurnOutput = computerPlayerstuff();
        return String.format("%s\n\nComputer's Turn\n%s", string, computerTurnOutput);
      }
      return string;
    }

    String sf1 = String.format("\nRoom %s renewed details :\n %s", roomMove, roomInfo(roomMove));
    moveTarget(this.target);
    String targetRoom = getTargetRoom(this.target);
    String sf0 = "";
    if (roomMove.equals(targetRoom)) {
      if (player.getPlayerName().equals("computer")) {
        sf0 = randomattemptToKillTarget();
      }
    }
    String sf3 = String.format("%s%s", sf1, sf0);
    turn = turn + 1;
    resetMaxTurns();
    String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
    if ("computer".equals(isComputer)) {
      String computerTurnOutput = computerPlayerstuff();
      return String.format("%s\n\nComputer's Turn\n%s", sf3, computerTurnOutput);
    }
    return sf3;

  }

  @Override
  public Player getPlayerByName(String playerName) {
    if (playerName == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    for (Player player : playerList) {
      if (player.getPlayerName().equals(playerName)) {
        return player;
      }
    }
    return null;
  }

  @Override
  public String randomattemptToKillTarget() {
    String itemName = null;
    Player player = getPlayerByName("computer");
    List<Item> computerItemlist = new ArrayList<Item>();
    computerItemlist = player.getPlayerItems();
    int maxdamage = 0;
    int k = 0;
    if (computerItemlist.size() == 0) {
      itemName = "poke";
    } else {
      for (int i = 0; i < computerItemlist.size(); i++) {
        Item item = computerItemlist.get(i);
        if (item.getDamageValue() > maxdamage) {
          maxdamage = item.getDamageValue();
          k = i;
        }
      }
      itemName = computerItemlist.get(k).getItemName();
    }
    String attemptToKillTarget = attemptToKillTarget(itemName);

    return attemptToKillTarget;
  }

  @Override
  public String attemptToKillTarget(String itemName) {

    if (itemName == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    String playerName = playerList.get(turn % playerList.size()).getPlayerName();

    if (this.target.getHealth() <= 0) {
      String gameover = String.format("Game over as target dead");
      return gameover;
    }
    if (this.pet.getPetPosition() == 0) {
      this.petRoom = getRoomById(0);
    }
    String roomName = getPlayerCurrentRoom(getPlayerByName(playerName));
    Room currentRoom = getRoomByName(roomName);
    String targetRoom = getTargetRoom(this.target);
    List<String> neighborArrayList = new ArrayList<String>();
    int count = 0;
    if (roomName.equals(targetRoom)) {
      neighborArrayList = getNeighbors(roomName);
      for (String room : neighborArrayList) {
        Room neighborroom = getRoomByName(room);
        if (neighborroom.getNumberofPlayers() != 0) {
          count = count + 1;
        }
      }

      if (roomName.equals(this.petRoom.getRoomName()) && currentRoom.getNumberofPlayers() == 1) {
        count = 0;
      }

      if (count == 0) {
        String x1 = String.format("\nAn attempt can be made to kill the target as ");
        String x2 = String.format("both player %s and", playerName);
        String x3 = String.format(" target are in the same room %s and no other ", roomName);
        String x4 = String.format("player can see the player %s", playerName);
        String sf1 = String.format("%s%s%s%s", x1, x2, x3, x4);
        if ("poke".equals(itemName)) {
          int health = this.target.getHealth() - 1;
          this.target.resetTargetHealth(health);
          if (this.target.getHealth() <= 0) {
            String sf5 = String.format("\nTarget's renewed health: %d", this.target.getHealth());
            String sf4 = String.format("\nGAME OVER TARGET IS DEAD");
            String sf7 = String.format("\nWINNER PLAYER : %s", playerName);
            String sf8 = String.format("%s%s%s", sf5, sf4, sf7);

            return sf8;
          }
          String y1 = String.format("\nAn attempt to kill the target was successful ");
          String y2 = String.format("by player %s", playerName);
          String y3 = String.format("\nTarget's renewed health: %d", this.target.getHealth());
          String sf2 = String.format("%s%s%s", y1, y2, y3);
          String sf3 = String.format("%s%s", sf1, sf2);

          moveTarget(this.target);
          turn = turn + 1;
          resetMaxTurns();

          String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
          if ("computer".equals(isComputer)) {
            String computerTurnOutput = computerPlayerstuff();
            return String.format("%s\n\n%s", sf3, computerTurnOutput);
          }

          return sf3;
        } else {
          int anothercount = 0;
          List<Item> playerItems = new ArrayList<Item>();
          Item weapon = null;
          playerItems = getPlayerByName(playerName).getPlayerItems();
          for (Item item : playerItems) {
            if (item.getItemName().equals(itemName)) {
              anothercount = anothercount + 1;
              weapon = item;
            }
          }

          if (anothercount > 0) {
            int health = this.target.getHealth() - weapon.getDamageValue();
            this.target.resetTargetHealth(health);
            if (this.target.getHealth() <= 0) {
              String sf5 = String.format("\nTarget's renewed health: %d", this.target.getHealth());
              String sf4 = String.format("\nGAME OVER TARGET IS DEAD");
              String sf7 = String.format("\nWINNER PLAYER : %s", playerName);
              String sf8 = String.format("%s%s%s", sf5, sf4, sf7);

              return sf8;
            }
            getPlayerByName(playerName).removeItems(weapon);
            String z1 = String.format("\nAn attempt to kill the target was successful ");
            String z2 = String.format("by player %s", playerName);
            String z3 = String.format("\nTarget's renewed health: %d", this.target.getHealth());
            String sf2 = String.format("%s%s%s", z1, z2, z3);
            String z4 = String.format("\nItem used as weapon %s ", weapon.getItemName());
            String z5 = String.format("removed from the game and used as evidence");
            String z6 = String.format("\nPlayer's renewed details %s: ",
                getPlayerByName(playerName).toString());
            String sf3 = String.format("%s%s%s", z4, z5, z6);
            String sf4 = String.format("%s%s%s", sf1, sf2, sf3);
            moveTarget(this.target);

            turn = turn + 1;
            resetMaxTurns();
            String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
            if ("computer".equals(isComputer)) {
              String computerTurnOutput = computerPlayerstuff();
              return String.format("%s\n\n%s", sf4, computerTurnOutput);
            }
            return sf4;

          } else {
            String sf = String.format("\nAttempt unsuccessful invalid weapon name");
            moveTarget(this.target);
            turn = turn + 1;
            resetMaxTurns();

            String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
            if ("computer".equals(isComputer)) {
              String computerTurnOutput = computerPlayerstuff();
              return String.format("%s\n\n%s", sf, computerTurnOutput);
            }

            return sf;
          }

        }
      } else {
        String sf1 = String.format("\nAttempt unsuccessful as players in neigbouring rooms");
        moveTarget(this.target);
        turn = turn + 1;
        resetMaxTurns();

        String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
        if ("computer".equals(isComputer)) {
          String computerTurnOutput = computerPlayerstuff();
          return String.format("%s\n\n%s", sf1, computerTurnOutput);
        }

        return sf1;
      }

    } else {
      String sf1 = String.format(
          "Attempt cannot be made as target and player %s in " + "different rooms", playerName);
      moveTarget(this.target);
      turn = turn + 1;
      resetMaxTurns();

      String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
      if ("computer".equals(isComputer)) {
        String computerTurnOutput = computerPlayerstuff();
        return String.format("%s\n\n%s", sf1, computerTurnOutput);
      }

      return sf1;
    }

  }

  @Override
  public String roomInfo(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    if (this.pet.getPetPosition() == 0) {
      this.petRoom = getRoomById(0);
    }
    String sf1 = "";
    String targetRoom = getTargetRoom(this.target);
    if (roomName.equals(targetRoom)) {
      sf1 = String.format("%s, Target : [Present]", getRoomByName(roomName).toString());

    } else {
      sf1 = String.format("%s, Target : [Absent]", getRoomByName(roomName).toString());
    }
    if (roomName.equals(this.petRoom.getRoomName())) {
      String sf2 = String.format("%s, Pet : [Present]", sf1);
      return sf2;
    }

    String sf2 = String.format("%s, Pet : [Absent]", sf1);
    return sf2;

  }

  @Override
  public Pet getPet() {
    return this.pet;
  }

  @Override
  public String petMove(String roomName) {
    if (roomName == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    if (this.pet.getPetPosition() == 0) {
      this.petRoom = getRoomById(0);
    }
    try {
      Room room = getRoomByName(roomName);

      String sf2 = String.format("\nPet is currently in room %s", this.petRoom.toString());
      int count = 0;
      for (int i = 0; i < rooms.size(); i++) {
        if (room == getRoomById(i)) {
          this.pet.setPetPosition(i);
          count = count + 1;
          break;
        }
      }
      if (count > 0) {
        if (!rooms.contains(petRoom)) {
          this.rooms.add(petRoom);
        }
        if (this.rooms.contains(room)) {
          this.petRoom = room;
        }
        String sf1 = String.format("\nPet moved to room %s", roomInfo(room.getRoomName()));
        String sf3 = String.format("%s%s", sf2, sf1);
        // this.rooms.remove(room);
        moveTarget(this.target);
        turn = turn + 1;
        resetMaxTurns();
        String isComputer = playerList.get(turn % playerList.size()).getPlayerName();
        if ("computer".equals(isComputer)) {
          String computerTurnOutput = computerPlayerstuff();
          return String.format("%s\n\n%s", sf3, computerTurnOutput);
        }
        return sf3;
      }

    } catch (NullPointerException e) {
      throw new NullPointerException("No such room in Dr.Lucky's world");
    }
    turn = turn + 1;
    resetMaxTurns();
    return "No such room in Dr.Lucky's world";
  }

  @Override
  public Room getPetRoom(Pet pet) {
    if (pet == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    return this.petRoom;
  }

  @Override
  public String playerInfo() {
    String playerName = getPlayerWhoseTurnItIs();
    if (playerName == null) {
      throw new IllegalArgumentException("The value cannot be null");
    }
    Player player = getPlayerByName(playerName);
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append(playerName + "'s turn now\n");
    stringBuilder.append(player.toString());
    stringBuilder.append(", ");
    stringBuilder.append(getPlayerCurrentRoom(player));
    stringBuilder.append("\nTarget Health: ").append(this.target.getHealth());
    stringBuilder.append("\nTurns Left: ").append(this.maxTurns);
    return String.valueOf(stringBuilder);
  }

  @Override
  public boolean isTargetDead() {
    if (this.target.getHealth() <= 0) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String computerPlayerstuff() {
    Player computerplayer = getPlayerByName("computer");
    // command selection
    List<String> commandList = new ArrayList<String>();
    commandList.add("pickitem");
    commandList.add("lookaround");
    commandList.add("move");
    int randomindex = randomPlayer.nextInt(3);

    String finalcommand = commandList.get(randomindex);

    if ("pickitem".equals(finalcommand)) {

      String randomItemName = null;
      List<Item> computerItemlist = new ArrayList<Item>();
      computerItemlist = getParticularRoomItems(getPlayerCurrentRoom(computerplayer));

      int randomindex1 = 0;

      if (computerItemlist.size() > 0) {
        randomindex1 = randomPlayer.nextInt(computerItemlist.size());
        randomItemName = computerItemlist.get(randomindex1).getItemName();
        String pick = addItemstoPlayer(randomItemName);
        return String.format("%s\n%s", finalcommand, pick);
      } else {
        turn = turn + 1;
        resetMaxTurns();
        return "No items to pick in this room";
      }

      // random lookaround
    } else if ("lookaround".equals(finalcommand)) {
      String lookaround = getneighborsforPlayermovement();
      return String.format("%s\n%s", finalcommand, lookaround);

      // random move
    } else {
      List<String> computerNeighbor = new ArrayList<String>();
      String currentRoomPlayer = this.getPlayerCurrentRoom(computerplayer);
      computerNeighbor = getNeighbors(currentRoomPlayer);

      int randomindex2 = 0;
      String computerPlayerchangeroom = null;

      if (computerNeighbor.size() > 0) {
        randomindex2 = randomPlayer.nextInt(computerNeighbor.size());
        computerPlayerchangeroom = computerNeighbor.get(randomindex2);
      }

      String move = playerMove(computerPlayerchangeroom);
      return String.format("%s\n%s", finalcommand, move);
    }

  }

  private HashMap<String, List<Integer>> roomCoords() {
    HashMap<String, List<Integer>> viewCoords = new HashMap<>();
    List<Integer> newCoords = new ArrayList<>();
    for (Room room2 : rooms) {
      int originalTopLeftRow = room2.getTopLeft().getPointRow();
      int originalTopLeftCol = room2.getTopLeft().getPointColumn();
      int originalBottomRightRow = room2.getBottomRight().getPointRow();
      int originalBottomRightCol = room2.getBottomRight().getPointColumn();

      int width = (originalBottomRightCol + 1 - originalTopLeftCol) * 25;
      int height = (originalBottomRightRow + 1 - originalTopLeftRow) * 25;

      int newXlimit1 = originalTopLeftCol * 25;
      int newXlimit2 = (originalTopLeftCol * 25) + width;
      int newYlimit1 = originalTopLeftRow * 25;
      int newYlimit2 = (originalTopLeftRow * 25) + height;
      newCoords = Arrays.asList(newXlimit1, newYlimit1, newXlimit2, newYlimit2);

      viewCoords.put(room2.getRoomName(), newCoords);

    }
    return viewCoords;
  }

  @Override
  public String roomCoordFromView(int row, int col) {
    if (row < 0 || col < 0) {
      throw new IllegalArgumentException("These values cannot be negative");
    }
    HashMap<String, List<Integer>> checkCoords = new HashMap<>();
    String roomName = null;
    checkCoords = roomCoords();

    for (Map.Entry<String, List<Integer>> i : checkCoords.entrySet()) {
      if ((row > i.getValue().get(0) && row < i.getValue().get(2))
          && (col > i.getValue().get(1) && col < i.getValue().get(3))) {
        return (i.getKey());
      }
    }
    return roomName;
  }

  // to do
  @Override
  public String getPlayerWhoseTurnItIs() {
    if (playerList.size() > 0) {
      String playerName = playerList.get(turn % playerList.size()).getPlayerName();
      return playerName;
    }

    return "";

  }

  @Override
  public List<String> getRoomNames() {
    List<String> roomNames = new ArrayList<>();
    for (Room room : rooms) {
      roomNames.add(room.getRoomName());
    }
    return roomNames;
  }

  @Override
  public List<String> getRoomItemsCurrentPlayer() {
    List<Item> itemNamesList = new ArrayList<>();
    List<String> itemNames = new ArrayList<>();
    String playerName = getPlayerWhoseTurnItIs();
    String roomName = getPlayerCurrentRoom(getPlayerByName(playerName));
    itemNamesList = getParticularRoomItems(roomName);

    for (int i = 0; i < itemNamesList.size(); i++) {
      itemNames.add(itemNamesList.get(i).getItemName());
    }
    return itemNames;
  }

  @Override
  public List<String> getPlayerItems() {
    List<String> playerItems = new ArrayList<>();
    playerItems.add("poke");
    String playerName = getPlayerWhoseTurnItIs();
    Player player = getPlayerByName(playerName);

    for (int i = 0; i < player.getPlayerItems().size(); i++) {
      playerItems.add(player.getPlayerItems().get(i).getItemName());
    }
    return playerItems;
  }

  @Override
  public boolean isGameover() {
    if (maxTurns <= 0 || target.getHealth() <= 0) {
      return true;
    }
    return false;
  }

  @Override
  public void resetMaxTurns() {
    if (maxTurns > 0) {
      maxTurns = maxTurns - 1;
    }
  }

  @Override
  public String messageMaxTurns() {
    if (maxTurns <= 0) {
      return "\nMAXIMUM TURNS REACHED GAMEOVER\n";
    } else if (target.getHealth() <= 0) {
      return "\nTARGET DEAD GAME OVER\n";
    }
    return "\nGAME OVER MAXIMUM TURNS REACHED AND TARGET DEAD GAMEOVER\n";
  }

  @Override
  public void resetGame(String filepath) {
    if (filepath == null) {
      throw new IllegalArgumentException("Invalid input values");
    }
    Model modelReading = new Model();
    Mansion mansion = null;
    File mansionFile = new File(filepath);
    mansion = modelReading.processMansionFile(mansionFile, 15);
    this.totalRows = mansion.getTotalRows();
    this.totalColumns = mansion.getTotalColumns();
    this.mansionName = mansion.getMansionName();
    this.target = mansion.getTarget();
    this.pet = mansion.getPet();
    this.maxTurns = 15;
    this.rooms = mansion.getRooms();
    this.turn = 0;
    this.randomPlayer = new RandomPlayer();
    this.playerList = mansion.getPlayerList();

  }

  @Override
  public String getTargetRoomName() {
    Room targetRoom = this.rooms.get(target.getTargetPosition());
    return targetRoom.getRoomName();
  }

  @Override
  public String getPetRoomNormal() {
    if (this.pet.getPetPosition() == 0) {
      this.petRoom = getRoomById(0);
    }
    return petRoom.getRoomName();
  }

}
