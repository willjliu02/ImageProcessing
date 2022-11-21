package model;

import imageinfo.IPixel;

/**
 * Focus the entire image the Blue component of the IImage.
 */
public class FocusBlue extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getB();
  }

  @Override
  public String toString() {
    return "Focus Component: blue-component";
  }
}
