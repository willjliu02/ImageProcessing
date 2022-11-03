import org.junit.Test;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.ImageUtil;
import model.IImageProcessor;
import model.ImageProcessorModel;
import model.MockImageCommand;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests if the ImageProcessorModel works correctly.
 */
public class ImageProcessorModelTest {
  private IImageProcessor model;
  private void initCond() {
    this.model = new ImageProcessorModel();
  }

  @Test
  public void testHasImage() {
    this.initCond();
    assertEquals(false, this.model.hasImage("twoBytwo"));
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");
    assertEquals(true, this.model.hasImage("twoBytwo"));
  }

  @Test
  public void testLoad() {
    this.initCond();
    try {
      this.model.applyCommand("twoBytwo",
              new MockImageCommand(null),
              "random");
      fail();
    } catch (IllegalArgumentException e) {
      this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");
      this.model.applyCommand("twoBytwo",
              new MockImageCommand(new StringBuilder()),
              "random");
      assertEquals(true, this.model.hasImage("twoBytwo"));
    }
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
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");
    this.model.applyCommand("twoBytwo",
            null,
            "random");
  }

  @Test
  public void testApplyCommand() {
    this.initCond();
    Appendable log = new StringBuilder();
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");
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
    ImageUtil processImage = new ImageUtil("ourImages/twoByTwo.ppm");
    IImage expectedImage = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    this.initCond();
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");

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
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoByTwo");
    IImage expectedImage = this.model.getImage("twoByTwo");
    this.model.saveImage("ourImages/newTwoByTwo.ppm", "twoByTwo");
    this.model.loadImage("ourImages/newTwoByTwo.ppm", "newTwoByTwo");

    IImage resultImage = this.model.getImage("newTwoByTwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}