package model;

import imageinfo.IImage;

import java.io.IOException;
import java.util.function.Function;

/**
 * Represents a tester ImageProcessor ot test the inputs from the controller.
 */
public class MockImageProcessor implements IImageProcessor {

  Appendable log;

  /**
   * Constructs a MockImageProcessor.
   *
   * @param log a log to look at the inputs.
   */
  public MockImageProcessor(Appendable log) {
    this.log = log;
  }

  @Override
  public void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException {
    String mask = String.format("%d, %d, %d, %d", -1, -1 , Integer.MAX_VALUE, Integer.MAX_VALUE);
    this.applyCommand(imageName, (IImageMaskCommand)command, mask, newImageName);
  }

  @Override
  public void applyCommand(String imageName, IImageMaskCommand command, String masks, String newImageName) throws IllegalArgumentException {
    try {
      this.log.append("ImageName: ");
      this.log.append(imageName);
      this.log.append("; IImageMaskCommand: ");
      this.log.append(command.toString());
      this.log.append("; Mask: ");
      this.log.append(masks);
      this.log.append("; NewImageName: ");
      this.log.append(newImageName);
      this.log.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to append to the file.");
    }
  }

  @Override
  public <T> T accept(String imageName, Function<IImage, T> func) {
    try {
      this.log.append("ImageName: ");
      this.log.append(imageName);
      this.log.append("; Command: ");
      this.log.append(func.toString());
      this.log.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to append to the file.");
    }

    return null;
  }

  @Override
  public IImage getImage(String imageName) throws IllegalArgumentException {
    return null;
  }

  @Override
  public boolean hasImage(String imageName) {
    return false;
  }

  @Override
  public void loadImage(IImage image, String imageName) {
    try {
      this.log.append("Loading; ");
      this.log.append("Image Details: ");
      this.log.append(image.toString());
      this.log.append("; ImageName: ");
      this.log.append(imageName);
      this.log.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to append to the file.");
    }
  }

  @Override
  public void saveImage(String imagePath, String imageName) {
    try {
      this.log.append("Saving; ");
      this.log.append("ImagePath: ");
      this.log.append(imagePath);
      this.log.append("; ImageName: ");
      this.log.append(imageName);
      this.log.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to append to the file.");
    }
  }
}
