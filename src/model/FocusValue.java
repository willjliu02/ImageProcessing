package model;

import imageinfo.IPixel;

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
