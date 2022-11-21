package model;

import imageinfo.IPixel;

/**
 * Focus the entire image the Intensity component of the IImage.
 */
public class FocusIntensity extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getIntensity();
  }

  @Override
  public String toString() {
    return "Focus Component: intensity-component";
  }
}
