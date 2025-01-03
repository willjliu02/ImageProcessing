package imageinfo;

import model.ImageCommand;

/**
 * Represents an image.
 */
public interface IImage extends IImageState {

  /**
   * Accepts the application of an action or command on the image.
   *
   * @param command the command that will be applied onto it
   */
  IImage acceptCommand(ImageCommand command);

  /**
   * Retrieves the builder of this type of ImageMask.
   * @return IImageMaskBuilder that will create a mask for this image.
   */
  IImageMaskBuilder maskBuilder();
}
