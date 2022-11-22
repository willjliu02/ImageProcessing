package model;

import imageinfo.IPixel;

/**
 * Gets a histogram of the red components of the image.
 */
public class RedHistogram extends GrabHistogram {
  @Override
  protected int getHistIndex(IPixel pixel) {
    return pixel.getR();
  }

  @Override
  public String toString() {
    return "Histogram: Red";
  }
}
