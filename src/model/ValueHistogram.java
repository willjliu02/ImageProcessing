package model;

import imageinfo.IPixel;

/**
 * Gets a histogram of the max component of the image.
 */
public class ValueHistogram extends GrabHistogram {
  @Override
  protected int getHistIndex(IPixel pixel) {
    return pixel.getValue();
  }

  @Override
  public String toString() {
    return "Histogram: Value";
  }
}
