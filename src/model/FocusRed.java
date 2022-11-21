package model;

import imageinfo.IPixel;

/**
 * Focus the entire image the Red component of the IImage.
 */
public class FocusRed extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getR();
  }

  @Override
  public String toString() {
    return "Focus Component: red-component";
  }
}
