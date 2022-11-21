package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Represents a command to flip the desired image across the axis.
 */
public abstract class FlipImage implements ImageCommand {

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

  protected abstract int getFlippedRow(int row, int height);

  protected abstract int getFlippedCol(int col, int width);

  @Override
  public abstract String toString();
}
