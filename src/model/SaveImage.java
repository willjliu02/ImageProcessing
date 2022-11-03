package model;

import java.io.FileWriter;
import java.io.IOException;

import imageinfo.IImage;
import imageinfo.IPixel;

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
    System.out.println("METHOD CALLED");
    String content = "";
    try {
      FileWriter file = new FileWriter(this.imagePath);
      int width = currentImage.getWidth();
      int height = currentImage.getHeight();
      IPixel[][] pixels = currentImage.getPixels();

      file.append("P3\n");
      content += "P3\n";
      file.append(width + " " + height);
      content += (width + " " + height);
      file.append("\n");
      content += "\n";
      file.append(currentImage.getMaxValue() + "\n");
      content += currentImage.getMaxValue() + "\n";
      for (int r = 0; r < height; r++) {
        for (int c = 0; c < width; c++) {
          IPixel pixel = pixels[r][c];
          file.append(pixel.getR() + " ");
          content += pixel.getR() + " ";
          file.append(pixel.getG() + " ");
          content += pixel.getG() + " ";
          file.append(pixel.getB() + " ");
          content += pixel.getB() + " ";

        }
        file.append("\n");
      }
      file.close();
      System.out.println(content);
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
