package doctorlucky.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;


/**
 * The PlayPanel class represents the panel where the player is able to play the game
 * by add the player and its details.
 */
public class PlayPanel extends JPanel {

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    this.setBackground(Color.orange);
    Border border = new LineBorder(Color.WHITE, 5, true);
    this.setBorder(border);
    this.setPreferredSize(new Dimension(500, 1000));
    g2.setColor(Color.white);

  }
}
