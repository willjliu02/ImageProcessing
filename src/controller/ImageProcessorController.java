package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import model.Brighten;
import model.FlipImage;
import model.FocusComponent;
import model.IImageProcessor;
import model.ImageCommand;
import model.LoadImage;
import model.SaveImage;
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
    this.commands.put("load", cmd -> new LoadImage(cmd));
    this.commands.put("brighten", inc -> new Brighten(Integer.parseInt(inc)));
    this.commands.put("horizontal-flip", flip -> new FlipImage(flip));
    this.commands.put("vertical-flip", flip -> new FlipImage(flip));
    this.commands.put("luma-component", comp -> new FocusComponent(comp));
    this.commands.put("intensity-component", comp -> new FocusComponent(comp));
    this.commands.put("red-component", comp -> new FocusComponent(comp));
    this.commands.put("green-component", comp -> new FocusComponent(comp));
    this.commands.put("value-component", comp -> new FocusComponent(comp));
    this.commands.put("blue-component", comp -> new FocusComponent(comp));
    this.commands.put("save", file -> new SaveImage(file));
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
          cmd = func.apply(line[1]);
        }
        this.model.applyCommand(imageName, cmd, newImageName);
      }
    }
  }
}
