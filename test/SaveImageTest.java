import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.IPixel;
import imageInfo.Pixel;
import model.ImageProcessorModel;
import model.SaveImage;
import model.SaveImageMock;

import static org.junit.Assert.*;

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
  public void setValues() {
    save = new SaveImage("ourImages/updatedImage.ppm");
    saveMock = new SaveImageMock("ourImages/updatedImage.ppm");
    pixels = new Pixel[10][5];
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    basic = new BasicImage(5, 10, 255, pixels);
  }

  @Test
  public void testImageSaving() {
    this.setValues();
    save.apply(basic);

    ImageProcessorModel modelTest = new ImageProcessorModel();
    modelTest.loadImage("ourImages/updatedImage.ppm", "loadedImage");
    assertEquals(basic, modelTest.getImage("loadedImage"));
  }

  @Test
  public void testImageSavingExceptions() {
    this.setValues();
    try {
      saveMock.apply(basic);
      fail("Unable to write to file.");
    }
    catch(IllegalStateException e) {
      assertNotNull(e);
    }
  }
}