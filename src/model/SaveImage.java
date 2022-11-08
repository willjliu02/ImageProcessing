package model;

import imageinfo.IImage;
import imageinfo.IPixel;

import java.io.FileWriter;
import java.io.IOException;

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
      FileWriter file = new FileWriter(this.imagePath);
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
          file.append(String.valueOf(pixel.getR())).append(" ");
          file.append(String.valueOf(pixel.getG())).append(" ");
          file.append(String.valueOf(pixel.getB())).append(" ");
        }
        file.append("\n");
      }
      file.close();

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
