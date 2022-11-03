package model;

/**
 * Represents an ImageProcessor.
 */
public interface IImageProcessor extends IImageProcessorState {

  /**
   * Applies the command onto the desired IImage and saves it under the newImageName in the map.
   *
   * @param imageName    the desired IImage nap
   * @param command      the desired command
   * @param newImageName the newImageName
   * @throws IllegalArgumentException the IllegalArgumentException
   */
  void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException;

  /**
   * Loads an image with the given path into the map with the given name.
   * @param imagePath the path of the image
   * @param imageName the name of the image in the map
   */
  void loadImage(String imagePath, String imageName);

  /**
   * Saves an image with the given name in the map to the given path.
   * @param imagePath the path of the image
   * @param imageName the name of the image in the map
   */
  void saveImage(String imagePath, String imageName);
}
