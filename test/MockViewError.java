
import doctorlucky.control.Features;
import doctorlucky.view.View;
import java.util.List;

/**
 * A JUNIT class to test MockViewError class.
 */
public class MockViewError implements View {

  private StringBuilder log;
  private int uniqueCode;

  /**
     * Constructs the mock model object.
     * @param log string to log the values
     * @param uniqueCode uniquecode
     */
  public MockViewError(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setFeatures(Features f) {
    log.append("Invalid Features unique code: \n" + uniqueCode);
  }

  @Override
  public void setOutput() {
    log.append(" Invalid Set output unique code: \n" + uniqueCode);
  }

  @Override
  public void playTurn() {
    log.append(" Wrong player Turn called unique code \n" + uniqueCode);
  }

  @Override
  public void showMessage(String message) {
    log.append(" Invalid Show Message called unique code \n" + uniqueCode);
  }

  @Override
  public void makeVisible() {
    log.append(" Invalid Make visible called unique code \n" + uniqueCode);
  }

  @Override
  public void resetFocus() {
    log.append(" Invalid Reset Focus called unique code \n" + uniqueCode);
  }

  @Override
  public String showCommands(List<String> roomItems) {
    return String
        .valueOf(log.append(" Invalid Show commands " + "called unique code \n" + uniqueCode));
  }

  @Override
  public void refresh() {
    log.append(" Invalid Refresh method called unique code \n" + uniqueCode);
  }
}