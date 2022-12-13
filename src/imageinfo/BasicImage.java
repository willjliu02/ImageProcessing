package imageinfo;

import model.IImageMaskCommand;
import model.ImageCommand;

/**
 * Represents an image with basic information.
 */
public class BasicImage implements IImage {
  private final IPixel[][] pixels;
  private final int width;
  private final int height;

  private final int maxVal;

  /**
   * Creates a basic image based on a provided image.
   *
   * @param image another IImage that will be converted to a BasicImage.
   */
  public BasicImage(IImage image) {
    this.width = image.getWidth();
    this.height = image.getHeight();
    this.maxVal = image.getMaxValue();
    this.pixels = image.getPixels();
  }

  /**
   * Creates a basic image with provided image information.
   *
   * @param width    width of the image
   * @param height   height of the image
   * @param maxValue max value seen in the image
   * @param pixels   pixels that make up the image
   */
  public BasicImage(int width, int height, int maxValue, IPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxVal = maxValue;
    this.pixels = new Pixel[this.height][this.width];
    for (int r = 0; r < this.height; r++) {
      System.arraycopy(pixels[r], 0, this.pixels[r], 0, this.width);
    }
  }

  @Override
  public IImage acceptCommand(ImageCommand command) {
    return command.apply(this);
  }

  @Override
  public IPixel[][] getPixels() {
    IPixel[][] copy = new Pixel[this.height][this.width];
    for (int r = 0; r < this.height; r++) {
      System.arraycopy(this.pixels[r], 0, copy[r], 0, this.width);
    }
    return copy;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getMaxValue() {
    return this.maxVal;
  }

  @Override
  public String toString() {
    return String.format("Width: %d; Height: %d; Max Value: %d",
            this.width, this.height, this.maxVal);
  }

  /**
   * A builder to build a basic image
   */
  public static class BasicImageMaskBuilder implements IImageMaskBuilder {
    private final boolean[][] mask;
    private final int maxValue;
    private final int width;
    private final int height;

    private final IImage image;

    public BasicImageMaskBuilder(IImage image) {
      if (image == null) {
        throw new IllegalArgumentException("This cannot be null");
      }

      this.image = image;
      this.width = image.getWidth();
      this.height = image.getHeight();
      this.mask = new boolean[this.height][this.width];
      this.maxValue = image.getMaxValue();

    }

    @Override
    public IImageMaskBuilder setModifiable(int xLeft, int yTop, int xRight, int yBottom) {
      if (xLeft < 0) {
          xLeft = 0;
      }

      if (yTop < 0) {
        yTop = 0;
      }

      if (xRight >= this.width) {
        xRight = this.width - 1;
      }

      if (yBottom >= this.height) {
        yBottom = this.height - 1;
      }

      if (xLeft > xRight) {
        int temp = xRight;
        xRight = xLeft;
        xLeft = temp;
      }

      if (yTop > yBottom) {
        int temp = yBottom;
        yBottom = yTop;
        yTop = temp;
      }

      for (int r = yTop; r <= yBottom; r++) {
        for (int c = xLeft; c <= xRight; c++) {
          this.mask[r][c] = true;
        }
      }

      return this;
    }

    @Override
    public IImageMaskBuilder setUnmodifiable(int xLeft, int yTop, int xRight, int yBottom) {
      if (xLeft < 0) {
        xLeft = 0;
      }

      if (yTop < 0) {
        yTop = 0;
      }

      if (xRight >= this.width) {
        xRight = this.width - 1;
      }

      if (yBottom >= this.height) {
        yBottom = this.height - 1;
      }

      if (xLeft > xRight) {
        int temp = xRight;
        xRight = xLeft;
        xLeft = temp;
      }

      if (yTop > yBottom) {
        int temp = yBottom;
        yBottom = yTop;
        yTop = temp;
      }

      for (int r = yTop; r <= yBottom; r++) {
        for (int c = xLeft; c <= xRight; c++) {
          this.mask[r][c] = false;
        }
      }

      return this;
    }

    private IImageMask createMask() {
      return new BasicImageMask(this.mask, this.maxValue);
    }

    @Override
    public IImage build(IImageMaskCommand command) {

      command.setMask(createMask());
      return command.apply(this.image);
    }

    /**
     * A mask for a basic image.
     */
    public class BasicImageMask implements IImageMask {
      private final boolean[][] mask;
      private final int width;
      private final int height;
      private final int maxValue;
      public BasicImageMask (boolean[][] mask, int maxValue) {
        this.mask = mask;
        this.height = mask.length;
        this.width = mask[0].length;
        this.maxValue = maxValue;
      }

      @Override
      public boolean isModifiable(int c, int r) {
        if (c < 0 || c > this.width || r < 0 || r > this.height) {
          throw new IllegalArgumentException("These are invalid points.");
        }

        return mask[r][c];
      }
    }
  }
}
