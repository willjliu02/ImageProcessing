package model;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

public class Filter implements ImageCommand {

  private final String filterCommand;
  private final double[][] filter;

  public Filter(String filterCommand) {
    if (filterCommand.equalsIgnoreCase("blur")) {
      this.filter = new double[][]
              {{0.0625, 0.125, 0.0625},
                      {0.125, 0.25, 0.125},
                      {0.0625, 0.125, 0.0625}};
    } else if (filterCommand.equalsIgnoreCase("sharpen")) {
      this.filter = new double[][]
              {{-0.125, -0.125, -0.125, -0.125, -0.125},
                      {-0.125, 0.25, 0.25, 0.25, -0.125},
                      {-0.125, 0.25, 1, 0.25, -0.125},
                      {-0.125, 0.25, 0.25, 0.25, -0.125},
                      {-0.125, -0.125, -0.125, -0.125, -0.125}};
    } else {
      throw new IllegalArgumentException("This is not a valid filter option.");
    }
    this.filterCommand = filterCommand;
  }


  @Override
  public IImage apply(IImage currentImage) {
    IPixel[][] currentPixels = currentImage.getPixels();
    IPixel[][] newPixels = currentImage.getPixels();

    int newRed = 0;
    int newGreen = 0;
    int newBlue = 0;

    IImage newImage = new BasicImage(currentImage);

    for (int i = 0; i < currentPixels.length; i++) {
      for (int j = 0; j < currentPixels[i].length; i++) {
        for (int k = -filter.length / 2; k < filter.length / 2 + 1; k++) {
          for (int l = -filter[i].length / 2; l < filter[i].length / 2 + 1; l++) {
            if (i + k >= 0 && i + k < currentPixels.length && j + l >= 0 &&
                    j + l < currentPixels.length) {
              newRed += (int) (currentPixels[i + k][j + l].getR() * filter[k][l]);
              newGreen += (int) (currentPixels[i + k][j + l].getG() * filter[k][l]);
              newBlue += (int) (currentPixels[i + k][j + l].getB() * filter[k][l]);
            }
          }
        }
        newPixels[i][j] = new Pixel(newRed, newGreen, newBlue);
        newRed = 0;
        newGreen = 0;
        newBlue = 0;
      }
    }
    // old maxVal okay?
    newImage = new BasicImage(currentImage.getWidth(), currentImage.getHeight(),
            currentImage.getMaxValue(), newPixels);
    return newImage;
  }
}