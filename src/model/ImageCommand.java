package model;

import imageinfo.IImage;

/**
 * Applies an action onto an image.
 */
public interface ImageCommand {

  /**
   * Applies the action on the image.
   *
   * @param currentImage the image that will be acted on.
   */
  IImage apply(IImage currentImage);
}
