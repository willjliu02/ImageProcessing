package model;

/**
 * Represents an ImageProcessor.
 */
public interface IModel {

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
   * Brings out a certain component in the image.
   * @param component Component to bring out the image.
   * @param imageName Image to edit
   * @param destImageName Image name to save the edited image as
   */
  void focusComponent(String component, String imageName, String destImageName);

  /**
   * Flips the image horizontally.
   * @param imageName Image to edit
   * @param destImageName Image name to save the edited image as
   */
  void horizontalFlip(String imageName, String destImageName);

  /**
   * Flips the image vertically.
   * @param imageName Image to edit
   * @param destImageName Image name to save the edited image as
   */
  void verticalFlip(String imageName, String destImageName);

  /**
   * Brightens the image to the specified value.
   * @param increment to brighten the value to
   * @param imageName Image to edit
   * @param destImageName Image name to save the edited image as
   */
  void brightenImage(int increment, String imageName, String destImageName);
}
