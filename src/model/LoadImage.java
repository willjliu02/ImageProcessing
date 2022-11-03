package model;

import imageInfo.BasicImage;
import imageInfo.IImage;
import imageInfo.ImageUtil;

/**
 * Loads an image into a format that can be added to the image directory.
 */
public class LoadImage implements ImageCommand {
  String imagePath;

  /**
   * Creates a command for loading an image
   *
   * @param imagePath path to access the image from.
   */
  public LoadImage(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public IImage apply(IImage currentImage) {
    ImageUtil processImage = new ImageUtil(imagePath);
    IImage image = new BasicImage(processImage.getWidth(),
            processImage.getHeight(),
            processImage.getMaxValue(),
            processImage.getPixels());
    return image;
  }
}
