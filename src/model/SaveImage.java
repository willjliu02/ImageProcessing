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
      BufferedImage image = new BufferedImage(currentImage.getWidth(), currentImage.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      IPixel[][] imagePixels = currentImage.getPixels();
      Color color = new Color(0, 0, 0);
      for (int i = 0; i < imagePixels.length; i++) {
        for (int j = 0; j < imagePixels[i].length; j++) {
          color = new Color(imagePixels[i][j].getR(), imagePixels[i][j].getG(),
                  imagePixels[i][j].getB());
          image.setRGB(j, i, color.getRGB());
        }
      }
      File file = new File(imagePath);
      if(imageExtension.equals(".png")) {
        try {
          ImageIO.write(image, "PNG", file);
        }
        catch(IOException e) {
          System.out.println("Saving failed!");
        }
      }
      else if(imageExtension.equals(".jpg")) {
        try {
          ImageIO.write(image, "JPG", file);
        }
        catch(IOException e) {
          System.out.println("Saving failed!");
        }
      }
      else if(imageExtension.equals(".bmp")) {
        try {
          ImageIO.write(image, "BMP", file);
        }
        catch(IOException e) {
          System.out.println("Saving failed!");
        }
      }
    }
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
