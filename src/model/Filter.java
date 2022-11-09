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
      for (int j = 0; j < currentPixels[i].length; j++) {
        for (int k = -filter.length / 2; k < filter.length / 2 + 1; k++) {
          for (int l = -filter.length / 2; l < filter.length / 2 + 1; l++) {
            //System.out.println("i j " + i + " " + j);
            if (i + k >= 0 && i + k < currentPixels.length && j + l >= 0 &&
                    j + l < currentPixels[i].length) {
              //System.out.println("filter x: " + (k
                  //    + filter.length / 2));
              //System.out.println("filter y: " + (l + filter[i].length / 2));
              newRed += (int) (currentPixels[i + k][j + l].getR() * filter[k
                      + filter.length / 2][l + filter.length / 2]);
              newGreen += (int) (currentPixels[i + k][j + l].getG() * filter[k
                      + filter.length / 2][l + filter.length / 2]);
              newBlue += (int) (currentPixels[i + k][j + l].getB() * filter[k
                      + filter.length / 2][l + filter.length / 2]);
            }
          }
        }
        newRed = this.checkVal(newRed);
        newGreen = this.checkVal(newGreen);
        newBlue = this.checkVal(newBlue);
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

  private int checkVal(int value) {
    if(value < 0) {
      value = 0;
    }
    else if(value > 255) {
      value = 255;
    }
    return value;
  }
}
