package model;

import imageinfo.IPixel;

public class FocusLuma extends FocusComponent {
  @Override
  protected int getGreyscale(IPixel currentPixel) {
    return currentPixel.getLuma();
  }

  @Override
  public String toString() {
    return "Focus Component: luma-component";
  }
}
