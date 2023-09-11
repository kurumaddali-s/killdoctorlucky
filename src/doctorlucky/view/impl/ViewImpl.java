package doctorlucky.view.impl;

import doctorlucky.control.Features;
import doctorlucky.view.ReadOnlyModel;
import doctorlucky.view.View;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

/**
 * Represents a view interacting with controller and the read only model. It
 * shows gui for a the game to screen. It also responds to user input for
 * example click and key board input for move, pick up item, kill target amd
 * moving the pet. Once action is completed, view is refreshed to reflect
 * correct values.
 */
public class ViewImpl extends JFrame implements View {

  private JFrame fileOpenerPanel;
  private Panel panel;
  private TurnPanel turnPanel;
  private JPanel mainPanel;
  private WelcomePanel welcomePanel;
  private PlayPanel playPanel;
  private JMenuItem newGame;
  private JMenuItem currentGame;
  private JMenuItem quitGame;
  private JButton addComputerPlayerButton;
  private JButton addPlayerButton;
  private JButton submitPlayerInfoButton;
  private JButton playGameButton;
  private JButton startGameButton;
  private JButton openFileButton;
  private JButton submitNewGame;
  private JLabel headerLabel;
  private JLabel jPlayername;
  private JLabel jPlayerroom;
  private JLabel jPlayerlimit;
  private JLabel jPlayeradded;
  private JLabel jTurnscommands;
  private JLabel jCredits;
  private JTextField playerNametF;
  private JTextField playerLimittF;
  private JDialog playerAddedBox;
  private JScrollPane scrollBar;
  private List<String> roomsList;
  private JComboBox<String> roomsComboBox;
  private int count;
  private ReadOnlyModel model;
  private JFileChooser fileChooser;

