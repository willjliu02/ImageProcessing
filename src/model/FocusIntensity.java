package model;

import imageinfo.IPixel;

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
