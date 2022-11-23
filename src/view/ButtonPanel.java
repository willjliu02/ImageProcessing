package view;

import java.awt.event.ActionListener;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * Represents the part of the screen with all the components the user can interact with to edit the
 * image
 */
public class ButtonPanel extends JPanel {

  //private JTextArea gameInfo;
  //private JButton load, save, focus, brighten, greyscale, sepia, blur, sharpen, horizFlip, vertFlip;

  private final JTextField component;
  private final JTextField number;
  //private Color purple;

  /**
   * Creates the Button Panel.
   *
   * @param view to add listener
   */
  public ButtonPanel(ActionListener view) {
    this.setPreferredSize(new Dimension(1200, 80));
    this.setLayout(new GridLayout(1, 8));
    Color purple = new Color(245, 225, 253);
    this.setBackground(purple);

    JPanel infoPanel = new JPanel();
    infoPanel.setBackground(purple);
    JLabel gameInfo = new JLabel("  Click for instructions!");
    infoPanel.add(gameInfo);
    JButton instructions = new JButton("instructions");
    instructions.addActionListener(view);
    infoPanel.add(instructions);
    this.add(infoPanel);

    JPanel loadPanel = new JPanel();
    loadPanel.setBackground(purple);
    JButton load = new JButton("load");
    loadPanel.add(load);
    this.add(loadPanel);
    load.addActionListener(view);
    load.setActionCommand("load");

    JPanel savePanel = new JPanel();
    savePanel.setBackground(purple);
    JButton save = new JButton("save");
    savePanel.add(save);
    this.add(savePanel);
    save.addActionListener(view);
    save.setActionCommand("save");

    JPanel optionPanel = new JPanel();
    optionPanel.setBackground(purple);
    component = new JTextField(5);
    optionPanel.add(component);
    JButton focus = new JButton("focus");
    optionPanel.add(focus);
    this.add(optionPanel);
    focus.addActionListener(view);
    focus.setActionCommand("focus");

    JPanel brightenPanel = new JPanel();
    brightenPanel.setBackground(purple);
    number = new JTextField(5);
    brightenPanel.add(number);
    JButton brighten = new JButton("brighten");
    brightenPanel.add(brighten);
    this.add(brightenPanel);
    brighten.addActionListener(view);
    brighten.setActionCommand("brighten");

    JPanel col1 = new JPanel();
    col1.setBackground(purple);
    JButton greyscale = new JButton("greyscale");
    col1.add(greyscale);
    JButton sepia = new JButton("sepia");
    col1.add(sepia);
    this.add(col1);
    greyscale.addActionListener(view);
    greyscale.setActionCommand("greyscale");
    sepia.addActionListener(view);
    sepia.setActionCommand("sepia");

    JPanel col2 = new JPanel();
    col2.setBackground(purple);
    JButton blur = new JButton("blur");
    col2.add(blur);
    JButton sharpen = new JButton("sharpen");
    col2.add(sharpen);
    this.add(col2);
    blur.addActionListener(view);
    blur.setActionCommand("blur");
    sharpen.addActionListener(view);
    sharpen.setActionCommand("sharpen");

    JPanel col3 = new JPanel();
    col3.setBackground(purple);
    JButton horizFlip = new JButton("horizontal flip");
    col3.add(horizFlip);
    JButton vertFlip = new JButton("vertical flip");
    col3.add(vertFlip);
    this.add(col3);
    horizFlip.addActionListener(view);
    horizFlip.setActionCommand("horizontal-flip");
    vertFlip.addActionListener(view);
    vertFlip.setActionCommand("vertical-flip");
  }

  /**
   * Returns the component for the focus command.
   *
   * @return String of what component is being focused on
   */
  public String getComponent() {
    String text = this.component.getText();
    this.component.setText("");
    this.repaint();
    return text;
  }

  /**
   * Returns the brighten amount/
   *
   * @return amt to brighten by
   */
  public String getBrightAmt() {
    String amt = this.number.getText();
    this.number.setText("");
    this.repaint();
    return amt;
  }
}
