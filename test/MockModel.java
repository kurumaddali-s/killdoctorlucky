import doctorlucky.model.Facade;
import doctorlucky.model.impl.Item;
import doctorlucky.model.impl.Pet;
import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Room;
import doctorlucky.model.impl.Target;
import java.util.ArrayList;
import java.util.List;

/**
 * A MockModel class mocks the mansion class, it implements Facade interface
 * which contains all the methods necessary to run the Controller class.
 */
public class MockModel implements Facade {

  private StringBuilder log;
  private int uniqueCode;

  /**
     * Constructs the mock model object.
     * @param log string to log the values
     * @param uniqueCode uniquecode
     */
  public MockModel(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public String playerMove(String roomMove) {
    String result = String.format("RoomName: %s, UniqueCode: %d\n", roomMove, uniqueCode);
    return String.valueOf(log.append(result));
  }

  @Override
  public String addPlayertoRoom(String roomName, String playerName, int playerLimit) {
    String result = String
        .valueOf(log.append("\nPlayer Information: name - " + playerName + "; RoomName - "
            + roomName + "; itemLimit - " + playerLimit + ". UniqueCode: " + uniqueCode + "\n"));
    return result;
  }

  @Override
  public String addItemstoPlayer(String itemName) {
    return String.valueOf(log.append(
        "\nAdd items to player:" + itemName + "itemName . UniqueCode: " + uniqueCode + "\n"));
  }

  @Override
  public Player getPlayerbyname(String playerName) {
    return null;
  }

  @Override
  public String createComputerPlayer() {
    return String.valueOf(log.append("Computer player created " + uniqueCode + "\n"));
  }

  @Override
  public Room getRoomByName(String roomName) {
    return null;
  }

  @Override
  public String getPlayerCurrentRoom(Player player) {
    return String.valueOf(log.append("Current Player " + player + uniqueCode + "\n"));
  }

  @Override
  public void resetPlayerLimit(Player player) {
  }

  @Override
  public String getneighborsforPlayermovement() {
    return null;
  }

  @Override
  public void setPlayerPosition(String rooomName, Player player) {

  }

  @Override
  public List<Player> getParticularRoomPlayers(String roomNameforPlayer) {
    return null;
  }

  @Override
  public void addPlayertoPlayerList(Player player) {

  }

  @Override
  public Player setPlayer(String roomName, int playerLimit) {
    return null;
  }

  @Override
  public List<Item> getParticularRoomItems(String roomNameforItem) {
    return null;
  }

  @Override
  public void addRoom(Room room) {

  }

  @Override
  public List<String> getNeighbors(String roomName) {
    return null;
  }

  @Override
  public List<Player> getPlayerList() {
    return null;
  }

  @Override
  public Target getTarget() {
    return null;
  }

  @Override
  public Room getRoomById(int id) {
    return null;
  }

  @Override
  public Player getPlayerByName(String playerName) {
    return null;
  }

  @Override
  public String randomattemptToKillTarget() {
    return String.valueOf(log.append("Attempt to kill by computer" + uniqueCode + "\n"));
  }

  @Override
  public String attemptToKillTarget(String itemName) {
    return String.valueOf(log.append("\nPlayer Information: attempt to kill target :" + itemName
        + "UniqueCode: " + uniqueCode + "\n"));
  }

  @Override
  public String roomInfo(String roomName) {
    return String
        .valueOf(log.append("\nRoomName - " + roomName + "UniqueCode: " + uniqueCode + "\n"));
  }

  @Override
  public String petMove(String roomName) {
    return String.valueOf(
        log.append("\nMoving pet to roomName - " + roomName + " UniqueCode: " + uniqueCode + "\n"));
  }

  @Override
  public Room getPetRoom(Pet pet) {
    return null;
  }

  @Override
  public void moveTarget(Target target) {

  }

  @Override
  public String getTargetRoom(Target target) {
    return null;
  }

  @Override
  public String getTargetRoomName() {
    return String.valueOf(log.append("Target " + "Unique code: " + uniqueCode));
  }

  @Override
  public int getTargetPosition(Target target) {
    return uniqueCode;
  }

  @Override
  public String getPetRoomNormal() {
    return String.valueOf(log.append("\nPet is in room: " + uniqueCode));
  }

  @Override
  public String playerInfo() {
    return String.valueOf(log.append("\nPlayer Details: " + uniqueCode));
  }

  @Override
  public boolean isTargetDead() {
    return false;
  }

  @Override
  public String computerPlayerstuff() {
    return String.valueOf("\nComputer Player Turn: " + uniqueCode);
  }

  @Override
  public List<Room> getRooms() {
    return null;
  }

  @Override
  public String roomCoordFromView(int row, int col) {
    return String.valueOf(log.append("\nRoom Coords from view Unique code: " + uniqueCode));
  }

  @Override
  public String getPlayerWhoseTurnItIs() {
    return String.valueOf(log.append("\nPlayer whose turn it is Unique code: " + uniqueCode));
  }

  @Override
  public List<String> getRoomNames() {
    return null;
  }

  @Override
  public List<String> getRoomItemsCurrentPlayer() {
    List<String> uniquecode = new ArrayList<String>();
    uniquecode.add("Revolver: " + String.valueOf(uniquecode));
    return uniquecode;
  }

  @Override
  public List<String> getPlayerItems() {
    List<String> uniquecode = new ArrayList<String>();
    uniquecode.add("Revolver: " + String.valueOf(uniquecode));
    return uniquecode;
  }

  @Override
  public boolean isGameover() {
    return false;
  }

  @Override
  public void resetMaxTurns() {
    log.append("\nInside reset max turns method: " + uniqueCode);
  }

  @Override
  public String messageMaxTurns() {
    return ("\nInside messageMaxTurns method: " + uniqueCode);
  }

  @Override
  public void resetGame(String filePath) {
    log.append("\nInside resetGame method: " + uniqueCode + " " + filePath);
  }

  @Override
  public Pet getPet() {
    return null;
  }
}
