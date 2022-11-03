package imageInfo;

import model.ImageCommand;

/**
 * Represents an image with basic information.
 */
public class BasicImage implements IImage {
  private IPixel[][] pixels;
  private int width;
  private int height;

  private int maxVal;

  /**
   * Creates a basic image based on a provided image
   * @param image
   */
  public BasicImage(IImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxVal = image.getMaxValue();

    IPixel[][] otherPixels = image.getPixels();
    this.pixels = new IPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        this.pixels[r][c] = otherPixels[r][c];
      }
    }
  }

  /**
   * Creates a basic image with provided image information
   * @param width width of the image
   * @param height height of the image
   * @param maxValue max value seen in the image
   * @param pixels pixels that make up the image
   */
  public BasicImage(int width, int height, int maxValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxVal = maxValue;

    this.pixels = new IPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        this.pixels[r][c] = pixels[r][c];
      }
    }
  }

  @Override
  public IPixel setPixel(int row, int col, IPixel newPixel) {
    return this.pixels[row][col] = newPixel;
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
