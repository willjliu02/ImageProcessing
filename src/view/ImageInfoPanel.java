package view;

import imageinfo.IImage;
import imageinfo.IPixel;
import imageinfo.Pixel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.ImageCommand;

public class ImageInfoPanel extends JPanel {
  private ActionListener view;

  private JPanel imagePan;
  private JPanel histPan;
  private JPanel hist1;
  private JPanel hist2;
  private JPanel hist3;
  private JPanel hist4;

  private IImage image;

  private Color purpleDark;
  private Color pink;
  public ImageInfoPanel(ActionListener view) {
    this.view = view;
    this.setLayout(new GridLayout(1, 2));
    this.setBackground(purpleDark);
    purpleDark = new Color(179, 153, 212);
    pink = new Color(206, 157, 217);

    //image = need to get the image that has been loaded

    imagePan = new JPanel();
    imagePan.setBackground(purpleDark);

    histPan = new JPanel();
    histPan.setBackground(pink);
    histPan.setLayout(new GridLayout(2, 2));
    hist1 = new JPanel();
    hist2 = new JPanel();
    hist3 = new JPanel();
    hist4 = new JPanel();
    hist1.setBackground(pink);
    hist2.setBackground(pink);
    hist3.setBackground(pink);
    hist4.setBackground(pink);
    histPan.add(hist1);
    histPan.add(hist2);
    histPan.add(hist3);
    histPan.add(hist4);


    this.add(imagePan);
    this.add(histPan);
  }

  @Override
  protected void paintComponent(Graphics g) {
    /*
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    int width = imagePan.getWidth();
    int height = imagePan.getWidth();

    IPixel[][] pixels = image.getPixels();
    //this might take forever
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        g.drawLine()
      }
    }
     */

  }
}

