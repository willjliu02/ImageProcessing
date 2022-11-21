package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.*;

import controller.ViewListener;

/**
 * A Graphical User Interface view for the ImageProcessor.
 */
public class GUIView extends JFrame implements ActionListener, IGUIView {

  private final Set<ViewListener> listeners;
  private final String displayedImage;

  private final JTextField loadPath, savePath, brightenAmt;
  private final JList<String> focusList;

  /**
   * Constructs a GUIView.
   */
  public GUIView() {
    this.listeners = new HashSet<ViewListener>();
    this.displayedImage = "";

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    this.loadPath = new JTextField(40);
    this.savePath = new JTextField(40);
    this.brightenAmt = new JTextField(40);

    this.focusList = new JList<String>();
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }

  private void notifyListeners(ViewEvent e) {
    for (ViewListener listener : this.listeners) {
      listener.listenTo(e);
    }
  }

  @Override
  public void addListener(ViewListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  private String resetTextField(JTextField field) {
    String path = field.getText();
    field.setText("");
    return path;
  }

  @Override
  public String getLoadPath() {
    return resetTextField(this.loadPath);
  }

  @Override
  public String getSavePath() {
    return resetTextField(this.savePath);
  }

  @Override
  public String getBrightenAmt() {
    return resetTextField(this.brightenAmt);
  }

  @Override
  public String getDisplayedImage() {
    return this.displayedImage;
  }
}
