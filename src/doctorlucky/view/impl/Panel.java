package doctorlucky.view.impl;

import doctorlucky.model.impl.Player;
import doctorlucky.model.impl.Room;
import doctorlucky.view.ReadOnlyModel;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
/**
 * The Panel class represents the board image of the game along with the room
 * names, player names and target drawn over it.
 */

public class Panel extends JPanel {

  private final ReadOnlyModel model;

  /**
   * Constructs the panel object for the view.
   * 
   * @param model the read-only model used by the view to get relevant information
   *              from the user.
   */
  public Panel(ReadOnlyModel model) {
    this.model = model;
  }

  // Helper method to draw mansion image
  private void createWorldImage(Graphics g) {
    Graphics2D graph = (Graphics2D) g;
    this.setBackground(Color.white);
    for (Room room : model.getRooms()) {
      String roomName = room.getRoomName();
      int leftX = room.getTopLeft().getPointRow();
      int leftY = room.getTopLeft().getPointColumn();
      int rightX = room.getBottomRight().getPointRow();
      int rightY = room.getBottomRight().getPointColumn();
      int height = (rightY + 1 - leftY) * 25;
      int width = (rightX + 1 - leftX) * 25;
      int topLeftX = leftX * 25;
      int topLeftY = leftY * 25;
      g.setColor(Color.blue);
      ((Graphics2D) g).setStroke(new BasicStroke(2));
      Rectangle2D.Double rect = new Rectangle2D.Double(topLeftY, topLeftX, height, width);
      graph.draw(rect);
      g.setColor(Color.red);
      graph.drawString(roomName, topLeftY + 15, topLeftX + 15);

    }

    graph.dispose();
  }

  // Paint component for Panel
  @Override
  public void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    Border border = new LineBorder(Color.CYAN, 5, true);
    this.setBorder(border);
    this.setPreferredSize(new Dimension(1000, 1000));

    if (model.getPlayerList().size() != 0) {
      g2.setColor(Color.BLACK);
      String currPlayerName = model.getPlayerList().get(model.getPlayerList().size() - 1)
          .getPlayerName();
      if (!model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName))
          .equals(model.getPetRoomNormal())) {
        int leftX = model
            .getRoomByName(model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName)))
            .getTopLeft().getPointRow();
        int leftY = model
            .getRoomByName(model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName)))
            .getTopLeft().getPointColumn();

        leftX = leftX * 25 + 20;
        leftY = leftY * 25 + 20;
        g2.drawString(currPlayerName, leftY + 25, leftX + 25);
      }
    }

    String targetName = "TARGET";
    Room targetRoomName = model.getRoomByName(model.getTargetRoomName());

    if (!model.getTargetRoomName().equals(model.getPetRoomNormal())) {
      int leftX = targetRoomName.getTopLeft().getPointRow();
      int leftY = targetRoomName.getTopLeft().getPointColumn();
      leftX = leftX * 25 + 20;
      leftY = leftY * 25 + 20;
      g2.drawString(targetName, leftY + 35, leftX + 28);
    }

    if (model.getPlayerList().size() > 0) {
      for (Player player : model.getPlayerList()) {
        g2.setColor(Color.BLACK);
        String currPlayerName = player.getPlayerName();
        if (!model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName))
            .equals(model.getPetRoomNormal())) {
          int leftX = model
              .getRoomByName(model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName)))
              .getTopLeft().getPointRow();
          int leftY = model
              .getRoomByName(model.getPlayerCurrentRoom(model.getPlayerByName(currPlayerName)))
              .getTopLeft().getPointColumn();

          leftX = leftX * 25 + 20;
          leftY = leftY * 25 + 20;
          g2.drawString(currPlayerName, leftY + 25, leftX + 25);
        }
      }

    }

    createWorldImage(g2);

  }

}
