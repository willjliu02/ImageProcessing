package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.swing.*;

import controller.ViewListener;

/**
 * A Graphical User Interface view for the ImageProcessor.
 */
public class GUIView extends JFrame implements ActionListener, IView {

  private Set<ViewListener> listeners;

  /**
   * Constructs a GUIView.
   */
  public GUIView() {
    this.listeners = new HashSet<ViewListener>();
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    this.setLayout(new BorderLayout());
  }

  @Override
  public void actionPerformed(ActionEvent e) {

  }

  @Override
  public void renderMessage(String message) throws IOException {

  }

  private void notifyListeners(ViewEvent e) {
    for (ViewListener listener: this.listeners) {
      listener.listenTo(e);
    }
  }

  @Override
  public void addListener(ViewListener listener) {
    this.listeners.add(listener);
  }
}
