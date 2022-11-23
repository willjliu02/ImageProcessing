package model;

/**
 * Blurs all the Pixels of an IImage.
 */
public class Blur extends Filter {

  /**
   * Constructs a Blur Filter for an IImage.
   */
  public Blur() {
    super(new double[][]
            {{0.0625, 0.125, 0.0625},
                    {0.125, 0.25, 0.125},
                    {0.0625, 0.125, 0.0625}});
  }

  @Override
  public String toString() {
    return "Filter: blur";
  }
}
