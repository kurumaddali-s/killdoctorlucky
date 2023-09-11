package doctorlucky.view.impl;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


/**
 * The WelcomePanel class is used to represent the layout of the welcome page
 * of the game.
 */
public class WelcomePanel extends JPanel {


  /** WelcomePanel constructor consists of ReadonlyTttModel interface object.
 * It is used to create the layout for starting welcome page.
 * */

  @Override
public void paintComponent(Graphics g) {
    super.paintComponent(g);
    setPreferredSize(new Dimension(1500, 1200));
    setLocation(0, 0);

  }
}
