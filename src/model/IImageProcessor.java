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

  /**
   * Applies the command onto the desired IImage and saves it under the newImageName in the map.
   * @param imageName the desired IImage nap
   * @param command the desired command
   * @param newImageName the newImageName
   * @throws IllegalArgumentException the IllegalArgumentException
   */
  public void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException;
}
