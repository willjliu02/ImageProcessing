import org.junit.Test;

import java.io.StringReader;

import controller.IController;
import controller.ImageProcessorController;
import model.IImageProcessor;
import model.MockImageProcessor;
import view.IView;
import view.TextImageView;

import static org.junit.Assert.assertEquals;

/**
 * Tests a controller.
 */
public class ImageProcessorControllerTest {
  private IController controller;
  private Readable in;
  private Appendable out;
  private Appendable modelLog;

  /**
   * Creates a mock model for the controller to test with.
   *
   * @param commands commands to work with
   */
  private void initCond(String commands) {
    this.modelLog = new StringBuilder();
    IImageProcessor model = new MockImageProcessor(modelLog);
    this.out = new StringBuffer();
    IView view = new TextImageView(model, this.out);
    this.in = new StringReader(commands);
    this.controller = new ImageProcessorController(model, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionModelNull() {
    this.initCond("");
    IImageProcessor model = new MockImageProcessor(modelLog);
    IView view = new TextImageView(model, this.out);
    this.controller = new ImageProcessorController(null, view, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionViewNull() {
    this.initCond("");
    IImageProcessor model = new MockImageProcessor(modelLog);
    IView view = new TextImageView(model, this.out);
    this.controller = new ImageProcessorController(model, null, in);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionInNull() {
    this.initCond("");
    IImageProcessor model = new MockImageProcessor(modelLog);
    IView view = new TextImageView(model, this.out);
    this.controller = new ImageProcessorController(model, view, null);
  }

  @Test
  public void testProcessLoad() {
    this.initCond("load res/koala.ppm koala");
    this.controller.processImage();
    String expected = "Loading; "
            + "Image Details: Width: 1024; Height: 768; Max Value: 255; "
            + "ImageName: koala\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBrighten() {
    this.initCond("brighten 10 koala koala-brighter");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Brighten Image: 10; "
            + "NewImageName: koala-brighter\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessVerticalFlip() {
    this.initCond("vertical-flip koala koala-vertical");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Flip Image: vertical-flip; "
            + "NewImageName: koala-vertical\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessHorizontalFlip() {
    this.initCond("horizontal-flip koala-vertical koala-vertical-horizontal");
    this.controller.processImage();
    String expected = "ImageName: koala-vertical; "
            + "Command: Flip Image: horizontal-flip; "
            + "NewImageName: koala-vertical-horizontal\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessRedComponent() {
    this.initCond("red-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: red-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessGreenComponent() {
    this.initCond("green-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: green-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBlueComponent() {
    this.initCond("blue-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: blue-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessValueComponent() {
    this.initCond("value-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: value-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessLumaComponent() {
    this.initCond("luma-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: luma-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessIntensityComponent() {
    this.initCond("intensity-component koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Focus Component: intensity-component; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSave() {
    this.initCond("save images/koala-brighter.ppm koala-brighter");
    this.controller.processImage();
    String expected = "Saving; "
            + "ImagePath: images/koala-brighter.ppm; "
            + "ImageName: koala-brighter\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBlur() {
    this.initCond("blur koala koala-blurred");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Filter: blur; "
            + "NewImageName: koala-blurred\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSharpen() {
    this.initCond("sharpen koala koala-sharpen");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Filter: sharpen; "
            + "NewImageName: koala-sharpen\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessGreyscale() {
    this.initCond("greyscale koala koala-greyscale");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Color Transformation: greyscale; "
            + "NewImageName: koala-greyscale\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSepia() {
    this.initCond("sepia koala koala-sepia");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Color Transformation: sepia; "
            + "NewImageName: koala-sepia\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessFile() {
    this.initCond("file Script.txt");
    this.controller.processImage();
    String expected = "Loading; "
            + "Image Details: Width: 1200; Height: 650; Max Value: 255; "
            + "ImageName: boston\n"

            + "ImageName: boston; "
            + "Command: Brighten Image: 10; "
            + "NewImageName: bostonBright\n"

            + "ImageName: bostonBright; "
            + "Command: Flip Image: horizontal-flip; "
            + "NewImageName: bostonHFlip\n"

            + "ImageName: bostonHFlip; "
            + "Command: Flip Image: vertical-flip; "
            + "NewImageName: bostonVFlip\n"

            + "ImageName: bostonVFlip; "
            + "Command: Focus Component: red-component; "
            + "NewImageName: bostonRedComp\n"

            + "ImageName: bostonRedComp; "
            + "Command: Filter: blur; "
            + "NewImageName: bostonBlur\n"

            + "Saving; "
            + "ImagePath: res/bostonSuperEdited.jpg; "
            + "ImageName: bostonBlur\n"

            + "ImageName: boston; "
            + "Command: Filter: sharpen; "
            + "NewImageName: bostonSharp\n"

            + "ImageName: bostonSharp; "
            + "Command: Color Transformation: greyscale; "
            + "NewImageName: bostonGrey\n"

            + "Saving; "
            + "ImagePath: res/bostonSharpAndGrey.jpg; "
            + "ImageName: bostonGrey\n"

            + "ImageName: boston; "
            + "Command: Color Transformation: sepia; "
            + "NewImageName: bostonSepia\n"

            + "Saving; "
            + "ImagePath: res/bostonSepia.jpg; "
            + "ImageName: bostonSepia\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessFileAndInputs() {
    this.initCond("sepia koala koala-sepia\nfile Script.txt");
    this.controller.processImage();
    String expected = "ImageName: koala; "
            + "Command: Color Transformation: sepia; "
            + "NewImageName: koala-sepia\n"

            + "Loading; "
            + "Image Details: Width: 1200; Height: 650; Max Value: 255; "
            + "ImageName: boston\n"

            + "ImageName: boston; "
            + "Command: Brighten Image: 10; "
            + "NewImageName: bostonBright\n"

            + "ImageName: bostonBright; "
            + "Command: Flip Image: horizontal-flip; "
            + "NewImageName: bostonHFlip\n"

            + "ImageName: bostonHFlip; "
            + "Command: Flip Image: vertical-flip; "
            + "NewImageName: bostonVFlip\n"

            + "ImageName: bostonVFlip; "
            + "Command: Focus Component: red-component; "
            + "NewImageName: bostonRedComp\n"

            + "ImageName: bostonRedComp; "
            + "Command: Filter: blur; "
            + "NewImageName: bostonBlur\n"

            + "Saving; "
            + "ImagePath: res/bostonSuperEdited.jpg; "
            + "ImageName: bostonBlur\n"

            + "ImageName: boston; "
            + "Command: Filter: sharpen; "
            + "NewImageName: bostonSharp\n"

            + "ImageName: bostonSharp; "
            + "Command: Color Transformation: greyscale; "
            + "NewImageName: bostonGrey\n"

            + "Saving; "
            + "ImagePath: res/bostonSharpAndGrey.jpg; "
            + "ImageName: bostonGrey\n"

            + "ImageName: boston; "
            + "Command: Color Transformation: sepia; "
            + "NewImageName: bostonSepia\n"

            + "Saving; "
            + "ImagePath: res/bostonSepia.jpg; "
            + "ImageName: bostonSepia\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }
}