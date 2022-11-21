package model;

import imageinfo.IPixel;

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
