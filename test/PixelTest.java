import org.junit.Test;

import imageInfo.Pixel;

import static org.junit.Assert.assertEquals;

public class PixelTest {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionR() {
    new Pixel(-1, 5, 5);
  }
}