package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Creates a greyscale image using one component of the image.
 */
public abstract class FocusComponent extends AImageMaskCommand {
  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getHeight()][currentImage.getWidth()];
    IPixel currentPixel;
    int maxVal = currentImage.getMaxValue();


    for (int i = 0; i < oldPixels.length; i++) {
      for (int j = 0; j < oldPixels[i].length; j++) {
        currentPixel = oldPixels[i][j];
        IPixel newPixel;

        if (isModifiable(i, j)) {
          int currentVal = this.getGreyscale(currentPixel);
          newPixel = new Pixel(currentVal, currentVal, currentVal);
        } else {
          newPixel = currentPixel;
        }

        newPixels[i][j] = newPixel;
      }
    }
    IImage newImage = new BasicImage(currentImage.getWidth(),
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
