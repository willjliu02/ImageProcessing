package controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import imageInfo.Brighten;
import model.FlipImage;
import model.FocusComponent;
import model.IImageProcessor;
import model.ImageCommand;
import view.IView;

import static java.util.Objects.nonNull;

public class ImageProcessorController implements IController {

  private Map<String, Function<String, ImageCommand>> commands;
  private IImageProcessor model;
  private IView view;
  private Scanner scan;

  public ImageProcessorController(IImageProcessor model, IView view, Readable in) {
    try {
      this.model = Objects.requireNonNull(model);
      this.view = Objects.requireNonNull(view);
      this.scan = new Scanner(Objects.requireNonNull(in));
    } catch (NullPointerException e) {
      throw new IllegalArgumentException("The arguments cannot be null.");
    }
    this.commands = new HashMap<String, Function<String, ImageCommand>>();
    this.commands.put("brighten", inc -> new Brighten(Integer.parseInt(inc)));
    this.commands.put("horizontal-flip", flip -> new FlipImage("horizontal-flip"));
    this.commands.put("vertical-flip", flip -> new FlipImage("vertical-flip"));
    this.commands.put("luma-component", comp -> new FocusComponent("luma-component"));
    this.commands.put("intensity-component", comp -> new FocusComponent("intensity-component"));
    this.commands.put("red-component", comp -> new FocusComponent("red-component"));
    this.commands.put("green-component", comp -> new FocusComponent("green-component"));
    this.commands.put("value-component", comp -> new FocusComponent("value-component"));
    this.commands.put("blue-component", comp -> new FocusComponent("blue-component"));
  }

  private void writeMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write message to the view.");
    }
  }

  @Override
  public void processImage() throws IllegalStateException {
    while (scan.hasNext()) {
      String nextCommand = scan.nextLine();
      String[] line = nextCommand.split(" ");

      Function<String, ImageCommand> func = this.commands.getOrDefault(line[0].toLowerCase(), null);
      if (func == null) {
        writeMessage("This is not a viable command. \nPlease enter another one:");
      } else {
        String imageName;
        String newImageName;
        ImageCommand cmd;
        if (line.length > 3) {
          imageName = line[2];
          newImageName = line[3];
          cmd = func.apply(line[1]);
        } else {
          imageName = line[1];
          newImageName = line[2];
          cmd = func.apply("");
        }
        this.model.applyCommand(imageName, cmd, newImageName);
      }
    }
  }
}
