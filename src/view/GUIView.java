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
public class GUIView extends JFrame implements IGUIView {

  private Set<ViewListener> listeners;
  private ButtonPanel bP;
  private ImageInfoPanel iIP;

  private MessagePanel mP;
  //private final String displayedImage;

  /**
   * Constructs a GUIView.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processor!");
    this.setSize( 1200, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(0,0);
    this.setResizable(false);

    this.listeners = new HashSet<ViewListener>();
    bP = new ButtonPanel(this);
    iIP = new ImageInfoPanel(this);
    mP = new MessagePanel(this);
    //needs this?

    this.setLayout(new BorderLayout());
    this.add(bP, BorderLayout.SOUTH);
    this.add(mP, BorderLayout.NORTH);
    this.add(iIP, BorderLayout.CENTER);

    this.setVisible(true);
    // own method??
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
    //return resetTextField(this.loadPath);
    return null;
  }

  @Override
  public String getSavePath() {
   //return resetTextField(this.savePath);
    return null;
  }

  @Override
  public String getBrightenAmt() {
    //return resetTextField(this.brightenAmt);
    return null;
  }

  @Override
  public String getDisplayedImage() {
    //return this.displayedImage;
    return null;
  }
}
