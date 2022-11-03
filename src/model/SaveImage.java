package model;

import java.io.FileWriter;
import java.io.IOException;

import imageInfo.IImage;
import imageInfo.IPixel;

/**
 * Represents a command that saves an IImage.
 */
public class SaveImage implements ImageCommand {
  private final String path;
  public SaveImage(String path) {
    this.path = path;
  }

  @Override
  public IImage apply(IImage currentImage) {
    try {
      Appendable file = new FileWriter(this.path);
      int width = currentImage.getWidth();
      int height = currentImage.getHeight();
      IPixel[][] pixels = currentImage.getPixels();


      file.append("P3 ");
      file.append("" + width);
      file.append(' ');
      file.append("" + height);
      file.append(' ');
      file.append("" + currentImage.getMaxValue());
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          IPixel pixel = pixels[r][c];
          file.append(" " + pixel.getR());
          file.append(" " + pixel.getG());
          file.append(" " + pixel.getB());
        }
      }
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to file.");
    }

    return null;
  }
}
