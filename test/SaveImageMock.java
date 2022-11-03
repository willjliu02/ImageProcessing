package model;

import java.io.FileWriter;
import java.io.IOException;

import imageInfo.IImage;
import imageInfo.IPixel;

/**
 * Represents a command that saves an IImage.
 */
public class SaveImageMock implements ImageCommand {
  private final String imagePath;

  public SaveImageMock(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public IImage apply(IImage currentImage) {
      throw new IllegalStateException("Unable to write to file.");
  }

  @Override
  public String toString() {
    return "Save Image: " + this.imagePath;
  }
}