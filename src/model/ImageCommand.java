package model;

import imageInfo.IImage;

/**
 * Applies an action onto an image.
 */
public interface ImageCommand {

  /**
   * Applies the action on the image.
   * @param currentImage the image that will be acted on.
   */
  public IImage apply(IImage currentImage);
}