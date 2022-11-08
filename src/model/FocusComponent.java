package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Creates a greyscale image using one component of the image.
 */
public class FocusComponent implements ImageCommand {
  private final String component;

  /**
   * Constructs a command to create a greyscale image based on a certain component.
   *
   * @param component to make the greyscale of
   * @throws new IllegalArgumentException if the component provided is invalid.
   */
  public FocusComponent(String component) {
    if (!(component.equals("value-component") || component.equals("luma-component") ||
            component.equals("intensity-component") || component.equals("red-component") ||
            component.equals("green-component") || component.equals("blue-component"))) {
      throw new IllegalArgumentException("Not a valid component to make a greyscale of");
    }
    this.component = component;
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getHeight()][currentImage.getWidth()];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxVal = -1;
    int currentVal = -1;

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
  protected int getGreyscale(IPixel currentPixel) {
    if (component.equals("value-component")) {
      return currentPixel.getValue();
    } else if (component.equals("luma-component")) {
      return currentPixel.getLuma();
    } else if (component.equals("intensity-component")) {
      return currentPixel.getIntensity();
    } else if (component.equals("red-component")) {
      return currentPixel.getR();
    } else if (component.equals("green-component")) {
      return currentPixel.getG();
    } else {
      return currentPixel.getB();
    }
  }

  @Override
  public String toString() {
    return "Focus Component: " + this.component;
  }
}
