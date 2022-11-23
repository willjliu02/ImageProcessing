import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import model.Greyscale;
import model.Sepia;

import static org.junit.Assert.assertEquals;

/**
 * Tests the different types of ColorTransformations.
 */
public class ColorTransformationTest {

  @Test
  public void testGreyscale() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, r * c, c);
        int greyScale = pixels[r][c].getLuma();
        expectedPixels[r][c] = new Pixel(greyScale, greyScale, greyScale);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 255, pixels);
    IImage expectedImage = new BasicImage(5, 5, 255, expectedPixels);

    IImage resultImage = (new Greyscale()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testSepia() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, r * c, c);
        int newRed = (int) (r * 0.393 + r * c * 0.769 + c * 0.189);
        int newGreen = (int) (r * 0.349 + r * c * 0.686 + c * 0.169);
        int newBlue = (int) (r * 0.272 + r * c * 0.534 + c * 0.131);
        expectedPixels[r][c] = new Pixel(newRed, newGreen, newBlue);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 255, pixels);
    IImage expectedImage = new BasicImage(5, 5, 255, expectedPixels);

    IImage resultImage = (new Sepia()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}