package imageinfo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method
 * as required.
 */
public class ImageUtil {

  private static int width;
  private static int height;
  private static int maxValue;
  private static IPixel[][] imagePixels;

  public ImageUtil(String fileName) {
    readPPM(fileName);
  }

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      //System.out.println(s);
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    ImageUtil.width = width;
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    ImageUtil.height = height;
    ImageUtil.imagePixels = new Pixel[width][height];
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    ImageUtil.maxValue = maxValue;
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imagePixels[i][j] = new Pixel(r, g, b);
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Returns a copy of the current pixels in the array.
   *
   * @return pixel array of the pixels in the image
   */
  public IPixel[][] getPixels() {
    IPixel[][] returnArray = new Pixel[height][width];
    IPixel reference = new Pixel(0, 0, 0);
    for (int i = 0; i < imagePixels.length; i++) {
      for (int j = 0; j < imagePixels[i].length; j++) {
        reference = imagePixels[i][j];
        returnArray[i][j] = new Pixel(reference.getR(), reference.getG(), reference.getB());
      }
    }
    return returnArray;
  }

  /**
   * Returns the width of the image.
   *
   * @return image width
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the image.
   *
   * @return image width
   */
  public int getHeight() {
    return width;
  }

  /**
   * Returns the width of the image.
   *
   * @return image width
   */
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Demos the main.
   * @param args the arguments to the main.
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "sample.ppm";
    }

    ImageUtil.readPPM(filename);
  }
}
