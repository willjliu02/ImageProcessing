package imageInfo;

/**
 * Represents the information behind a Pixel.
 */
public interface IPixel {

  //value, luma, and intensity??

  /**
   * Get the maximum of the three components.
   * @return int the maximum of the three components
   */
  public int getValue();

  /**
   * Get the average of the three components.
   * @return double returns the average of the three components
   */
  public double getLuma();

  /**
   * Gets the weighted sum of the components.
   * @return double the weighted sum
   */
  public double getIntensity();

  /**
   * Get red value of a pixel.
   * @return red value
   */
  public int getR();

  /**
   * Get green value of a pixel.
   * @return green value
   */
  public int getG();

  /**
   * Get blue value of a pixel.
   * @return blue value
   */
  public int getB();
}
