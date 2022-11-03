package imageInfo;

import model.ImageCommand;

/**
 * Represents an image.
 */
public interface IImage extends IImageState {
  /**
   * Replaces the pixel at row and col with the new Pixel.
   * @param row the to be switched
   * @param col the column to be switched
   * @param newPixel the new pixel to be switched to
   * @return the old pixel that used to be there
   */
  public IPixel setPixel(int row, int col, IPixel newPixel);

  /**
   * Accepts the application of an action or command on the image.
   * @param command the command that will be applied onto it
   */
  public IImage acceptCommand(ImageCommand command);
}
