package model;

import java.util.HashMap;
import java.util.Map;

import imageInfo.BasicImage;
import imageInfo.IImage;

/**
 * Represents an Image Processor.
 */
public class ImageProcessorModel implements IImageProcessor {

  private Map<String, IImage> images;

  /**
   * Constructs a new Image Processor model.
   */
  public ImageProcessorModel() {
    images = new HashMap<String, IImage>();
  }

  @Override
  public void saveImagePathAndName(String imagePath, String imageName) {
    //?? save to a specific place ??
  }

  @Override
  public void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException {

    this.images.put(newImageName, command.apply(this.images.get(imageName)));
  }

  @Override
  public IImage getImage(String imageName) {
    return new BasicImage(this.images.get(imageName));
  }
}
