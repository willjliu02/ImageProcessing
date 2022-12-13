package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Process for brightening an image.
 */
public class Brighten implements ImageCommand {

  private final int increment;

  /**
   * Constructs a command to brighten an image.
   *
   * @param increment to brighten the image by
   */
  public Brighten(String increment) {
    try {
      this.increment = Integer.parseInt(increment);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid increment.");
    }
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getHeight()][currentImage.getWidth()];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxVal = currentImage.getMaxValue();

    for (int i = 0; i < oldPixels.length; i++) {
      for (int j = 0; j < oldPixels[i].length; j++) {
        currentPixel = oldPixels[i][j];
        newPixels[i][j] = new Pixel(this.getValue(currentPixel.getR(), maxVal),
                this.getValue(currentPixel.getG(), maxVal),
                this.getValue(currentPixel.getB(), maxVal));
      }
    }
    newImage = new BasicImage(currentImage.getWidth(),
            currentImage.getHeight(), maxVal, newPixels);
    return newImage;
  }

  /**
   * Updates the value with the appropriate brightness.
   *
   * @param value brightness value to update with
   * @return updated value
   */
  protected int getValue(int value, int maxValue) {
    int newValue = value + this.increment;
    if (newValue >= maxValue) {
      return maxValue - 1;
    } else if (newValue < 0) {
      return 0;
    } else {
      return newValue;
    }
  }

  @Override
  public String toString() {
    return "Brighten Image: " + this.increment;
  }
}
