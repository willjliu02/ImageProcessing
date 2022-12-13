package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Creates a greyscale image using one component of the image.
 */
public abstract class FocusComponent implements ImageCommand {
  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getHeight()][currentImage.getWidth()];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxVal = currentImage.getMaxValue();
    int currentVal;

    for (int i = 0; i < oldPixels.length; i++) {
      for (int j = 0; j < oldPixels[i].length; j++) {
        currentPixel = oldPixels[i][j];
        currentVal = this.getGreyscale(currentPixel);
        newPixels[i][j] = new Pixel(currentVal, currentVal, currentVal);
        if (currentVal > maxVal) {
          maxVal = currentVal;
        }
      }
    }
    newImage = new BasicImage(currentImage.getWidth(),
            currentImage.getHeight(), maxVal, newPixels);
    return newImage;
  }

  /**
   * Returns the component value to be used for the greyscale.
   *
   * @param currentPixel pixel we're using.
   * @return greyscale component value.
   */
  protected abstract int getGreyscale(IPixel currentPixel);

  @Override
  public abstract String toString();
}
