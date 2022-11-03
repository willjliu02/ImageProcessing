import org.junit.Test;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.IPixel;
import imageInfo.Pixel;
import model.Brighten;

import static org.junit.Assert.assertEquals;

/**
 * Tests for the brighten command.
 */
public class BrightenTest {

  @Test
  public void testNormalBrightening() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 5, pixels);
    IImage expectedImage = new BasicImage(5, 5, 5 + inc, expectedPixels);

    IImage resultImage = (new Brighten(inc)).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testBrightenOver255() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 255;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(inc, inc, inc);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 5, pixels);
    IImage expectedImage = new BasicImage(5, 5, 255, expectedPixels);

    IImage resultImage = (new Brighten(inc)).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testBrightenBelow0() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = -255;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(0, 0, 0);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 5, pixels);
    IImage expectedImage = new BasicImage(5, 5, 0, expectedPixels);

    IImage resultImage = (new Brighten(inc)).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}