package model;

import java.util.HashMap;
import java.util.Map;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.ImageUtil;

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

    this.images.put(newImageName,
                    command.apply(this.images.getOrDefault(imageName, null)));
  }

  @Override
  public void loadImage(String imagePath, String imageName) {
    ImageUtil processImage = new ImageUtil(imagePath);
    IImage image = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    this.images.put(imageName, image);
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("This image has not been loaded, so it cannot be saved.");
    }
    ImageCommand saver = new SaveImage(imagePath);
    saver.apply(this.images.get(imageName));
  }

  @Override
  public IImage getImage(String imageName) {
    return new BasicImage(this.images.get(imageName));
  }
}
