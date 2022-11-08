package model;

import imageinfo.IImage;
import imageinfo.IPixel;

import java.io.IOException;

/**
 * Represents a tester ImageCommand to tests the the input are valid.
 */
public class MockImageCommand implements ImageCommand {
  private final Appendable log;

  /**
   * Constructs a MockImageCommand to test.
   *
   * @param log an appendable to tests its inputs.
   */
  public MockImageCommand(Appendable log) {
    this.log = log;
  }

  @Override
  public IImage apply(IImage currentImage) {
    try {
      this.log.append("Width: " + currentImage.getWidth() + "\n");
      this.log.append("Height: " + currentImage.getHeight() + "\n");
      this.log.append("Max Value: " + currentImage.getMaxValue() + "\n");
      IPixel[][] pixel = currentImage.getPixels();
      for (int r = 0; r < currentImage.getHeight(); r++) {
        for (int c = 0; c < currentImage.getWidth(); c++) {
          this.log.append(pixel[r][c].toString() + " ");
        }
        this.log.append("\n");
      }

      return null;
    } catch (IOException e) {
      throw new IllegalStateException("Unable to write to the file.");
    }
  }
}
