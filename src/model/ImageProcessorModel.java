package model;

import imageinfo.IImage;
import imageinfo.IImageMaskBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Represents an Image Processor.
 */
public class ImageProcessorModel implements IImageProcessor {

  private final Map<String, IImage> images;

  /**
   * Constructs a new Image Processor model.
   */
  public ImageProcessorModel() {
    images = new HashMap<String, IImage>();
  }

  @Override
  public void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException {

    String mask = String.format("%d, %d, %d, %d", -1, -1 , Integer.MAX_VALUE, Integer.MAX_VALUE);
    this.applyCommand(imageName, (IImageMaskCommand) command, mask, newImageName);
  }

  @Override
  public void applyCommand(String imageName, IImageMaskCommand command, String masks, String newImageName)
          throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException(imageName + " has not been loaded.");
    } else if (command == null) {
      throw new IllegalArgumentException("There must be a command");
    }

    this.images.put(newImageName,
            applyMasks(this.images.get(imageName), masks, command));
  }

  private IImage applyMasks(IImage oldImage, String masks, IImageMaskCommand cm) {
    IImageMaskBuilder builder = oldImage.maskBuilder();
    String[] grids = masks.split(";");
    for (String mask: grids) {
      String[] coords = masks.split(",");

      if (coords.length < 4) {
        continue;
      }

      int xLeft = Integer.parseInt(coords[0]);
      int yTop = Integer.parseInt(coords[1]);
      int xRight = Integer.parseInt(coords[2]);
      int yBottom = Integer.parseInt(coords[3]);

      builder.setModifiable(xLeft, yTop, xRight, yBottom);
    }

    return builder.build(cm);
  }

  @Override
  public <T> T accept(String imageName, Function<IImage, T> func) {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException(imageName + " has not been loaded.");
    } else if (func == null) {
      throw new IllegalArgumentException("There must be a command");
    }

    return func.apply(this.images.get(imageName));
  }

  @Override
  public IImage getImage(String imageName) throws IllegalArgumentException {
    if (!this.hasImage(imageName)) {
      throw new IllegalArgumentException("This image has not been loaded.");
    }
    return this.images.get(imageName);
  }

  @Override
  public boolean hasImage(String imageName) {
    return this.images.containsKey(imageName);
  }

  @Override
  public void loadImage(IImage image, String imageName) {
    this.images.put(imageName, image);
  }

  @Override
  public void saveImage(String imagePath, String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("This image has not been loaded, so it cannot be saved.");
    }
    ImageCommand saver = new SaveImage(imagePath);
    saver.apply(this.images.get(imageName));
  }
}
