package model;

import imageInfo.IPixel;

/**
 * Represents an ImageProcessor.
 */
public interface IImageProcessor extends IImageProcessorState {

  /**
   * Loads an image from the path and saves it with the given name.
   * @param imagePath to locate the image
   * @param imageName name to save the image as
   */
  void loadImagePathAndName(String imagePath, String imageName);

  /**
   * Saves an image to the path with the given name.
   * @param imagePath to save the image in
   * @param imageName name to save the image as
   */
  void saveImagePathAndName(String imagePath, String imageName);
}
