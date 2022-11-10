import org.junit.Test;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.ImageUtil;
import imageinfo.Pixel;

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
  private IImage image;

  private void initCond() {
    this.model = new ImageProcessorModel();
    ImageUtil processImage = new ImageUtil("ourImages/twoByTwo.ppm");
    IPixel[][] pixels = processImage.getPixels();
    this.image = new BasicImage(2, 2, 255, pixels);
  }

  @Test
  public void testHasImage() {
    this.initCond();
    assertEquals(false, this.model.hasImage("twoBytwo"));
    this.model.loadImage(image, "twoBytwo");
    assertEquals(true, this.model.hasImage("twoBytwo"));
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
    ImageUtil processImage = new ImageUtil("ourImages/twoByTwo.ppm");
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
    this.model.saveImage("ourImages/newTwoByTwo.ppm", "twoByTwo");

    ImageUtil processImage = new ImageUtil("ourImages/newTwoByTwo.ppm");
    IImage savedImage = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());

    this.model.loadImage(savedImage, "newTwoByTwo");

    IImage resultImage = this.model.getImage("newTwoByTwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }
}