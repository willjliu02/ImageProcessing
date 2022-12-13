package imageinfo;

import model.IImageMaskCommand;
import model.ImageCommand;

/**
 * Represents a mask to be applied to an image.
 */
public interface IImageMaskBuilder {
  /**
   * Sets a rectangular area to be modified when given a command is applied.
   * @param xLeft the left index of the rectangle
   * @param yTop the top index of the rectangle
   * @param xRight the right index of the rectangle
   * @param yBottom the bottom index of the rectangle
   * @return a reference to the current IImageMaskBuilder
   */
  IImageMaskBuilder setModifiable(int xLeft, int yTop, int xRight, int yBottom);

  /**
   * Sets a rectangular area to be unmodified when given a command is applied.
   * @param xLeft the left index of the rectangle
   * @param yTop the top index of the rectangle
   * @param xRight the right index of the rectangle
   * @param yBottom the bottom index of the rectangle
   * @return a reference to the current IImageMaskBuilder
   */
  IImageMaskBuilder setUnmodifiable(int xLeft, int yTop, int xRight, int yBottom);

  /**
   * Builds the desired mask and applies that to the mask and the Image.
   * @param command the command that will be applied it
   * @return the modified image
   */
  IImage build(IImageMaskCommand command);
}
