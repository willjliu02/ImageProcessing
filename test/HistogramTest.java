import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.BlueHistogram;
import model.GreenHistogram;
import model.RedHistogram;
import model.ValueHistogram;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Histogram classes.
 */
public class HistogramTest {
  private IImage image;

  private void initCond() {
    IPixel[][] pixels = new Pixel[10][10];

    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        pixels[r][c] = new Pixel(r * 25, c  * 20, r % 5);
      }
    }

    this.image = new BasicImage(10, 10, 256, pixels);
  }

  @Test
  public void testRed() {
    this.initCond();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      if (i % 25 == 0 && i <= 225) {
        expected.add(10);
      } else {
        expected.add(0);
      }
    }

    List<Integer> result = (new RedHistogram()).apply(this.image);

    assertEquals(expected, result);
  }

  @Test
  public void testGreen() {
    this.initCond();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      if (i % 20 == 0 && i <= 180) {
        expected.add(10);
      } else {
        expected.add(0);
      }
    }

    List<Integer> result = (new GreenHistogram()).apply(this.image);

    assertEquals(expected, result);
  }

  @Test
  public void testBlue() {
    this.initCond();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      if (i < 5) {
        expected.add(20);
      } else {
        expected.add(0);
      }
    }

    List<Integer> result = (new BlueHistogram()).apply(this.image);

    assertEquals(expected, result);
  }

  @Test
  public void testValue() {
    this.initCond();
    List<Integer> expected = new ArrayList<Integer>();

    for (int i = 0; i < 256; i++) {
      expected.add(0);
    }

    IPixel[][] pixels = this.image.getPixels();

    for (int r = 0; r < 10; r++)  {
      for (int c = 0; c < 10; c++) {
        IPixel pixel = pixels[r][c];
        int index = pixel.getR();
        if (pixel.getG() > index) {
          index = pixel.getG();
        }
        if (pixel.getB() > index) {
          index = pixel.getB();
        }

        expected.set(index, expected.get(index) + 1);
      }
    }

    List<Integer> result = (new ValueHistogram()).apply(this.image);

    assertEquals(expected, result);
  }
}
