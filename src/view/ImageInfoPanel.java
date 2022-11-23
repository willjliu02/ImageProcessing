package view;

import imageinfo.IImage;
import imageinfo.IPixel;

import java.awt.*;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.util.List;



/**
 * Represents the part of the screen with the image information, displaying it and histograms.
 */
public class ImageInfoPanel extends JPanel implements ChangeListener {
  private ImagePanel imagePan;
  private JPanel histPan;

  private List<Integer> red;
 private List<Integer> green;
  private List<Integer> blue;
  private List<Integer> value;

  private IImage image;

  private JSlider sliderY;
  private int yStart;
  private int sliderYValue;

  private JSlider sliderX;
  private int xStart;
  private int sliderXValue;

  /**
   * Creates the Image Info Panel.
   */
  public ImageInfoPanel() {
    this.setLayout(new GridLayout(1, 2));
    Color purpleDark = new Color(179, 153, 212);
    this.setBackground(purpleDark);
    Color pink = new Color(206, 157, 217);

    imagePan = new ImagePanel();
    imagePan.setLayout(new BorderLayout());
    imagePan.setBackground(purpleDark);
    sliderY = new JSlider(JSlider.VERTICAL, 0, 100, 0);
    imagePan.add(sliderY, BorderLayout.EAST);
    sliderY.addChangeListener(this);
    sliderX = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
    imagePan.add(sliderX, BorderLayout.SOUTH);
    sliderX.addChangeListener(this);


    histPan = new JPanel();
    histPan.setBackground(purpleDark);
    histPan.setLayout(new GridLayout(2, 2));
    // add labels here?
    HistGraph hist1 = new HistGraph("red");
    HistGraph hist2 = new HistGraph("blue");
    HistGraph hist3 = new HistGraph("green");
    HistGraph hist4 = new HistGraph("value");
    histPan.add(hist1);
    hist1.setBackground(pink);
    histPan.add(hist2);
    hist2.setBackground(pink);
    histPan.add(hist3);
    hist3.setBackground(pink);
    histPan.add(hist4);
    hist4.setBackground(pink);

    this.add(imagePan);
    this.add(histPan);

    yStart = 0;
    sliderYValue = 0;

    xStart = 0;
    sliderXValue = 0;
  }

  /**
   * Refreshes the image on the screen.
   *
   * @param image image to refresh
   */
  public void refreshImage(IImage image) {
    this.image = image;
    this.imagePan.repaint();
  }

  /**
   * Refreshes the histograms on the screen
   *
   * @param red   red histogram
   * @param green green histogram
   * @param blue  blue histogram
   * @param value value histogram
   */
  public void refreshHistograms(List<Integer> red, List<Integer> green, List<Integer> blue, List<Integer> value) {
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.value = value;
    this.histPan.repaint();
  }

  @Override
  public void stateChanged(ChangeEvent e) {
    sliderYValue = sliderY.getValue();
    sliderXValue = sliderX.getValue();
    this.imagePan.repaint();
  }

  /**
   * Panel that displays the image.
   */
  private class ImagePanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {

      super.paintComponent(g);

      if (image == null) {
        return;
      }

      Graphics2D g2d = (Graphics2D) g;

      IPixel[][] pixels = image.getPixels();

      int height = this.getHeight();
      int yDist = (int) (pixels.length / 100.0);
      int yEnd = height + yStart;
      if(height + (sliderYValue * yDist) < pixels.length) {
        yStart = sliderYValue * yDist;
        yEnd = height + yStart;
      }

      int width = this.getWidth();
      int xDist = (int) (pixels[0].length / 100.0);
      int xEnd = width + xStart;
      if(width + (sliderXValue * xDist) < pixels[0].length) {
        xStart = sliderXValue * xDist;
        xEnd = width + xStart;
      }

      for (int i = yStart; i < yEnd ; i++) {
        for (int j = xStart; j < xEnd; j++) {
          g2d.setColor(new Color(pixels[i][j].getR(), pixels[i][j].getG(), pixels[i][j].getB()));
          g2d.drawLine(j - xStart, i - yStart, j - xStart, i - yStart);
        }
      }
    }
  }

  /**
   * Panel for each histogram.
   */
  private class HistGraph extends JPanel {

    String component;

    /**
     * Creates a histogram panel specifying what component it is for.
     *
     * @param component component to make graph from
     */
    public HistGraph(String component) {

      if (!(component.equals("red") || component.equals("blue") || component.equals("green")
              || component.equals("value"))) {
        throw new IllegalArgumentException("Invalid histogram.");
      }
      this.component = component;
    }

    @Override
    protected void paintComponent(Graphics g) {

      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      if (image == null || red == null || green == null || blue == null || value == null) {
        return;
      }

      int startPos = 15;

      int width = this.getWidth() - startPos * 2;
      int height = this.getHeight() - startPos * 2;

      g2d.setColor(Color.BLACK);
      //horiz line
      g2d.drawLine(startPos, height + startPos, width + startPos, height + startPos);
      //vert line
      g2d.drawLine(startPos, startPos, startPos, height + startPos);
      // draw graph

      List<Integer> points;
      if (component.equals("red")) {
        g2d.setColor(Color.RED);
        points = red;
      } else if (component.equals("green")) {
        g2d.setColor(Color.GREEN);
        points = green;
      } else if (component.equals("blue")) {
        g2d.setColor(Color.BLUE);
        points = blue;
      } else {
        g2d.setColor(Color.DARK_GRAY);
        points = value;
      }

      int highestVal = -1;
      for (int i = 0; i < points.size() - 1; i++) {
        if (points.get(i) > highestVal) {
          highestVal = points.get(i);
        }
      }

      double yDist = (double) (height) / highestVal;
      double xDist = (double) (width) / points.size();

      for (int i = 0; i < points.size() - 1; i++) {
        g2d.drawLine((int) (startPos + i * xDist), (int) (height - (points.get(i) * yDist) +
                startPos), (int) (startPos + (i + 1) * xDist), (int) (height - (points.get(i + 1)
                * yDist) + startPos));
      }

      g2d.drawString(component + " component", 10, 10);
    }
  }
}

