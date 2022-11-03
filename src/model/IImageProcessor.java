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
}
