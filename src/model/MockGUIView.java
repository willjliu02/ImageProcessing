package model;

import imageinfo.IImage;

import java.io.IOException;
import java.util.List;

import controller.ViewListener;
import view.IGUIView;

public class MockGUIView implements IGUIView {

  private String loadPath,  savePath, brightAmt, focusComp, displayingImage;

  public MockGUIView(String loadPath, String savePath, String brightenAmt,
                     String focusComp, String displayingImage) {
    this.loadPath = loadPath;
    this.savePath = savePath;
    this.brightAmt = brightenAmt;
    this.focusComp = focusComp;
    this.displayingImage = displayingImage;
  }

  @Override
  public String getLoadPath() {
    return this.loadPath;
  }

  @Override
  public String getSavePath() {
    return this.savePath;
  }

  @Override
  public String getBrightenAmt() {
    return this.brightAmt;
  }

  @Override
  public String getFocusComp() {
    return this.focusComp;
  }

  @Override
  public String getDisplayedImage() {
    return this.displayingImage;
  }

  @Override
  public void addListener(ViewListener listener) {

  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void refresh(IImage image, List<List<Integer>> histograms) {

  }

  @Override
  public void updateDisplayedImage(String newImageName) {
    this.displayingImage = newImageName;
  }

  @Override
  public void renderMessage(String message) throws IOException {

  }
}
