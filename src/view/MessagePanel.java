package view;

import java.awt.*;

import javax.swing.*;

public class MessagePanel extends JPanel {
  IGUIView view;
  Color purple;

  public MessagePanel(IGUIView view) {
    this.view = view;
    this.setPreferredSize(new Dimension(1200, 50));
    purple = new Color(245, 225, 253);
    this.setBackground(purple);
  }
}
