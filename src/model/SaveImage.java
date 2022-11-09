package model;

import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Represents a command that saves an IImage.
 */
public class SaveImage implements ImageCommand {
  private final String imagePath;
  private final String imageExtension;

  public SaveImage(String imagePath) {
    this.imagePath = imagePath;
    this.imageExtension = imagePath.substring(imagePath.lastIndexOf("."));
  }

  @Override
  public IImage apply(IImage currentImage) {
    if(this.imageExtension.equals(".ppm")) {
      this.ppmWriter(currentImage);
    }
    else {
      //i dont wanna do this rn
    }
    //#take this out later
    return null;
  }

  /**
   * Handles writing ppm files.
   * @param currentImage image to save to location
   * @return IImage placeholder
   */
  private IImage ppmWriter(IImage currentImage) {
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
