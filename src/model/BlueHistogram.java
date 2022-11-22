package model;

import imageinfo.IPixel;

/**
 * Gets a histogram of the blue components of the image.
 */
public class BlueHistogram extends GrabHistogram {
  @Override
  protected int getHistIndex(IPixel pixel) {
    return pixel.getB();
  }

  @Override
  public String toString() {
    return "Histogram: Blue";
  }
}
