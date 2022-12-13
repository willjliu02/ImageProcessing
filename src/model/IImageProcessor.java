package model;

import imageinfo.IImage;

import java.util.function.Function;

/**
 * Represents an ImageProcessor.
 */
public interface IImageProcessor {

  /**
   * Applies the command onto the desired IImage and saves it under the newImageName in the map.
   *
   * @param imageName    the desired IImage nap
   * @param command      the desired command
   * @param newImageName the newImageName
   * @throws IllegalArgumentException if imageName has not been loaded into the map
   */
  void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException;

  /** Applies the command onto the desired IImage and saves it under the newImageName in the map.
   *
   * @param imageName    the desired IImage nap
   * @param command      the desired command
   * @param masks        the masks for the image
   * @param newImageName the newImageName
   * @throws IllegalArgumentException if imageName has not been loaded into the map
   */
  void applyCommand(String imageName, IImageMaskCommand command, String masks, String newImageName)
          throws IllegalArgumentException;

  /**
   * Accepts the application of a command onto it.
   * @param imageName the name of the image to be applied to.
   * @param func an action that will be applied on said image.
   * @return Some desired output.
   * @param <T> Any type
   */
  <T> T accept(String imageName, Function<IImage, T> func);

  /**
   * Retrieves the image with that desires name from the processor.
   *
   * @param imageName the name of the image that is to be retrieved
   * @return IImage the image that is desired
   * @throws IllegalArgumentException thrown if the imageName has not been loaded
   */
  IImage getImage(String imageName) throws IllegalArgumentException;

  /**
   * Answers the question has the image been loaded.
   *
   * @param imageName the image that is to be retrieved
   * @return boolean if the image is loaded in the processor
   */
  boolean hasImage(String imageName);

  /**
   * Loads an image with the given path into the map with the given name.
   *
   * @param image     image to load
   * @param imageName the name of the image in the map
   */
  void loadImage(IImage image, String imageName);

  /**
   * Saves an image with the given name in the map to the given path.
   *
   * @param imagePath the path of the image
   * @param imageName the name of the image in the map
   * @throws IllegalArgumentException if the file that is trying to be saved has not been loaded.
   */
  void saveImage(String imagePath, String imageName) throws IllegalArgumentException;
}