  /**
   * Constructs a view object to represent board in the screen. The board
   * represents the board image, player details, instruction to run the game.
   * 
   * @param model the read-only model used by the view to get relevant information
   *              from the user.
   */
  public ViewImpl(ReadOnlyModel model) {
    this.model = model;
    setPreferredSize(new Dimension(1500, 1200));
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setTitle("Kill Doctor Lucky");

    // Declaring panels
    mainPanel = new JPanel(new GridLayout());
    welcomePanel = new WelcomePanel();
    panel = new Panel(model);
    playPanel = new PlayPanel();
    turnPanel = new TurnPanel();
    fileOpenerPanel = new JFrame();

    // Setting scrollbar
    scrollBar = new JScrollPane(panel);

    // Adding welcome and main panel
    mainPanel.add(welcomePanel);
    this.add(mainPanel);
    welcomePanel.setVisible(true);

    // setting visibility
    panel.setVisible(false);
    turnPanel.setVisible(false);
    playPanel.setVisible(false);
    mainPanel.setVisible(true);

    // Setting layout
    GridBagConstraints c = new GridBagConstraints();
    welcomePanel.setLayout(new GridBagLayout());
    welcomePanel.setBackground(Color.orange);
    playPanel.setLayout(new GridBagLayout());

    // Welcome panel components
    ImageIcon image = new ImageIcon("res/bgImg.jpeg");
    JLabel imageLabel = new JLabel();
    imageLabel.setIcon(image);
    c.gridx = 1;
    c.anchor = GridBagConstraints.CENTER;
    c.gridy = 1;
    c.weightx = 10.0;
    c.weighty = 10.0;
    c.insets = new Insets(10, 30, 10, 70);
    welcomePanel.add(imageLabel, c);

    headerLabel = new JLabel("<html><u><b>WELCOME TO KILL DOCTOR LUCKY GAME<b></u></html>");
    headerLabel.setFont(new Font("Lucida Grande", Font.BOLD, 18));
    c.gridx = 1;
    c.anchor = GridBagConstraints.PAGE_START;
    c.gridy = 0;
    c.insets = new Insets(10, 35, 10, 10);
    welcomePanel.add(headerLabel, c);

    startGameButton = new JButton("Click here to start the game");
    c.gridx = 2;
    c.anchor = GridBagConstraints.NORTHWEST;
    c.gridy = 1;
    welcomePanel.add(startGameButton, c);

    jCredits = new JLabel("<html>Anushka Jain<br>Sushmitha Kurumaddali</html>");
    jCredits.setFont(new Font("Lucida Grande", Font.BOLD, 15));
    c.gridx = 2;
    c.anchor = GridBagConstraints.NORTHWEST;
    c.gridy = 2;
    c.insets = new Insets(5, 5, 50, 10);
    welcomePanel.add(jCredits, c);

    // Declaring settings menu
    JMenuBar menu = new JMenuBar();
    setJMenuBar(menu);
    JMenu settingsMenu = new JMenu("Settings");
    menu.add(settingsMenu);
    newGame = new JMenuItem("New Game");
    settingsMenu.add(newGame);
    quitGame = new JMenuItem("QUIT");
    settingsMenu.add(quitGame);

    // File chooser components
    openFileButton = new JButton("OPEN");
    submitNewGame = new JButton("SUBMIT");
    fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

    // Positioning Menu
    c.gridx = 0;
    c.gridy = 0;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(menu, c);

    // Rooms list combo box
    roomsList = model.getRoomNames();
    roomsComboBox = new JComboBox<>();
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(roomsComboBox, c);
    roomsComboBox.setVisible(false);

    // Play panel components
    addComputerPlayerButton = new JButton("ADD COMPUTER PLAYER");
    c.gridx = 2;
    c.gridy = 0;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(addComputerPlayerButton, c);

    addPlayerButton = new JButton("ADD PLAYER");
    c.gridx = 1;
    c.gridy = 0;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(addPlayerButton, c);

    submitPlayerInfoButton = new JButton("SUBMIT");
    c.gridx = 1;
    c.gridy = 3;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.CENTER;
    playPanel.add(submitPlayerInfoButton, c);
    submitPlayerInfoButton.setVisible(false);

    playGameButton = new JButton("PLAY");
    c.gridx = 1;
    c.gridy = 4;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.CENTER;
    playPanel.add(playGameButton, c);
    playGameButton.setVisible(false);

    jPlayerlimit = new JLabel("Enter Player Item Limit");
    c.gridx = 0;
    c.gridy = 1;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.gridwidth = 3;
    c.insets = new Insets(20, 30, 5, 20);
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(jPlayerlimit, c);
    jPlayerlimit.setVisible(false);

    playerLimittF = new JTextField(10);
    c.gridx = 1;
    c.gridy = 1;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.insets = new Insets(20, 5, 5, 70);
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(playerLimittF, c);
    playerLimittF.setVisible(false);

    jPlayername = new JLabel("Enter Player Name");
    c.gridx = 0;
    c.gridy = 2;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.insets = new Insets(5, 5, 0, 70);
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(jPlayername, c);
    jPlayername.setVisible(false);

    playerNametF = new JTextField(10);
    c.gridx = 1;
    c.gridy = 2;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.insets = new Insets(5, 5, 0, 70);
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(playerNametF, c);
    playerNametF.setVisible(false);

    jPlayerroom = new JLabel("Enter Room Name");
    c.gridx = 0;
    c.gridy = 3;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    playPanel.add(jPlayerroom, c);
    jPlayerroom.setVisible(false);

    playerAddedBox = new JDialog();
    playerAddedBox.setSize(200, 100);
    playerAddedBox.setLocationRelativeTo(null);
    jPlayeradded = new JLabel("", SwingConstants.CENTER);
    playerAddedBox.setVisible(false);
    jPlayeradded.setVisible(false);

    this.pack();
    this.setVisible(true);
  }

