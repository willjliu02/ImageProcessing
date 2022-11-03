package imageInfo;

/**
 * Represents an immutable Image
 */
public interface IImageState {
  /**
   * Gets the list of Pixels holding the data of the images.
   *
   * @return IPixel[][] containing the Pixels of the loaded image
   */
  IPixel[][] getPixels();

  /**
   * Gets the width of the image.
   *
   * @return the width
   */
  int getWidth();

  /**
   * Gets the height of the image.
   *
   * @return the height
   */
  int getHeight();

  /**
   * Gets the maximum value of the image.
   *
   * @return the width
   */
  int getMaxValue();
}
