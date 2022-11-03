package model;

import imageInfo.IImage;

/**
 * Represents the state of an Image Processor.
 */
public interface IImageProcessorState {
  /**
   * Gets the image with that name.
   *
   * @param imageName the image with that name.
   */
  IImage getImage(String imageName);
}
