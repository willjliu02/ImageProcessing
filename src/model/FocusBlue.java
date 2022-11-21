package model;

import imageinfo.IPixel;

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
