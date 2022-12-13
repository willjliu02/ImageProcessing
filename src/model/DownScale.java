package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

/**
 * Downscales the image size.
 */
public class DownScale implements ImageCommand {
  private int newWidth;
  private int newHeight;

  /**
   * Downscales the image.
   * @param inputs inputs.
   */
  public DownScale(String inputs) {
    try {
      this.newWidth = Integer.parseInt(inputs.substring(0, inputs.indexOf("x")));
      this.newHeight = Integer.parseInt(inputs.substring(inputs.indexOf("x") + 1));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Invalid width or height.");
    }
  }

  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[this.newHeight][this.newWidth];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxVal = currentImage.getMaxValue();

    double xRatio = 0;
    double yRatio = 0;

    double newX = 0;
    double newY = 0;

    for (int i = 0; i < oldPixels.length; i++) {
      for (int j = 0; j < oldPixels[i].length; j++) {
        currentPixel = oldPixels[i][j];
        xRatio =  ((double) (j + 1)) / oldPixels[i].length;
        yRatio =  ((double) (i + 1)) / oldPixels.length;
        newX = xRatio * this.newWidth - 1;
        newY = yRatio * this.newHeight - 1;
        newPixels[(int) (newY)][(int) (newX)] = new Pixel(currentPixel.getR(), currentPixel.getG(),
                  currentPixel.getB());
      }
    }
    newImage = new BasicImage(newWidth, newHeight, maxVal, newPixels);
    return newImage;
  }

  @Override
  public String toString() {
    return "Downsize Image";
  }
}
