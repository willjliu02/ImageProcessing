package controller;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.ImageUtil;

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

  public GUIController(IImageProcessor model, IGUIView view) {
    try  {
      this.model = Objects.requireNonNull(model);
      this.view = Objects.requireNonNull(view);
    } catch (NullPointerException e)  {
      throw new IllegalArgumentException("Unable to print to the file.");
    }

    this.commands = new HashMap<ViewEvent, Function<Void, ImageCommand>>();
    this.commands.put(ViewEvent.BRIGHTEN, (Void v) -> new Brighten(this.view.getBrightenAmt()));
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

  @Override
  public void processImage() throws IllegalStateException {
    this.view.makeVisible();
    this.view.addListener(this);
  }

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
        this.model.applyCommand(currentImage,
                this.commands.get(e).apply(null),
                newImage);
        break;
    }

    this.view.updateDisplayedImage(newImage);
    this.view.refresh(this.model.getImage(newImage), this.grabHistograms(newImage));
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
      throw new IllegalArgumentException("This command does not exist");
    }
    return this.focusEvent.get(comp);
  }

  private String getNewImageName(String currentImage, ViewEvent e) {
    return currentImage + "-" + e.toString().toLowerCase();
  }

}
