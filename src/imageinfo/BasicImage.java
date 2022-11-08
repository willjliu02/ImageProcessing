package imageinfo;

import model.ImageCommand;

/**
 * Represents an image with basic information.
 */
public class BasicImage implements IImage {
  private final IPixel[][] pixels;
  private final int width;
  private final int height;

  private final int maxVal;

  /**
   * Creates a basic image based on a provided image.
   *
   * @param image another IImage that will be converted to a BasicImage.
   */
  public BasicImage(IImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxVal = image.getMaxValue();

    IPixel[][] otherPixels = image.getPixels();
    this.pixels = image.getPixels();

//    this.pixels = new IPixel[height][width];
//    for (int r = 0; r < height; r++) {
//      if (width >= 0) {
//        System.arraycopy(otherPixels[r], 0, this.pixels[r], 0, width);
//      }
//    }
  }

  /**
   * Creates a basic image with provided image information.
   *
   * @param width    width of the image
   * @param height   height of the image
   * @param maxValue max value seen in the image
   * @param pixels   pixels that make up the image
   */
  public BasicImage(int width, int height, int maxValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxVal = maxValue;

//    this.pixels = new IPixel[height][width];
//    for (int r = 0; r < height; r++) {
//      System.arraycopy(pixels[r], 0, this.pixels[r], 0, width);
//    }
    this.pixels = pixels;
  }

  @Override
  public IImage acceptCommand(ImageCommand command) {
    return command.apply(this);
  }

  @Override
  public IPixel[][] getPixels() {
    return this.pixels;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxValue() {
    return this.maxVal;
  }
}
