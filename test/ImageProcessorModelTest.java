import org.junit.Test;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.ImageUtil;
import imageinfo.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
  public void testLoadAppears() {
    this.initCond();
    assertEquals(false, this.model.hasImage("twoBytwo"));
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");
    assertEquals(true, this.model.hasImage("twoBytwo"));
  }

  @Test
  public void testLoadPPMFormatting() {
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
    this.model.loadImage("ourImages/twoByTwo.ppm", "twoBytwo");

    IImage resultImage = this.model.getImage("twoBytwo");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testLoadPNGFormatting() {
    this.initCond();
    int width = 5;
    int height = 5;
    IPixel[][] expectedPixels = new Pixel[height][width];
    int counter = 0;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        expectedPixels[r][c] = new Pixel(counter, counter, counter);
        counter++;
      }
    }
    IImage expectedImage = new BasicImage(width, height, 255, expectedPixels);
    this.model.loadImage("ourImages/customPNG.png", "customPNG");

    IImage resultImage = this.model.getImage("customPNG");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testLoadBMPFormatting() {
    this.initCond();
    int width = 7;
    int height = 5;
    IPixel[][] expectedPixels = new Pixel[height][width];
    int counter = 0;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        expectedPixels[r][c] = new Pixel(counter, counter, counter);
        counter++;
      }
    }
    IImage expectedImage = new BasicImage(width, height, 255, expectedPixels);
    this.model.loadImage("ourImages/customBMP.bmp", "customBMP");

    IImage resultImage = this.model.getImage("customBMP");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testLoadJPGFormatting() {
    this.initCond();
    int width = 5;
    int height = 7;
    IPixel[][] expectedPixels = new Pixel[height][width];
    int counter = 0;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        expectedPixels[r][c] = new Pixel(counter, counter, counter);
        counter++;
      }
    }
    IImage expectedImage = new BasicImage(width, height, 255, expectedPixels);
    this.model.loadImage("ourImages/customJPG.jpg", "customJPG");

    IImage resultImage = this.model.getImage("customJPG");

    assertEquals(expectedImage.getHeight(), resultImage.getHeight());
    assertEquals(expectedImage.getWidth(), resultImage.getWidth());
    assertEquals(expectedImage.getMaxValue(), resultImage.getMaxValue());
    assertEquals(expectedImage.getPixels(), resultImage.getPixels());
  }

  @Test
  public void nothing() {
    int width = 5;
    int height = 7;
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    int counter = 0;
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        Color color = new Color(counter, counter, counter);
        image.setRGB(c, r, color.getRGB());
        counter++;
      }
    }

    File file = new File("ourImages/customJPG.jpg");
    try {
      ImageIO.write(image, "JPG", file);
    }
    catch(IOException e) {
      System.out.println("Saving failed!");
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