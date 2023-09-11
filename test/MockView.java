
import doctorlucky.control.Features;
import doctorlucky.view.View;
import java.util.List;

/**
 * A MockView class mocks the ViewImpl class, it implements View interface which
 * contains all the methods necessary to run the KillDoctorLucky Game.
 */
public class MockView implements View {

  private StringBuilder log;
  private int uniqueCode;

  /**
     * Constructs the mock view object.
     * @param log string to log the values
     * @param uniqueCode uniquecode
     */
  public MockView(StringBuilder log, int uniqueCode) {
    this.log = log;
    this.uniqueCode = uniqueCode;
  }

  @Override
  public void setFeatures(Features f) {
    log.append("Features called unique code: \n" + uniqueCode);
  }

  @Override
  public void setOutput() {
    log.append("Set output called unique code: \n" + uniqueCode);
  }

  @Override
  public void playTurn() {
    log.append("Player Turn called unique code \n" + uniqueCode);
  }

  @Override
  public void showMessage(String message) {
    log.append("Show Message called unique code \n" + uniqueCode);
  }

  @Override
  public void makeVisible() {
    log.append("Make visible called unique code \n" + uniqueCode);
  }

  @Override
  public void resetFocus() {
    log.append("Reset Focus called unique code \n" + uniqueCode);
  }

  @Override
  public String showCommands(List<String> roomItems) {
    return String.valueOf(log.append("Show commands called unique code \n" + uniqueCode));
  }

  @Override
  public void refresh() {
    log.append(" Refresh method called unique code \n" + uniqueCode);
  }
}
