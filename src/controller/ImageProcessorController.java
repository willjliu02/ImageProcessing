package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import model.Brighten;
import model.ColorTransformation;
import model.Filter;
import model.FlipImage;
import model.FocusComponent;
import model.IImageProcessor;
import model.ImageCommand;
import view.IView;

/**
 * Represents a controller to interact with the ImageProcessor and the view.
 */
public class ImageProcessorController implements IController {

  private final Map<String, Function<String, ImageCommand>> commands;
  private final IImageProcessor model;
  private final IView view;
  private Scanner scan;

  /**
   * Constructs a controller to interact with the IImageProcessor.
   *
   * @param model the ImageProcessor
   * @param view  the View
   * @param in    the Readable
   */
  public ImageProcessorController(IImageProcessor model, IView view, Readable in) {
    try {
      this.model = Objects.requireNonNull(model);
      this.view = Objects.requireNonNull(view);
      this.scan = new Scanner(Objects.requireNonNull(in));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }

    this.commands = new HashMap<String, Function<String, ImageCommand>>();
    this.commands.put("brighten", inc -> new Brighten(inc));
    this.commands.put("horizontal-flip", flip -> new FlipImage("horizontal-flip"));
    this.commands.put("vertical-flip", flip -> new FlipImage("vertical-flip"));
    this.commands.put("luma-component", comp -> new FocusComponent("luma-component"));
    this.commands.put("intensity-component", comp -> new FocusComponent("intensity-component"));
    this.commands.put("red-component", comp -> new FocusComponent("red-component"));
    this.commands.put("green-component", comp -> new FocusComponent("green-component"));
    this.commands.put("value-component", comp -> new FocusComponent("value-component"));
    this.commands.put("blue-component", comp -> new FocusComponent("blue-component"));
    this.commands.put("blur", filter -> new Filter("blur"));
    this.commands.put("sharpen", filter -> new Filter("sharpen"));
    this.commands.put("greyscale", transform -> new ColorTransformation("greyscale"));
    this.commands.put("sepia", transform -> new ColorTransformation("sepia"));
  }

  private void writeMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write message to the view.");
    }
  }

  private void applyCommand(String[] command) {
    Function<String, ImageCommand> func = this.commands.getOrDefault(command[0].toLowerCase(),
            null);
    if (func == null) {
      writeMessage("This is not a viable command. \nPlease enter another one:");
    } else {
      String imageName;
      String newImageName;
      ImageCommand cmd = func.apply(command[1]);
      if (command.length > 3) {
        imageName = command[2];
        newImageName = command[3];
      } else {
        imageName = command[1];
        newImageName = command[2];
      }
      this.model.applyCommand(imageName, cmd, newImageName);
    }
  }

  @Override
  public void processImage() throws IllegalStateException {
    this.printWelcomeMessage();
    while (scan.hasNext()) {
      String nextCommand = scan.nextLine();
      String[] line = nextCommand.split(" ");

      if(line[0].equalsIgnoreCase("file")) {
        if(this.checkInputs(line, 2)) {
          continue;
        }
      }
      else {
        if(this.checkInputs(line, 3)) {
          continue;
        }
      }

      switch (line[0]) {
        case "file":
          try {
            File file = new File(line[1]);
            scan = new Scanner(file);
          }
          catch(FileNotFoundException e) {
            try {
              this.view.renderMessage("File not found, please retry input!");
            } catch (IOException exc) {
              throw new IllegalStateException("Unable to print to the file.");
            }
          }
          break;

        case "load":
          this.model.loadImage(line[1], line[2]);
          break;
        case "save":
          this.model.saveImage(line[1], line[2]);
          break;
        default:
          try {
            this.applyCommand(line);
          } catch (IllegalArgumentException e) {
            // okay to catch general exception??
            System.out.println("Error: " + e.getMessage());
            continue;
          }
      }
      this.writeMessage("Request processed!\n");
      //?? MAYBE ADD A CONFIRMATION THAT THE COMMAND WAS PROCESSED ??
      // added this line but it will print regardless of the command works or not.
    }
  }

  /**
   * Checks if the # of inputs is valid.
   * @param line the array of inputs
   * @param amt the number of inputs needed
   * @return true if the inputs are invalid, and false otherwise
   */
  private boolean checkInputs(String[] line, int amt) {
    if (line.length < amt) {
      try {
        this.view.renderMessage("Insufficient inputs.\n");
      } catch (IOException e) {
        throw new IllegalStateException("Unable to print to the file.");
      }
      return true;
    }
    return false;
  }

  /**
   * Prints the welcome message when the user starts the processor.
   */
  // MOVE TO INTERFACE ?
  private void printWelcomeMessage() {
    this.writeMessage("Welcome to Image Processor! Current accepted commands are:\n" +
            "load filePath fileName\n" +
            "brighten 'incrementToBrightenBy' fileName newFileName\n" +
            "horizontalFlip fileName newFileName\n" +
            "verticalFlip fileName newFileName\n" +
            "'componentToMakeGreyScaleFrom'-component fileName newFileName\n" +
            "blur fileName newFileName\n" +
            "sharpen fileName newFileName\n" +
            "greyscale fileName newFileName\n" +
            "sepia fileName newFileName\n" +
            "save filePath fileName\n\n");
  }
}