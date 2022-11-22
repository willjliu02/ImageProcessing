import imageinfo.IPixel;
import imageinfo.Pixel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Test that the Pixel class saves data correctly.
 */
public class PixelTest {
  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionRLow() {
    new Pixel(-1, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionRHigh() {
    new Pixel(256, 5, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionGLow() {
    new Pixel(5, -1, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionGHigh() {
    new Pixel(5, 256, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionBLow() {
    new Pixel(5, 5, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructExceptionBHigh() {
    new Pixel(5, 5, 256);
  }

  @Test
  public void testGetValue() {
    IPixel pix = new Pixel(1, 2, 3);

    assertEquals(3, pix.getValue());
  }

  @Test
  public void testGetIntensity() {
    IPixel pix = new Pixel(1, 2, 3);

    assertEquals(2, pix.getIntensity());
  }

  @Test
  public void testGetLuma() {
    IPixel pix = new Pixel(100, 100, 100);

    assertEquals(100, pix.getLuma());
  }

  @Test
  public void testGetR() {
    IPixel pix = new Pixel(1, 2, 3);

    assertEquals(1, pix.getR());
  }

  @Test
  public void testGetG() {
    IPixel pix = new Pixel(1, 2, 3);

    assertEquals(2, pix.getG());
  }

  @Test
  public void testGetB() {
    IPixel pix = new Pixel(1, 2, 3);

    assertEquals(3, pix.getB());
  }

  @Test
  public void testEquals() {
    IPixel pix1 = new Pixel(1, 2, 3);
    IPixel pix2 = new Pixel(1, 2, 3);

    assertEquals(pix1, pix2);
  }

  @Test
  public void testEqualsReflexivity() {
    IPixel pix1 = new Pixel(1, 2, 3);
    IPixel pix2 = new Pixel(1, 2, 3);

    assertEquals(pix2, pix1);
  }

  @Test
  public void testTrueEquals() {
    IPixel pix1 = new Pixel(1, 2, 3);
    IPixel pix2 = pix1;

    assertEquals(pix1, pix2);
  }

  @Test
  public void testNotEquals1() {
    IPixel pix1 = new Pixel(1, 2, 2);
    IPixel pix2 = new Pixel(1, 2, 3);

    assertNotEquals(pix1, pix2);
  }

  @Test
  public void testNotEquals2() {
    IPixel pix1 = new Pixel(1, 3, 3);
    IPixel pix2 = new Pixel(1, 2, 3);

    assertNotEquals(pix1, pix2);
  }

  @Test
  public void testNotEquals3() {
    IPixel pix1 = new Pixel(2, 2, 3);
    IPixel pix2 = new Pixel(1, 2, 3);

    assertNotEquals(pix1, pix2);
  }

  @Test
  public void testEqualsTransitive() {
    IPixel pix1 = new Pixel(1, 2, 3);
    IPixel pix2 = new Pixel(1, 2, 3);
    IPixel pix3 = new Pixel(1, 2, 3);

    assertEquals(pix1, pix2);
    assertEquals(pix2, pix3);
    assertEquals(pix1, pix3);
  }
}