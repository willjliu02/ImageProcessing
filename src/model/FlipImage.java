package model;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.IPixel;
import imageInfo.Pixel;

/**
 * Represents a command to flip the desired image across the
 */
public class FlipImage implements ImageCommand {

  private final String flip;

  /**
   * Constructs a command to flip an IImage.
   *
   * @param flip the direction to flip it.
   */
  public FlipImage(String flip) {
    if (!flip.equalsIgnoreCase("horizontal-flip")
            && !flip.equalsIgnoreCase("vertical-flip")) {
      throw new IllegalArgumentException("This is not a valid flip option.");
    }

    this.flip = flip;
  }

  @Override
  public IImage apply(IImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    int maxVal = image.getMaxValue();
    IPixel[][] newPixels = new Pixel[height][width];
    IPixel[][] oldPixels = image.getPixels();
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        newPixels[r][c] = oldPixels[this.getFlippedRow(r, height)][this.getFlippedCol(c, width)];
      }
    }

    return new BasicImage(width, height, maxVal, newPixels);
  }

  private int getFlippedRow(int row, int height) {
    if (this.flip.equalsIgnoreCase("horizontal-flip")) {
      return row;
    } else {
      return height - 1 - row;
    }
  }

  private int getFlippedCol(int col, int width) {
    if (this.flip.equalsIgnoreCase("vertical-flip")) {
      return col;
    } else {
      return width - 1 - col;
    }
  }

  @Override
  public String toString() {
    return "Flip Image: " + this.flip;
  }
}
