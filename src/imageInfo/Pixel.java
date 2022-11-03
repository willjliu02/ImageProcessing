package imageInfo;

public class Pixel implements IPixel {

  private final int r;
  private final int g;
  private final int b;

  private final int value;

  private final int luma;

  private final int intensity;

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

    this.value = this.getMax(this.r, this.g, this.b);
    this.intensity = (r + g + b) / 3;
    this.luma = (int) (0.2126 * r + 0.7152 * g + 0.0722 * g);
  }

  /**
   * Returns the max value between the r, g, b values provided.
   * @param r red value
   * @param g green value
   * @param b blue value
   * @return max value
   */
  protected int getMax(int r, int g, int b) {
    int max = r;
    if(g > max) {
      max = g;
    }
    else if(b > max) {
      max = b;
    }
    return max;
  }
  @Override
  public int getValue() {
    return this.value;
  }

  @Override
  public int getIntensity() {
    return this.intensity;
  }

  @Override
  public int getLuma() {
    return this.luma;
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
