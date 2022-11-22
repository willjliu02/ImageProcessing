import java.io.InputStreamReader;

import controller.GUIController;
import controller.IController;
import controller.ImageProcessorController;
import model.IImageProcessor;
import model.ImageProcessorModel;
import view.GUIView;
import view.IGUIView;
import view.IView;
import view.TextImageView;

/**
 * Runs the Image Processor.
 */
public class Main {

  /**
   * Runs the program.
   *
   * @param args run arguments.
   */
  public static void main(String[] args) {
    IImageProcessor model = new ImageProcessorModel();
    //IView view = new TextImageView(model);
    IGUIView view = new GUIView();
    Readable input = new InputStreamReader(System.in);
    //IController controller = new ImageProcessorController(model, view, input);
    IController controller = new GUIController(model, view);
    controller.processImage();
  }
}