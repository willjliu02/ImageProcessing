import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

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
    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = (new Brighten("" + inc)).apply(starterImage);

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
    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = (new Brighten("" + inc)).apply(starterImage);

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
    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = (new Brighten("" + inc)).apply(starterImage);

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithUnmodifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, pixels);

    IImage resultImage = starterImage.maskBuilder().build(
            new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithPartialModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        if (r <= 2 && c <= 2) {
          expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
        } else {
          expectedPixels[r][c] = new Pixel(r, c, r);
        }
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(0, 0, 2, 2)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithFullModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(0, 0, 4, 4)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithNegLeftModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(-1, 0, 4, 4)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithNegTopModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(0, -1, 4, 4)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithOverLengthRightModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(0, -1, 10, 4)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testNormalBrighteningWithOverLengthBottomModifiableMask() {
    IPixel[][] pixels = new Pixel[5][5];
    IPixel[][] expectedPixels = new Pixel[5][5];
    int inc = 10;
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
        expectedPixels[r][c] = new Pixel(r + inc, c + inc, r + inc);
      }
    }

    IImage starterImage = new BasicImage(5, 5, 256, pixels);
    IImage expectedImage = new BasicImage(5, 5, 256, expectedPixels);

    IImage resultImage = starterImage.maskBuilder()
            .setModifiable(0, 0, 4, 10)
            .build(new Brighten("" + inc));

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}