package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Represents a Color Transformation.
 */
public class ColorTransformation implements ImageCommand {
  private final String transformation;
  private final double[][] transform;

  public ColorTransformation(String transformation) {
    if (transformation.equalsIgnoreCase("greyscale")) {
      this.transform = new double[][]
              {{0.2126, 0.7152, 0.0722},
                      {0.2126, 0.7152, 0.0722},
                      {0.2126, 0.7152, 0.0722}};
    } else if (transformation.equalsIgnoreCase("sepia")) {
      this.transform = new double[][]
              {{0.393, 0.769, 0.189},
                      {0.349, 0.686, 0.168},
                      {0.272, 0.534, 0.131}};
    } else {
      throw new IllegalArgumentException("This is not a valid color transformation option.");
    }
    this.transformation = transformation;
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] currentPixels = currentImage.getPixels();
    IPixel[][] newPixels = currentImage.getPixels();

    double[] colorValues = new double[3];
    double[] resultColor = new double[colorValues.length];

    for (int i = 0; i < currentPixels.length; i++) {
      for (int j = 0; j < currentPixels[i].length; j++) {
        colorValues = new double[]{currentPixels[i][j].getR(), currentPixels[i][j].getG(),
                currentPixels[i][j].getB()};
        resultColor = this.getColor(colorValues);
        newPixels[i][j] = new Pixel(this.checkVal((int) (resultColor[0]), currentImage.getMaxValue()),
                this.checkVal((int) (resultColor[1]), currentImage.getMaxValue()),
                this.checkVal((int) (resultColor[2]), currentImage.getMaxValue()));

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
    } else if (value > maxVal) {
      value = maxVal;
    }
    return value;
  }

  @Override
  public String toString() {
    return "Color Transformation: " + this.transformation;
  }
}
