package model;

import imageinfo.IPixel;

/**
 * Focus the entire image the Green component of the IImage.
 */
public class FocusGreen extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getG();
  }

  @Override
  public String toString() {
    return "Focus Component: green-component";
  }
}
