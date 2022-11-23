package view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;

/**
 * Displays messages that update as the user interacts with the processor.
 */
public class MessagePanel extends JPanel {

  private final JLabel update;

  /**
   * Creates the message panel.
   */
  public MessagePanel() {
    this.setPreferredSize(new Dimension(1200, 50));
    Color purple = new Color(245, 225, 253);
    this.setBackground(purple);

    update = new JLabel("Image updates will show here!");
    this.add(update);
  }

  /**
   * Refreshes the screen with the given message.
   *
   * @param message message to update
   */
  public void refreshMessage(String message) {
    update.setText(message);
    this.repaint();
  }
}
