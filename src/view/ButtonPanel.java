package view;

import java.awt.*;

import javax.swing.*;

public class ButtonPanel extends JPanel {

  private JTextArea gameInfo;
  private JButton load;
  private JButton save;

  private JPanel optionPanel;
  private JTextField component;
  private JButton focus;
  private JTextField number;
  private JButton brighten;
  private JButton greyscale;
  private JButton sepia;
  private JButton blur;
  private JButton sharpen;
  private JButton horizFlip;
  private JButton vertFlip;


  private IGUIView view;

  private Color purple;

  public ButtonPanel(IGUIView view) {
    this.view = view;
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

    JPanel savePanel = new JPanel();
    savePanel.setBackground(purple);
    save = new JButton("save");
    savePanel.add(save);
    this.add(savePanel);

    JPanel optionPanel = new JPanel();
    optionPanel.setBackground(purple);
    component = new JTextField(5);
    optionPanel.add(component);
    focus = new JButton("focus");
    optionPanel.add(focus);
    this.add(optionPanel);

    JPanel brightenPanel = new JPanel();
    brightenPanel.setBackground(purple);
    number = new JTextField(5);
    brightenPanel.add(number);
    brighten = new JButton("brighten");
    brightenPanel.add(brighten);
    this.add(brightenPanel);

    JPanel col1 = new JPanel();
    col1.setBackground(purple);
    greyscale = new JButton("greyscale");
    col1.add(greyscale);
    sepia = new JButton("sepia");
    col1.add(sepia);
    this.add(col1);

    JPanel col2 = new JPanel();
    col2.setBackground(purple);
    blur = new JButton("blur");
    col2.add(blur);
    sharpen = new JButton("sharpen");
    col2.add(sharpen);
    this.add(col2);

    JPanel col3 = new JPanel();
    col3.setBackground(purple);
    horizFlip = new JButton("horizontal flip");
    col3.add(horizFlip);
    vertFlip = new JButton("vertical flip");
    col3.add(vertFlip);
    this.add(col3);
  }
}
