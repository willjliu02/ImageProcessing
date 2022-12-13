import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import model.DownScale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

/**
 * Tests downscaling feature.
 */
public class DownScaleTest {


  @Test
  public void testDownscale() {
    IPixel[][] pixels = new Pixel[10][10];

    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    IImage starterImage = new BasicImage(10, 10, 255, pixels);
    IImage resultImage = (new DownScale("5x5")).apply(starterImage);

    assertEquals(5, resultImage.getHeight());
    assertEquals(5, resultImage.getWidth());
  }

  @Test
  public void testUpscaleFail() {
    IPixel[][] pixels = new Pixel[10][10];

    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 10; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    IImage starterImage = new BasicImage(10, 10, 255, pixels);

    try {
      IImage resultImage = (new DownScale("20x20")).apply(starterImage);
      fail("Only downscaling is supported.");
    } catch (IllegalArgumentException e) {
      assertNotNull(e);
    }
  }
}
