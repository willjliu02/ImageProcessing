package model;

import java.util.HashMap;
import java.util.Map;

import imageInfo.BasicImage;
import imageInfo.IImageState;
import imageInfo.IPixel;
import imageInfo.IImage;
import imageInfo.ImageUtil;
import imageInfo.Pixel;

public class ImageProcessorModel implements IImageProcessor {

  private Map<String, IImage> images;

  public ImageProcessorModel() {
    images = new HashMap<String, IImage>();
  }

  @Override
  public void loadImagePathAndName(String imagePath, String imageName) {
    ImageUtil processImage = new ImageUtil(imagePath);
    IImage image = new BasicImage(processImage.getWidth(),
                                  processImage.getHeight(),
                                  processImage.getMaxValue(),
                                  processImage.getPixels());
    images.put(imageName, image);
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
