import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
    if (args.length > 0) {
      IImageProcessor model = new ImageProcessorModel();
      IView view = new TextImageView(model);
      Readable input = new InputStreamReader(System.in);
      for (int i = 0; i < args.length; i++) {
        if (args[i].equals("-file") && i + 1 < args.length && i - 2 >= 0) {
          checkJar(args, i);
          File file = new File(args[i + 1]);
          try {
            input = new FileReader(file);
          } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found.");
          }
          IController controller = new ImageProcessorController(model, view, input);
          controller.processImage();
        } else if (args[i].equals("-text")) {
          System.out.println(args[i - 2]);
          System.out.println(args[i - 1]);
          checkJar(args, i);
          IController controller = new ImageProcessorController(model, view, input);
          controller.processImage();
        }
      }
      checkJar(args, 3);
      guiView();
    } else {
      guiView();
    }
  }

  private static void guiView() {
    IImageProcessor model = new ImageProcessorModel();
    IGUIView view = new GUIView();
    IController controller = new GUIController(model, view);
    controller.processImage();
  }

  private static void checkJar(String[] args, int i) {
    if (!(args[i - 2].equals("-jar") && args[i - 1].equals("Program.jar"))) {
      throw new IllegalArgumentException("Command line arguments are not supported.");
    }
  }
}