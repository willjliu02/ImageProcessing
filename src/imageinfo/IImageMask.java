package imageinfo;

/**
 * A black and white mask (black = modifiable, white is unmodifiable).
 */
public interface IImageMask {
  /**
   * Gets the pixel at the coordinates (c,r).
   * @param c the column of the pixel
   * @param r the row of the pixel
   * @return the pixel at that location
   */
  boolean isModifiable(int c, int r);
}
