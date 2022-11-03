package model;

import imageInfo.IImage;
import imageInfo.IImageState;
import imageInfo.IPixel;

/**
 * Represents the state of an Image Processor.
 */
public interface IImageProcessorState {
  /**
   * Gets the image with that name.
   * @param imageName the image with that name.
   */
  public IImage getImage(String imageName);
}
