package view;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ButtonPanel extends JPanel {

  private JTextArea gameInfo;
  private JButton load, save, focus, brighten, greyscale, sepia, blur, sharpen, horizFlip, vertFlip;

  private JPanel optionPanel;
  private JTextField component, number;
  private Color purple;

  public ButtonPanel(ActionListener view) {
    this.setPreferredSize(new Dimension(1200, 80));
    this.setLayout(new GridLayout(1, 8));
    purple = new Color(245, 225, 253);
    this.setBackground(purple);

    gameInfo = new JTextArea("Set game instructions");
    gameInfo.setEditable(false);
    this.add(gameInfo);

    JPanel loadPanel = new JPanel();
    loadPanel.setBackground(purple);
    load = new JButton("load");
    loadPanel.add(load);
    this.add(loadPanel);
    load.addActionListener(view);
    load.setActionCommand("load");

    JPanel savePanel = new JPanel();
    savePanel.setBackground(purple);
    save = new JButton("save");
    savePanel.add(save);
    this.add(savePanel);
    save.addActionListener(view);
    save.setActionCommand("save");

    JPanel optionPanel = new JPanel();
    optionPanel.setBackground(purple);
    component = new JTextField(5);
    optionPanel.add(component);
    focus = new JButton("focus");
    optionPanel.add(focus);
    this.add(optionPanel);
    focus.addActionListener(view);
    focus.setActionCommand("focus");

    JPanel brightenPanel = new JPanel();
    brightenPanel.setBackground(purple);
    number = new JTextField(5);
    brightenPanel.add(number);
    brighten = new JButton("brighten");
    brightenPanel.add(brighten);
    this.add(brightenPanel);
    brighten.addActionListener(view);
    brighten.setActionCommand("brighten");

    JPanel col1 = new JPanel();
    col1.setBackground(purple);
    greyscale = new JButton("greyscale");
    col1.add(greyscale);
    sepia = new JButton("sepia");
    col1.add(sepia);
    this.add(col1);
    greyscale.addActionListener(view);
    greyscale.setActionCommand("greyscale");
    sepia.addActionListener(view);
    sepia.setActionCommand("sepia");

    JPanel col2 = new JPanel();
    col2.setBackground(purple);
    blur = new JButton("blur");
    col2.add(blur);
    sharpen = new JButton("sharpen");
    col2.add(sharpen);
    this.add(col2);
    blur.addActionListener(view);
    blur.setActionCommand("blur");
    sharpen.addActionListener(view);
    sharpen.setActionCommand("sharpen");

    JPanel col3 = new JPanel();
    col3.setBackground(purple);
    horizFlip = new JButton("horizontal flip");
    col3.add(horizFlip);
    vertFlip = new JButton("vertical flip");
    col3.add(vertFlip);
    this.add(col3);
    horizFlip.addActionListener(view);
    horizFlip.setActionCommand("horizontal-flip");
    vertFlip.addActionListener(view);
    vertFlip.setActionCommand("vertical-flip");
  }

  public String getComponent() {
    return this.component.getText();
  }

  public String getBrightAmt() {
    return this.number.getText();
  }
}
