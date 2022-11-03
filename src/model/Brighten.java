package model;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.IPixel;
import imageInfo.Pixel;

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
  public Brighten(int increment) {
    this.increment = increment;
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getWidth()][currentImage.getHeight()];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxVal = currentImage.getMaxValue();

    for (int i = 0; i < oldPixels.length; i++) {
      for (int j = 0; j < oldPixels.length; j++) {
        currentPixel = oldPixels[i][j];
        newPixels[i][j] = new Pixel(this.getValue(currentPixel.getR()),
                this.getValue(currentPixel.getG()), this.getValue(currentPixel.getB()));
        maxVal = this.getValue(currentImage.getMaxValue());
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
  protected int getValue(int value) {
    if (!(value + this.increment > 255)) {
      return value + this.increment;
    }
    return 255;
  }
}
