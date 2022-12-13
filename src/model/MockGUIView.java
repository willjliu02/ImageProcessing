package model;

import imageinfo.IImage;

import java.io.IOException;
import java.util.List;

import controller.ViewListener;
import view.IGUIView;

/**
 * Mock GUI view for testing.
 */
public class MockGUIView implements IGUIView {

  private final String loadPath;
  private final String savePath;
  private final String brightAmt;
  private final String focusComp;
  private String displayingImage;

  /**
   * Creates the mock GUI view.
   * @param loadPath path to load from
   * @param savePath path to save to
   * @param brightenAmt amt to brighten by
   * @param focusComp component to make greyscale from
   * @param displayingImage image displaying
   */
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
  public String getWidthAmt() {
    return null;
  }

  @Override
  public String getHeightAmt() {
    return null;
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
    return;
  }

  @Override
  public void makeVisible() {
    return;
  }

  @Override
  public void refresh(IImage image, List<List<Integer>> histograms) {
    return;
  }

  @Override
  public void updateDisplayedImage(String newImageName) {
    this.displayingImage = newImageName;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    return;
  }
}
