package controller;

import view.ViewEvent;

/**
 * Represents a Listener to View Events
 */
public interface ViewListener {
  void listenTo(ViewEvent e);
}
