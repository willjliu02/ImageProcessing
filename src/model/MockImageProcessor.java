package model;

import java.io.IOException;

import imageInfo.IImage;

public class MockImageProcessor implements IImageProcessor {

  Appendable log;

  public MockImageProcessor(Appendable log) {
    this.log = log;
  }

  @Override
  public void applyCommand(String imageName, ImageCommand command, String newImageName)
          throws IllegalArgumentException {
    try {
      this.log.append("ImageName: ");
      this.log.append(imageName);
      this.log.append("; Command: ");
      this.log.append(command.toString());
      this.log.append("; NewImageName: ");
      this.log.append(newImageName);
      this.log.append("\n");
    } catch (IOException e) {
      throw new IllegalStateException("Unable to append to the file.");
    }
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
  public void loadImage(String imagePath, String imageName) {
    try {
      this.log.append("Loading; ");
      this.log.append("ImagePath: ");
      this.log.append(imagePath);
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