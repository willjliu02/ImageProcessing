package controller;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.ImageUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Function;

import javax.swing.text.View;

import model.Blur;
import model.Brighten;
import model.FocusBlue;
import model.FocusGreen;
import model.FocusIntensity;
import model.FocusLuma;
import model.FocusRed;
import model.FocusValue;
import model.Greyscale;
import model.HorizontalFlip;
import model.IImageProcessor;
import model.ImageCommand;
import model.Sepia;
import model.Sharpen;
import model.VerticalFlip;
import view.IGUIView;
import view.IView;
import view.ViewEvent;

public class GUIController implements IController, ViewListener {
  private final IGUIView view;
  private final IImageProcessor model;
  private final Map<ViewEvent, Function<Void, ImageCommand>> commands;

  public GUIController(IImageProcessor model, IGUIView view) {
    try  {
      this.model = Objects.requireNonNull(model);
      this.view = Objects.requireNonNull(view);
    } catch (NullPointerException e)  {
      throw new IllegalStateException("Unable to print to the file.");
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
  }

  @Override
  public void processImage() throws IllegalStateException {
    this.view.makeVisible();
    this.view.addListener(this);
  }

  @Override
  public void listenTo(ViewEvent e) {
    String currentImage = this.view.getDisplayedImage();
    switch (e) {
      case LOAD:
        ImageUtil processImage = new ImageUtil(this.view.getLoadPath());
        IImage image = new BasicImage(processImage.getWidth(),
                processImage.getHeight(),
                processImage.getMaxValue(),
                processImage.getPixels());
        this.model.loadImage(image, currentImage);
        break;
      case SAVE:
        this.model.saveImage(this.view.getSavePath(), currentImage);
        break;
      default:
        this.model.applyCommand(currentImage,
                this.commands.get(e).apply(null),
                this.getNewImageName(currentImage, e));
        break;
    }
  }

  private String getNewImageName(String currentImage, ViewEvent e) {
    return currentImage + "-" + e.toString().toLowerCase();
  }

}
