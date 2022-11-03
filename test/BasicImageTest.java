import org.junit.Test;

import imageInfo.BasicImage;
import imageInfo.IPixel;
import imageInfo.Pixel;

import static org.junit.Assert.assertEquals;

public class BasicImageTest {
  BasicImage basic;
  IPixel[][] pixels;

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
  }



}