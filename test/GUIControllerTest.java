import org.junit.Test;

import controller.GUIController;
import controller.IController;
import controller.ViewListener;
import model.IImageProcessor;
import model.MockGUIView;
import model.MockImageProcessor;
import view.GUIView;
import view.IGUIView;
import view.ViewEvent;

import static org.junit.Assert.assertEquals;

/**
 * Tests the GUIController.
 */
public class GUIControllerTest {
  private IController controller;
  private Appendable modelLog;

  /**
   * Creates a mock model for the controller to test with.
   *
   * @param command commands to work with
   */
  private void initCond(ViewEvent command) {
    this.modelLog = new StringBuilder();
    IImageProcessor model = new MockImageProcessor(modelLog);
    IGUIView view = new MockGUIView("res/boston.jpg", "images/koala-brighter.ppm",
            "10", "red", "koala", "3, 3, 3, 3");
    this.controller = new GUIController(model, view);
    ((ViewListener)this.controller).listenTo(command);
  }

  private void initCond(String focusComp) {
    this.modelLog = new StringBuilder();
    IImageProcessor model = new MockImageProcessor(modelLog);
    IGUIView view = new MockGUIView("res/koala.ppm", "images/koala-brighter.ppm",
            "10", focusComp, "koala", "3, 3, 3, 3");
    this.controller = new GUIController(model, view);
    ((ViewListener)this.controller).listenTo(ViewEvent.FOCUS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionModelNull() {
    IImageProcessor model = new MockImageProcessor(modelLog);
    IGUIView view = new GUIView();
    this.controller = new GUIController(null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionViewNull() {
    IImageProcessor model = new MockImageProcessor(modelLog);
    IGUIView view = new GUIView();
    this.controller = new GUIController(model, null);
  }

  @Test
  public void testProcessLoad() {
    this.initCond(ViewEvent.LOAD);
    
    String expected = "Loading; "
            + "Image Details: Width: 1200; Height: 650; Max Value: 255; "
            + "ImageName: boston\n"
            + "ImageName: boston; "
            + "Command: Histogram: Red\n"
            + "ImageName: boston; "
            + "Command: Histogram: Green\n"
            + "ImageName: boston; "
            + "Command: Histogram: Blue\n"
            + "ImageName: boston; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBrighten() {
    this.initCond(ViewEvent.BRIGHTEN);
    
    String expected = "ImageName: koala; "
            + "Command: Brighten Image: 10; "
            + "NewImageName: koala-brighten\n"
            + "ImageName: koala-brighten; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-brighten; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-brighten; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-brighten; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessVerticalFlip() {
    this.initCond(ViewEvent.VERTICALFLIP);
    
    String expected = "ImageName: koala; "
            + "Command: Flip Image: vertical-flip; "
            + "NewImageName: koala-verticalflip\n"
            + "ImageName: koala-verticalflip; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-verticalflip; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-verticalflip; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-verticalflip; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessHorizontalFlip() {
    this.initCond(ViewEvent.HORIZONTALFLIP);
    
    String expected = "ImageName: koala; "
            + "Command: Flip Image: horizontal-flip; "
            + "NewImageName: koala-horizontalflip\n"
            + "ImageName: koala-horizontalflip; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-horizontalflip; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-horizontalflip; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-horizontalflip; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessRedComponent1() {
    this.initCond(ViewEvent.FOCUSRED);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: red-component; "
            + "NewImageName: koala-focusred\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessRedComponent2() {
    this.initCond("red");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: red-component; "
            + "NewImageName: koala-focusred\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusred; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessGreenComponent1() {
    this.initCond(ViewEvent.FOCUSGREEN);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: green-component; "
            + "NewImageName: koala-focusgreen\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessGreenComponent2() {
    this.initCond("green");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: green-component; "
            + "NewImageName: koala-focusgreen\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusgreen; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBlueComponent1() {
    this.initCond(ViewEvent.FOCUSBLUE);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: blue-component; "
            + "NewImageName: koala-focusblue\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBlueComponent2() {
    this.initCond("blue");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: blue-component; "
            + "NewImageName: koala-focusblue\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusblue; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessValueComponent1() {
    this.initCond(ViewEvent.FOCUSVALUE);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: value-component; "
            + "NewImageName: koala-focusvalue\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessValueComponent2() {
    this.initCond("value");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: value-component; "
            + "NewImageName: koala-focusvalue\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusvalue; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessLumaComponent1() {
    this.initCond(ViewEvent.FOCUSLUMA);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: luma-component; "
            + "NewImageName: koala-focusluma\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessLumaComponent2() {
    this.initCond("luma");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: luma-component; "
            + "NewImageName: koala-focusluma\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusluma; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessIntensityComponent1() {
    this.initCond(ViewEvent.FOCUSINTENSITY);
    
    String expected = "ImageName: koala; "
            + "Command: Focus Component: intensity-component; "
            + "NewImageName: koala-focusintensity\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessIntensityComponent2() {
    this.initCond("intensity");

    String expected = "ImageName: koala; "
            + "Command: Focus Component: intensity-component; "
            + "NewImageName: koala-focusintensity\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-focusintensity; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSave() {
    this.initCond(ViewEvent.SAVE);
    
    String expected = "Saving; "
            + "ImagePath: images/koala-brighter.ppm; "
            + "ImageName: koala\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessBlur() {
    this.initCond(ViewEvent.BLUR);
    
    String expected = "ImageName: koala; "
            + "Command: Filter: blur; "
            + "NewImageName: koala-blur\n"
            + "ImageName: koala-blur; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-blur; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-blur; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-blur; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSharpen() {
    this.initCond(ViewEvent.SHARPEN);
    
    String expected = "ImageName: koala; "
            + "Command: Filter: sharpen; "
            + "NewImageName: koala-sharpen\n"
            + "ImageName: koala-sharpen; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-sharpen; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-sharpen; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-sharpen; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessGreyscale() {
    this.initCond(ViewEvent.GREYSCALE);
    
    String expected = "ImageName: koala; "
            + "Command: Color Transformation: greyscale; "
            + "NewImageName: koala-greyscale\n"
            + "ImageName: koala-greyscale; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-greyscale; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-greyscale; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-greyscale; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }

  @Test
  public void testProcessSepia() {
    this.initCond(ViewEvent.SEPIA);
    
    String expected = "ImageName: koala; "
            + "Command: Color Transformation: sepia; "
            + "NewImageName: koala-sepia\n"
            + "ImageName: koala-sepia; "
            + "Command: Histogram: Red\n"
            + "ImageName: koala-sepia; "
            + "Command: Histogram: Green\n"
            + "ImageName: koala-sepia; "
            + "Command: Histogram: Blue\n"
            + "ImageName: koala-sepia; "
            + "Command: Histogram: Value\n";
    String result = this.modelLog.toString();

    assertEquals(expected, result);
  }
}