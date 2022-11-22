import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

/**
 * Tests the Histogram classes.
 */
public class HistogramTest {
  private IImage image;

  private void initCond() {
    IPixel[][] pixels = new Pixel[10][10];
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        pixels[r][c] = new Pixel(r, c, r+c);
      }
    }

  }
}
