package controller;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.ImageUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import model.BlueHistogram;
import model.Blur;
import model.Brighten;
import model.FocusBlue;
import model.FocusGreen;
import model.FocusIntensity;
import model.FocusLuma;
import model.FocusRed;
import model.FocusValue;
import model.GreenHistogram;
import model.Greyscale;
import model.HorizontalFlip;
import model.IImageProcessor;
import model.ImageCommand;
import model.RedHistogram;
import model.Sepia;
import model.Sharpen;
import model.ValueHistogram;
import model.VerticalFlip;
import view.IGUIView;
import view.IView;
import view.ViewEvent;

public class GUIController implements IController, ViewListener {
  private final IGUIView view;
  private final IImageProcessor model;
  private final Map<ViewEvent, Function<Void, ImageCommand>> commands;

  private final Map<String, ViewEvent> focusEvent;

  /**
   * Constructs a controller for a user interface processor.
   * @param model model that represents the processor
   * @param view view that represents the processor
   */
  public GUIController(IImageProcessor model, IGUIView view) {
    try {
      this.model = Objects.requireNonNull(model);
      this.view = Objects.requireNonNull(view);
    } catch (NullPointerException e) {
      //throw new IllegalArgumentException("Unable to print to the file.");
      throw new IllegalArgumentException("Error in accepting elements.");
    }

    this.commands = new HashMap<ViewEvent, Function<Void, ImageCommand>>();
    try {
      this.commands.put(ViewEvent.BRIGHTEN, (Void v) -> new Brighten(this.view.getBrightenAmt()));
    } catch (NumberFormatException e) {
      writeMessage("This is not a valid brighten increment. Please try again.");
    }
    this.commands.put(ViewEvent.HORIZONTALFLIP, (Void v) -> new HorizontalFlip());
    this.commands.put(ViewEvent.VERTICALFLIP, (Void v) -> new VerticalFlip());
    this.commands.put(ViewEvent.FOCUSLUMA, (Void v) -> new FocusLuma());
    this.commands.put(ViewEvent.FOCUSINTENSITY, (Void v) -> new FocusIntensity());
    this.commands.put(ViewEvent.FOCUSRED, (Void v) -> new FocusRed());
    this.commands.put(ViewEvent.FOCUSGREEN, (Void v) -> new FocusGreen());
    this.commands.put(ViewEvent.FOCUSVALUE, (Void v) -> new FocusValue());
    this.commands.put(ViewEvent.FOCUSBLUE, (Void v) -> new FocusBlue());
    this.commands.put(ViewEvent.BLUR, (Void v) -> new Blur());
    this.commands.put(ViewEvent.SHARPEN, (Void v) -> new Sharpen());
    this.commands.put(ViewEvent.GREYSCALE, (Void v) -> new Greyscale());
    this.commands.put(ViewEvent.SEPIA, (Void v) -> new Sepia());

    this.focusEvent = new HashMap<String, ViewEvent>();
    this.focusEvent.put("blue", ViewEvent.FOCUSBLUE);
    this.focusEvent.put("green", ViewEvent.FOCUSGREEN);
    this.focusEvent.put("intensity", ViewEvent.FOCUSINTENSITY);
    this.focusEvent.put("luma", ViewEvent.FOCUSLUMA);
    this.focusEvent.put("red", ViewEvent.FOCUSRED);
    this.focusEvent.put("value", ViewEvent.FOCUSVALUE);
  }

  /**
   * Sets up the user interface.
   * @throws IllegalStateException if this isn't possible
   */
  @Override
  public void processImage() throws IllegalStateException {
    this.view.makeVisible();
    this.view.addListener(this);
  }

  /**
   * Listens to commands and updates the processor info and view accordingly.
   * @param e image edit that is being tried
   */
  @Override
  public void listenTo(ViewEvent e) {
    String currentImage = this.view.getDisplayedImage();
    String newImage;

    if (e == ViewEvent.FOCUS) {
      e = this.getFocusCommand();
    }

    switch (e) {
      case LOAD:
        String path = this.view.getLoadPath();

        int lastPeriod = path.lastIndexOf(".");
        int lastSlash = path.lastIndexOf("/");
        if (lastPeriod == -1) {
          throw new IllegalArgumentException("Illegal Path.");
        }
        newImage = path.substring(lastSlash + 1, lastPeriod);

        ImageUtil processImage = new ImageUtil(path);
        IImage image = new BasicImage(processImage.getWidth(),
                processImage.getHeight(),
                processImage.getMaxValue(),
                processImage.getPixels());
        this.model.loadImage(image, newImage);
        break;
      case SAVE:
        this.model.saveImage(this.view.getSavePath(), currentImage);
        return;
      default:
        newImage = this.getNewImageName(currentImage, e);
        try {
          this.model.applyCommand(currentImage,
                  this.commands.get(e).apply(null),
                  newImage);
        } catch (IllegalArgumentException ex) {
          writeMessage("Error in editing.");
        }
        break;
    }

    this.view.updateDisplayedImage(newImage);
    this.view.refresh(this.model.getImage(newImage), this.grabHistograms(newImage));
    writeMessage("Request processed!");
  }

  private List<List<Integer>> grabHistograms(String imageName) {
    List<List<Integer>> histograms = new ArrayList<List<Integer>>();
    histograms.add(this.model.accept(imageName, new RedHistogram()));
    histograms.add(this.model.accept(imageName, new GreenHistogram()));
    histograms.add(this.model.accept(imageName, new BlueHistogram()));
    histograms.add(this.model.accept(imageName, new ValueHistogram()));

    return histograms;
  }

  private ViewEvent getFocusCommand() {
    String comp = this.view.getFocusComp().toLowerCase();
    if (!this.focusEvent.containsKey(comp)) {
      //throw new IllegalArgumentException("This command does not exist");
      writeMessage("This is not a viable command. Please try again. ");
    }
    return this.focusEvent.get(comp);
  }

  private String getNewImageName(String currentImage, ViewEvent e) {
    return currentImage + "-" + e.toString().toLowerCase();
  }

  private void writeMessage(String message) {
    try {
      this.view.renderMessage(message);
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write message to the view.");
    }
  }

}
