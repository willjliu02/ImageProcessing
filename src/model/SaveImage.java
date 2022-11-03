package model;

import java.io.FileWriter;
import java.io.IOException;

import imageInfo.IImage;
import imageInfo.IPixel;

/**
 * Represents a command that saves an IImage.
 */
public class SaveImage implements ImageCommand {
  private final String imagePath;

  public SaveImage(String imagePath) {
    this.imagePath = imagePath;
  }

  @Override
  public IImage apply(IImage currentImage) {
    try {
      Appendable file = new FileWriter(this.imagePath);
      int width = currentImage.getWidth();
      int height = currentImage.getHeight();
      IPixel[][] pixels = currentImage.getPixels();

      file.append("P3\n");
      file.append(width + " " + height);
      file.append("\n");
      file.append(currentImage.getMaxValue() + "\n");
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          IPixel pixel = pixels[r][c];
          file.append(pixel.getR() + " ");
          file.append(pixel.getG() + " ");
          file.append(pixel.getB() + " ");
        }
        file.append("\n");
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to file.");
    }

    return null;
  }

  @Override
  public String toString() {
    return "Save Image: " + this.imagePath;
  }
}
