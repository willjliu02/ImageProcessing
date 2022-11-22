package model;

import imageinfo.IImage;
import imageinfo.IPixel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Creates a Histogram of some component of a pixel.
 */
public abstract class GrabHistogram implements Function<IImage, List<Integer>> {
  @Override
  public List<Integer> apply(IImage image) {
    List<Integer> histogram = new ArrayList<Integer>(256);
    for (int i = 0; i < 256; i++) {
      histogram.add(0);
    }

    IPixel[][] pixels = image.getPixels();
    int width = image.getWidth();
    int height = image.getHeight();
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        int index = getHistIndex(pixels[r][c]);
        histogram.set(index, histogram.get(index) + 1);
      }
    }

    return histogram;
  }

  protected abstract int getHistIndex(IPixel pixel);

  public abstract String toString();
}