  @Override
  public void setFeatures(Features f) {

    startGameButton.addActionListener(l -> {
      mainPanel.remove(welcomePanel);
      mainPanel.add(scrollBar);
      mainPanel.add(playPanel);
      panel.setVisible(true);
      playPanel.setVisible(true);
      resetFocus();
    });

    addPlayerButton.addActionListener(l -> setOutput());

    addComputerPlayerButton.addActionListener(l -> f.addComputerPlayer());

    submitPlayerInfoButton.addActionListener(l -> {
      f.addPlayerInfo(playerLimittF.getText(), playerNametF.getText(),
          (String) roomsComboBox.getSelectedItem());
      playerNametF.setText("");
      playerLimittF.setText("");
      resetFocus();
    });

    playGameButton.addActionListener(l -> {
      f.getPlayerInfo();
      playTurn();
      resetFocus();
    });

    panel.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        f.movePlayerToNewRoom(e.getX(), e.getY());
        f.getPlayerInfo();
        resetFocus();
      }
    });

    addKeyBinding(turnPanel, KeyEvent.VK_L, "lookaround", (evt) -> {
      f.lookAround();
      f.getPlayerInfo();
      resetFocus();
    });

    addKeyBinding(turnPanel, KeyEvent.VK_P, "pick", (evt) -> {
      f.pickItem();
      f.getPlayerInfo();
      resetFocus();
    });

    addKeyBinding(turnPanel, KeyEvent.VK_M, "movepet", (evt) -> {
      f.movePet();
      f.getPlayerInfo();
      resetFocus();

    });

    addKeyBinding(turnPanel, KeyEvent.VK_K, "kill", (evt) -> {
      f.killTarget();
      f.getPlayerInfo();
      resetFocus();

    });

    openFileButton.addActionListener(e -> {
      fileOpenerPanel.setVisible(false);
      fileChooser.showSaveDialog(null);
    });

    submitNewGame.addActionListener(e -> {
      if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        f.reuseGame(filePath);
        showMessage("Selected file added to the game");
      }
    });

    newGame.addActionListener(e -> {
      JPanel filePanel = new JPanel();
      fileOpenerPanel.setSize(500, 300);
      filePanel.add(openFileButton);
      filePanel.add(submitNewGame);
      fileOpenerPanel.add(filePanel);
      fileOpenerPanel.setVisible(true);
      playPanel.add(fileOpenerPanel);
    });

    quitGame.addActionListener(l -> f.exitProgram());
  }

  @Override
  public void setOutput() {
    jPlayerlimit.setVisible(true);
    playerLimittF.setVisible(true);
    jPlayername.setVisible(true);
    playerNametF.setVisible(true);
    jPlayerroom.setVisible(true);
    roomsComboBox.setVisible(true);

    count = 0;
    for (int i = 0; i < roomsList.size(); i++) {
      roomsComboBox.addItem(roomsList.get(count++));
    }
    submitPlayerInfoButton.setVisible(true);
    playGameButton.setVisible(true);
    resetFocus();
  }

  @Override
  public void playTurn() {
    mainPanel.remove(playPanel);
    mainPanel.add(turnPanel);
    turnPanel.setBackground(Color.orange);
    this.add(mainPanel);
    turnPanel.setVisible(true);

    turnPanel.setLayout(new GridBagLayout());
    GridBagConstraints c = new GridBagConstraints();

    jTurnscommands = new JLabel("");
    c.gridx = 2;
    c.gridy = 2;
    c.weightx = 0.5;
    c.weighty = 0.5;
    c.anchor = GridBagConstraints.NORTHWEST;
    c.insets = new Insets(50, 50, 10, 10);

    jTurnscommands.setText(
        "<html><h><center>INSTRUCTIONS</center></h><br>1. Click on the room to "
            + "move the player to new room."
            + "<br>2. Enter p to pick up weapon.<br>3. Enter l to look around the room<br>"
            + "4. Enter k to kill target.<br>5. Enter m to move the pet.<br></html>");
    jTurnscommands.setFont(new Font("Calibri", Font.PLAIN, 14));
    turnPanel.add(jTurnscommands, c);
    jTurnscommands.setVisible(true);
  }

  @Override
  public void showMessage(String message) {
    if (message == null) {
      throw new IllegalArgumentException("Not valid message");
    }
    JOptionPane.showMessageDialog(panel, message, "Confirmation", 1);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  private void addKeyBinding(JComponent comp, int keyCode, String id,
      ActionListener actionListener) {
    InputMap im = turnPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
    im.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);

    ActionMap ap = turnPanel.getActionMap();
    ap.put(id, new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        actionListener.actionPerformed(e);
      }
    });
  }

  @Override
  public String showCommands(List<String> itemsList) {
    if (itemsList == null) {
      throw new IllegalArgumentException("Cannot be null");
    }
    String[] items = itemsList.toArray(new String[0]);
    String response = (String) JOptionPane.showInputDialog(panel, "Select", "Turn",
        JOptionPane.QUESTION_MESSAGE, null, items, items[0]);
    return response;
  }

  @Override
  public void refresh() {
    this.repaint();
  }

}
