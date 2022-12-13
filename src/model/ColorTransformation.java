package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Represents a Color Transformation.
 */
public abstract class ColorTransformation extends AImageMaskCommand {
  private final double[][] transform;

  /**
   * Constructs a ColorTranformation to an IImage.
   * @param transform transform filter
   */
  public ColorTransformation(double[][] transform) {
    this.transform = transform;
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] currentPixels = currentImage.getPixels();
    IPixel[][] newPixels = currentImage.getPixels();
    int imageMaxValue = currentImage.getMaxValue();

    for (int i = 0; i < currentPixels.length; i++) {
      for (int j = 0; j < currentPixels[i].length; j++) {

        IPixel newPixel;
        IPixel currentPixel = currentPixels[i][j];
        if (isModifiable(i, j)) {
          double[] colorValues = new double[]{currentPixel.getR(), currentPixel.getG(),
                  currentPixel.getB()};
          double[] resultColor = this.getColor(colorValues);
          newPixel = new Pixel(this.checkVal((int) (resultColor[0]),
                  imageMaxValue),
                  this.checkVal((int) (resultColor[1]), imageMaxValue),
                  this.checkVal((int) (resultColor[2]), imageMaxValue));
        } else {
          newPixel = currentPixel;
        }

        newPixels[i][j] = newPixel;

      }
    }
    IImage newImage = new BasicImage(currentImage.getWidth(), currentImage.getHeight(),
            currentImage.getMaxValue(), newPixels);
    return newImage;
  }

  /**
   * Returns the new RGB values.
   *
   * @param colorValues current color values
   * @return new color values
   */
  private double[] getColor(double[] colorValues) {
    double[] resultColor = new double[this.transform.length];
    double currentVal = 0;

    for (int i = 0; i < resultColor.length; i++) {
      for (int k = 0; k < colorValues.length; k++) {
        currentVal += this.transform[i][k] * colorValues[k];
      }
      resultColor[i] = currentVal;
      currentVal = 0;
    }
    return resultColor;
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
    } else if (value >= maxVal) {
      value = maxVal - 1;
    }
    return value;
  }

  @Override
  public abstract String toString();
}
