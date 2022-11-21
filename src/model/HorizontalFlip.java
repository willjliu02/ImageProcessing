package model;

/**
 * Flips an image Horizontally along the y-axis.
 */
public class HorizontalFlip extends FlipImage {
  @Override
  protected int getFlippedRow(int row, int height) {
    return row;
  }

  @Override
  protected int getFlippedCol(int col, int width) {
    return width - 1 - col;
  }

  @Override
  public String toString() {
    return "Flip Image: horizontal-flip";
  }
}
