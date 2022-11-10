import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.ImageUtil;
import imageinfo.Pixel;

import org.junit.Test;

import model.IImageProcessor;
import model.ImageProcessorModel;
import model.MockImageCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests if the ImageProcessorModel works correctly.
 */
public class ImageProcessorModelTest {
  private IImageProcessor model;
  private IImage image;

  private void initCond() {
    this.model = new ImageProcessorModel();
    ImageUtil processImage = new ImageUtil("res/twoByTwo.ppm");
    IPixel[][] pixels = processImage.getPixels();
    this.image = new BasicImage(2, 2, 255, pixels);
  }

  @Test
  public void testHasImage() {
    this.initCond();
    assertFalse(this.model.hasImage("twoBytwo"));
    this.model.loadImage(image, "twoBytwo");
    assertTrue(this.model.hasImage("twoBytwo"));
  }

  @Test
  public void testLoadPPMLoading() {
    this.initCond();
    int width = 2;
    int height = 2;
    IPixel[][] expectedPixels = new Pixel[height][width];
    int counter = 0;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        expectedPixels[r][c] = new Pixel(counter, counter, counter);
        counter++;
      }
    }
    IImage expectedImage = new BasicImage(2, 2, 255, expectedPixels);

    ImageUtil loadedImage = new ImageUtil("res/twoByTwo.ppm");
    IImage image = new BasicImage(loadedImage.getWidth(), loadedImage.getHeight(),
            loadedImage.getMaxValue(), loadedImage.getPixels());

    this.model.loadImage(image, "twoBytwo");

    IImage resultImage = this.model.getImage("twoBytwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyCommandImageDoesntExistException() {
    this.initCond();
    this.model.applyCommand("random image",
            new MockImageCommand(null),
            "random");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyCommandNullException() {
    this.initCond();
    this.model.loadImage(image, "twoBytwo");
    this.model.applyCommand("twoBytwo",
            null,
            "random");
  }

  @Test
  public void testApplyCommand() {
    this.initCond();
    Appendable log = new StringBuilder();
    this.model.loadImage(image, "twoBytwo");
    this.model.applyCommand("twoBytwo", new MockImageCommand(log), "twoByTwo");
    String expected = "Width: 2\n"
            + "Height: 2\n"
            + "Max Value: 255\n"
            + "(0 0 0) (1 1 1) \n"
            + "(2 2 2) (3 3 3) \n";

    assertEquals(expected, log.toString());
  }

  @Test
  public void testGetImage() {
    ImageUtil processImage = new ImageUtil("res/twoByTwo.ppm");
    IImage expectedImage = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    this.initCond();
    this.model.loadImage(expectedImage, "twoBytwo");

    IImage resultImage = this.model.getImage("twoBytwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveException() {
    this.initCond();
    this.model.saveImage("random", "random/random");
  }

  @Test
  public void testSaveSuccessful() {
    this.initCond();
    this.model.loadImage(image, "twoByTwo");
    IImage expectedImage = this.model.getImage("twoByTwo");
    this.model.saveImage("res/newTwoByTwo.ppm", "twoByTwo");

    ImageUtil loadedImage = new ImageUtil("res/newTwoByTwo.ppm");
    IImage savedImage = new BasicImage(loadedImage.getWidth(),
            loadedImage.getHeight(),
            loadedImage.getMaxValue(),
            loadedImage.getPixels());

    this.model.loadImage(savedImage, "newTwoByTwo");

    IImage resultImage = this.model.getImage("newTwoByTwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}