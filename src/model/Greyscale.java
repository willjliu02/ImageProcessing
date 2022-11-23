package model;

/**
 * Applies a Greyscale ColorTransformation to the given IImage.
 */
public class Greyscale extends ColorTransformation {

  /**
   * Constructs a Greyscaling matrix ColorTransformation.
   */
  public Greyscale() {
    super(new double[][] {{0.2126, 0.7152, 0.0722}, {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}});
  }

  @Override
  public String toString() {
    return "Color Transformation: greyscale";
  }
}
