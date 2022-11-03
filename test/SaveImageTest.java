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
    int inc = 10;
    for (int r = 0; r < 10; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    basic = new BasicImage(5, 10, 255, pixels);
  }

  @Test
  public void testImageSaving() {
    save.apply(basic);
    Appendable file = this.createFile();

    ImageProcessorModel modelTest = new ImageProcessorModel();
    modelTest.loadImage("ourImages/updatedImage.ppm", "loadedImage");
    assertEquals(basic, modelTest.getImage("loadedImage"));
  }

  public void testImageSavingExceptions() {
    try {
      saveMock.apply(basic);
      fail("Unable to write to file.");
    }
    catch(IllegalStateException e) {
      assertNotNull(e);
    }
  }

  /**
   * Creates a file with info from the basic image.
   * @return a file in PPM format for that information
   */
  public FileWriter createFile() {
    FileWriter file;
    try {
      file = new FileWriter("ourImages/updatedImage.ppm");
      int width = basic.getWidth();
      int height = basic.getHeight();
      IPixel[][] pixels = basic.getPixels();

      file.append("P3\n");
      file.append(width + " " + height);
      file.append("\n");
      file.append(basic.getMaxValue() + "\n");
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          IPixel pixel = pixels[r][c];
          file.append(pixel.getR() + " ");
          file.append(pixel.getG() + " ");
          file.append(pixel.getB() + " ");
        }
        file.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to file.");
    }
    return file;
  }
}