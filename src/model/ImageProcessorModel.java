package model;

import java.util.HashMap;
import java.util.Map;

import imageInfo.BasicImage;
import imageInfo.IPixel;
import imageInfo.IImage;
import imageInfo.ImageUtil;
import imageInfo.Pixel;

public class ImageProcessorModel implements IImageProcessor {

  private Map<String, IImage> images;

  public ImageProcessorModel() {
    images = new HashMap<String, IImage>();
  }

  @Override
  public void loadImagePathAndName(String imagePath, String imageName) {
    ImageUtil processImage = new ImageUtil(imagePath);
    IImage image = new BasicImage(processImage.getWidth(),
                                  processImage.getHeight(),
                                  processImage.getMaxValue(),
                                  processImage.getPixels());
    images.put(imageName, image);
  }

  @Override
  public void saveImagePathAndName(String imagePath, String imageName) {
    //?? save to a specific place ??
  }

  // TRIED THIS W ONLY RED COMPONENT, WOULD HAVE TO UPDATE W OTHER ONES BUT WASN'T
  // SURE IF PROCESS WAS RIGHT HAHA WE CAN UPDATE
  @Override
  public void focusComponent(String component, String imageName, String destImageName) {
    IImage currentImage = images.get(imageName);
    IPixel[][] oldPixels = currentImage.getPixels();
    IPixel[][] newPixels = new Pixel[currentImage.getWidth()][currentImage.getHeight()];
    IImage newImage = new BasicImage(currentImage);
    IPixel currentPixel = new Pixel(0, 0, 0);
    int maxRed = -1;

    for(int i = 0; i < oldPixels.length; i++) {
      for(int j = 0; j < oldPixels.length; j++) {
        currentPixel = oldPixels[i][j];
        newPixels[i][j] = new Pixel(currentPixel.getR(), currentPixel.getR(), currentPixel.getR());
        if(currentPixel.getR() > maxRed) {
          maxRed = currentPixel.getR();
        }
      }
    }
    ImageUtil newImage = new ImageUtil(currentImage.getWidth(),
            currentImage.getHeight(), maxRed, newPixels);
    images.put(destImageName, newImage);
  }

  @Override
  public void horizontalFlip(String imageName, String destImageName) {

  }

  @Override
  public void verticalFlip(String imageName, String destImageName) {

  }

  @Override
  public void brightenImage(int increment, String imageName, String destImageName) {

  }
}
