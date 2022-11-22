package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MessagePanel extends JPanel {
  private ActionListener view;
  private Color purple;

  private JLabel update;

  public MessagePanel(ActionListener view) {
    this.view = view;
    this.setPreferredSize(new Dimension(1200, 50));
    purple = new Color(245, 225, 253);
    this.setBackground(purple);

    update = new JLabel("Image updates will show here!");
    this.add(update);
  }

  public void refreshMessage(String message) {
    update.setText(message);
    this.repaint();
  }
}
