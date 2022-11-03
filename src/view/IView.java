package view;

import java.io.IOException;

/**
 * Represents a view of the controller.
 */
public interface IView {
  /**
   * Renders a message to the view.
   * @param message the message to be written to the view
   * @throws IOException thrown if unable to render the message
   */
  public void renderMessage(String message) throws IOException;
}
