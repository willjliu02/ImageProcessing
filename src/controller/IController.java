package controller;

public interface IController {

  /**
   * Edits a given image based on different provided commands.
   *
   * @throws IllegalStateException if the controller cannot successfully
   *                               read input or transmit output.
   */
  void processImage() throws IllegalStateException;

}
