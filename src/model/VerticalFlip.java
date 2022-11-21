package model;

/**
 * Flips an image Vertically along the x-axis.
 */
public class VerticalFlip extends FlipImage {
  @Override
  protected int getFlippedRow(int row, int height) {
    return height - 1 - row;
  }

  @Override
  protected int getFlippedCol(int col, int width) {
    return col;
  }

  @Override
  public String toString() {
    return "Flip Image: vertical-flip";
  }
}
