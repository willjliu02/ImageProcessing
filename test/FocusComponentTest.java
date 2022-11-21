import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import model.FocusBlue;
import model.FocusComponent;
import model.FocusGreen;
import model.FocusIntensity;
import model.FocusLuma;
import model.FocusRed;
import model.FocusValue;

import static org.junit.Assert.assertEquals;

/**
 * Tests the process of creating a greyscale with a specific image component.
 */
public class FocusComponentTest {

  @Test
  public void testFocusRed() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r, r, r);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 4, pixels);
    IImage expectedImage = new BasicImage(5, 5, 4, expectedPixels);

    IImage resultImage = (new FocusRed()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testFocusGreen() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r + c);
        expectedPixels[r][c] = new Pixel(c, c, c);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 8, pixels);
    IImage expectedImage = new BasicImage(5, 5, 4, expectedPixels);

    IImage resultImage = (new FocusGreen()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testFocusBlue() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r + c);
        expectedPixels[r][c] = new Pixel(r + c, r + c, r + c);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 8, pixels);
    IImage expectedImage = new BasicImage(5, 5, 8, expectedPixels);

    IImage resultImage = (new FocusBlue()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testFocusIntensity() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r + c);
        int avg = (r + c) * 2 / 3;
        expectedPixels[r][c] = new Pixel(avg, avg, avg);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 8, pixels);
    IImage expectedImage = new BasicImage(5, 5, 5, expectedPixels);

    IImage resultImage = (new FocusIntensity()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testFocusValue() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, r + c, c);
        expectedPixels[r][c] = new Pixel(r + c, r + c, r + c);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 8, pixels);
    IImage expectedImage = new BasicImage(5, 5, 8, expectedPixels);

    IImage resultImage = (new FocusValue()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testFocusLuma() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, r + c, c);
        int luma = (int) ((r * 0.2126) + ((r + c) * 0.7152) + (c * 0.0722));
        expectedPixels[r][c] = new Pixel(luma, luma, luma);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 8, pixels);
    IImage expectedImage = new BasicImage(5, 5, 6, expectedPixels);

    IImage resultImage = (new FocusLuma()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}