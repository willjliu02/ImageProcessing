import org.junit.Test;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.IPixel;
import imageInfo.Pixel;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests for creating Images.
 */
public class BasicImageTest {
  IImage basic;
  IPixel[][] pixels;

  /**
   * Set values to create an image with.
   */
  public void setValues() {
    pixels = new Pixel[10][5];
    int inc = 10;
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    basic = new BasicImage(5, 10, 255, pixels);
  }

  @Test
  public void testValues() {
    this.setValues();
    assertEquals(5, basic.getWidth());
    assertEquals(10, basic.getHeight());
    assertEquals(255, basic.getMaxValue());
    assertEquals(pixels, basic.getPixels());

    IPixel[][] pixels2 = new Pixel[5][10];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 10; c++) {
        pixels2[r][c] = new Pixel(r, c, r);
      }
    }
    IImage basic2 = new BasicImage(10, 5, 200, pixels2);
    IImage basic3 = new BasicImage(5, 10, 255, pixels);

    assertFalse(basic.equals(basic2));
    assertTrue(basic.equals(basic3));
  }



}