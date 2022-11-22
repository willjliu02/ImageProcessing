package model;

import imageinfo.IPixel;

/**
 * Gets a histogram of the green components of the image.
 */
public class GreenHistogram extends GrabHistogram {
  @Override
  protected int getHistIndex(IPixel pixel) {
    return pixel.getG();
  }
}
