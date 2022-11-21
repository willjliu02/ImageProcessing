package model;

import imageinfo.IPixel;

/**
 * Focus the entire image the Value component of the IImage.
 */
public class FocusValue extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getValue();
  }

  @Override
  public String toString() {
    return "Focus Component: value-component";
  }
}
