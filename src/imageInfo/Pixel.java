package imageInfo;

public class Pixel implements IPixel {

  private final int r;
  private final int g;
  private final int b;

  //value, luma, and intensity??

  /**
   * Assigns red, green, and blue values to a pixel.
   * @param r red value to assign
   * @param g green value to assign
   * @param b blue value to assign
   */
  public Pixel(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  @Override
  public int getR() {
    return this.r;
  }

  @Override
  public int getG() {
    return this.g;
  }

  @Override
  public int getB() {
    return this.b;
  }
}
