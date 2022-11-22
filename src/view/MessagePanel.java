package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class MessagePanel extends JPanel {
  private ActionListener view;
  private Color purple;

  public MessagePanel(ActionListener view) {
    this.view = view;
    this.setPreferredSize(new Dimension(1200, 50));
    purple = new Color(245, 225, 253);
    this.setBackground(purple);
  }
}
