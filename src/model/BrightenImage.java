package model;

import imageInfo.IImage;

public class BrightenImage implements ImageCommand {

  private int brightScale;

  public BrightenImage(int brightScale) {

  }

  @Override
  public IImage apply(IImage image) {
    return null;
  }
}
