import org.junit.Test;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;
import model.FlipImage;

import static org.junit.Assert.assertEquals;

/**
 * Tests for flipping an image horizontally and vertically.
 */
public class FlipImageTest {

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorException() {
    new FlipImage("anything");
  }

  @Test
  public void testCorrectHorizontalApply() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][4 - c] = new Pixel(r, c, r);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 5, pixels);
    IImage expectedImage = new BasicImage(5, 5, 5, expectedPixels);

    IImage resultImage = (new FlipImage("horizontal-flip")).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testCorrectVerticalApply() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[4 - r][c] = new Pixel(r, c, r);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 5, pixels);
    IImage expectedImage = new BasicImage(5, 5, 5, expectedPixels);

    IImage resultImage = (new FlipImage("vertical-flip")).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}