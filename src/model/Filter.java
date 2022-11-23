package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Represents a filter.
 */
public abstract class Filter implements ImageCommand {

  private final double[][] filter;

  /**
   * Adds a filter on to an image.
   * @param filter filter to apply
   */
  public Filter(double[][] filter) {
    this.filter = filter;
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] currentPixels = currentImage.getPixels();
    IPixel[][] newPixels = currentImage.getPixels();

    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;

    IImage newImage = new BasicImage(currentImage);

    int halfLength = filter.length / 2;

    for (int i = 0; i < currentPixels.length; i++) {
      for (int j = 0; j < currentPixels[i].length; j++) {
        for (int k = -1 * halfLength; k < halfLength + 1; k++) {
          for (int l = -1 * halfLength; l < halfLength + 1; l++) {
            if (i + k >= 0 && i + k < currentPixels.length && j + l >= 0 &&
                    j + l < currentPixels[i].length) {
              int currentR = i + k;
              int currentC = j + l;
              int filterR = k + halfLength;
              int filterC = l + halfLength;
              newRed += (int) (currentPixels[currentR][currentC].getR()
                      * filter[filterR][filterC]);
              newGreen += (int) (currentPixels[currentR][currentC].getG()
                      * filter[filterR][filterC]);
              newBlue += (int) (currentPixels[currentR][currentC].getB()
                      * filter[filterR][filterC]);
            }
          }
        }
        newRed = this.checkVal(newRed, currentImage.getMaxValue());
        newGreen = this.checkVal(newGreen, currentImage.getMaxValue());
        newBlue = this.checkVal(newBlue, currentImage.getMaxValue());
        newPixels[i][j] = new Pixel(newRed, newGreen, newBlue);
        newRed = 0;
        newGreen = 0;
        newBlue = 0;
      }
    }

    newImage = new BasicImage(currentImage.getWidth(), currentImage.getHeight(),
            currentImage.getMaxValue(), newPixels);
    return newImage;
  }

  /**
   * Updates values to make sure they don't exceed the range of possible values.
   *
   * @param value current value
   * @return corrected value
   */
  private int checkVal(int value, int maxVal) {
    if (value < 0) {
      value = 0;
    } else if (value > maxVal) {
      value = maxVal;
    }
    return value;
  }

  @Override
  public abstract String toString();
}
