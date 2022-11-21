import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import model.Blur;
import model.Filter;
import model.Sharpen;

import static org.junit.Assert.assertEquals;

/**
 * Tests the types of Filters.
 */
public class FilterTest {

  @Test
  public void testBlur() {
    IPixel[][] pixels = new Pixel[3][3];
    IPixel[][] expected = new Pixel[3][3];

    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        pixels[r][c] = new Pixel(r * 16, r * c * 16, c * 16);
      }
    }

    expected[0][0] = new Pixel(3, 1, 3);
    expected[0][1] = new Pixel(4, 4, 12);
    expected[0][2] = new Pixel(3, 5, 15);

    expected[1][0] = new Pixel(12, 4, 4);
    expected[1][1] = new Pixel(16, 16, 16);
    expected[1][2] = new Pixel(12, 20, 20);

    expected[2][0] = new Pixel(15, 5, 3);
    expected[2][1] = new Pixel(20, 20, 12);
    expected[2][2] = new Pixel(15, 25, 15);

    IImage starterImage = new BasicImage(3, 3, 255, pixels);
    IImage expectedImage = new BasicImage(3, 3, 255, expected);

    IImage resultImage = (new Blur()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testSharpen() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expected = new Pixel[5][5];

    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r * 8, r * c * 8, c * 8);
      }
    }

    expected[0][0] = new Pixel(0, 0, 0);
    expected[0][1] = new Pixel(0, 0, 6);
    expected[0][2] = new Pixel(0, 0, 18);
    expected[0][3] = new Pixel(0, 0, 42);
    expected[0][4] = new Pixel(0, 0, 39);

    expected[1][0] = new Pixel(6, 0, 0);
    expected[1][1] = new Pixel(9, 0, 9);
    expected[1][2] = new Pixel(3, 6, 26);
    expected[1][3] = new Pixel(9, 39, 59);
    expected[1][4] = new Pixel(6, 33, 51);

    expected[2][0] = new Pixel(18, 0, 0);
    expected[2][1] = new Pixel(26, 6, 3);
    expected[2][2] = new Pixel(16, 32, 16);
    expected[2][3] = new Pixel(26, 98, 49);
    expected[2][4] = new Pixel(18, 84, 42);

    expected[3][0] = new Pixel(42, 0, 0);
    expected[3][1] = new Pixel(59, 39, 9);
    expected[3][2] = new Pixel(49, 98, 26);
    expected[3][3] = new Pixel(59, 197, 59);
    expected[3][4] = new Pixel(42, 171, 51);

    expected[4][0] = new Pixel(39, 0, 0);
    expected[4][1] = new Pixel(51, 33, 6);
    expected[4][2] = new Pixel(42, 84, 18);
    expected[4][3] = new Pixel(51, 171, 42);
    expected[4][4] = new Pixel(39, 162, 39);

    IImage starterImage = new BasicImage(5, 5, 255, pixels);
    IImage expectedImage = new BasicImage(5, 5, 255, expected);

    IImage resultImage = (new Sharpen()).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}