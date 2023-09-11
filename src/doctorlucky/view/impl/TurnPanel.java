package doctorlucky.view.impl;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 * The TurnPanel class is used to represent the layout of the panel where the
 * player is able to play the game through instructions.
 */
public class TurnPanel extends JPanel {

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    Border border = new LineBorder(Color.WHITE, 5, true);
    this.setBorder(border);
    this.setPreferredSize(new Dimension(600, 1500));
    g2.setColor(Color.white);

  }
}
