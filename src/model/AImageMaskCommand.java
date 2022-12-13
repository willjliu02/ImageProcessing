package model;

import imageinfo.IImage;
import imageinfo.IImageMask;
import imageinfo.IPixel;

public abstract class AImageMaskCommand implements IImageMaskCommand {
  private IImageMask mask;

  public AImageMaskCommand() {
    this.mask = null;
  }
  @Override
  public void setMask(IImageMask mask) throws IllegalStateException {
    if (this.mask != null) {
      throw new IllegalStateException("You can only have 1 mask per command.");
    }

    this.mask = mask;
  }

  protected boolean isModifiable(int r, int c) {
    if (mask == null) {
      return true;
    }

    return this.mask.isModifiable(c, r);
  }

  @Override
  public abstract IImage apply(IImage currentImage);
}
