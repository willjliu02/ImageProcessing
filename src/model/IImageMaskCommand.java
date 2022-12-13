package model;

import imageinfo.IImageMask;

/**
 * A command that accepts a mask to allow only partial changes.
 */
public interface IImageMaskCommand extends ImageCommand {
  /**
   * Sets 1 mask to a command.
   * @param mask a mask that defines modifiability
   * @throws IllegalStateException if more than 1 mask is set
   *                               (ie it is set and attempted to be changed)
   */
  public void setMask(IImageMask mask) throws IllegalStateException;
}
