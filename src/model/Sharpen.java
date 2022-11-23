package model;

/**
 * Sharpens all the Pixels of an IImage.
 */
public class Sharpen extends Filter {

  /**
   * Constructs a Sharpen Filter for an IImage.
   */
  public Sharpen() {
    super(new double[][]
            {{-0.125, -0.125, -0.125, -0.125, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, 0.25, 1, 0.25, -0.125},
                    {-0.125, 0.25, 0.25, 0.25, -0.125},
                    {-0.125, -0.125, -0.125, -0.125, -0.125}});
  }

  @Override
  public String toString() {
    return "Filter: sharpen";
  }
}
