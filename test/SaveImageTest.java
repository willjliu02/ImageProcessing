import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import model.ImageProcessorModel;
import model.SaveImage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;


/**
 * Tests the save image command.
 */
public class SaveImageTest {
  SaveImage save;
  SaveImageMock saveMock;
  IImage basic;
  IPixel[][] pixels;

  /**
   * Set values to create an image with.
   */
  private void setValues() {
    save = new SaveImage("ourImages/updatedImage.ppm");
    saveMock = new SaveImageMock("ourImages/updatedImage.ppm");
    pixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    basic = new BasicImage(5, 5, 255, pixels);
  }

  @Test
  public void testImageSaving() {
    this.setValues();
    save.apply(basic);

    ImageProcessorModel modelTest = new ImageProcessorModel();
    modelTest.loadImage("ourImages/updatedImage.ppm", "loadedImage");

    IImage resultImage = modelTest.getImage("loadedImage");

    assertEquals(basic.getWidth(), resultImage.getWidth());
    assertEquals(basic.getHeight(), resultImage.getHeight());
    assertEquals(basic.getMaxValue(), resultImage.getMaxValue());
    assertEquals(basic.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testImageSavingExceptions() {
    this.setValues();
    try {
      saveMock.apply(basic);
      fail("Unable to write to file.");
    } catch (IllegalStateException e) {
      assertNotNull(e);
    }
  }
}