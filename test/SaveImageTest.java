import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.ImageUtil;
import imageinfo.Pixel;

import org.junit.Test;

import model.ImageProcessorModel;
import model.SaveImage;
import model.SaveImageMock;

import static org.junit.Assert.assertEquals;


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
  private void setValues(String path) {
    save = new SaveImage(path);
    saveMock = new SaveImageMock(path);
    pixels = new Pixel[5][5];
    for (int r = 0; r < 5; r++) {
      for (int c = 0; c < 5; c++) {
        pixels[r][c] = new Pixel(r, c, r);
      }
    }
    basic = new BasicImage(5, 5, 255, pixels);
  }

  @Test
  public void testPPMImageSaving() {
    this.setValues("res/updatedImage.ppm");
    save.apply(basic);

    ImageProcessorModel modelTest = new ImageProcessorModel();
    ImageUtil processImage = new ImageUtil("res/updatedImage.ppm");
    IImage image = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    modelTest.loadImage(image, "loadedImage");

    IImage resultImage = modelTest.getImage("loadedImage");

    assertEquals(basic.getWidth(), resultImage.getWidth());
    assertEquals(basic.getHeight(), resultImage.getHeight());
    assertEquals(basic.getMaxValue(), resultImage.getMaxValue());
    assertEquals(basic.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testPNGImageSaving() {
    String path = "res/updatedImagePNG.png";
    this.setValues(path);
    save.apply(basic);

    ImageProcessorModel modelTest = new ImageProcessorModel();
    ImageUtil processImage = new ImageUtil(path);
    IImage image = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    modelTest.loadImage(image, "loadedImage");

    IImage resultImage = modelTest.getImage("loadedImage");

    assertEquals(basic.getWidth(), resultImage.getWidth());
    assertEquals(basic.getHeight(), resultImage.getHeight());
    assertEquals(basic.getMaxValue(), resultImage.getMaxValue());
    assertEquals(basic.getPixels(), resultImage.getPixels());
  }

  @Test
  public void testBMPImageSaving() {
    String path = "res/updatedImageBMP.bmp";
    this.setValues(path);
    save.apply(basic);

    ImageProcessorModel modelTest = new ImageProcessorModel();
    ImageUtil processImage = new ImageUtil(path);
    IImage image = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    modelTest.loadImage(image, "loadedImage");

    IImage resultImage = modelTest.getImage("loadedImage");

    assertEquals(basic.getWidth(), resultImage.getWidth());
    assertEquals(basic.getHeight(), resultImage.getHeight());
    assertEquals(basic.getMaxValue(), resultImage.getMaxValue());
    assertEquals(basic.getPixels(), resultImage.getPixels());
  }

  @Test(expected = IllegalStateException.class)
  public void testImageSavingExceptions() {
    this.setValues("res/updatedImage.ppm");
    saveMock.apply(basic);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testImageExtensionException() {
    new SaveImage("invalid/path");
  }
}