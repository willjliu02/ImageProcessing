package view;

import imageinfo.IImage;

import java.util.List;

import controller.ViewListener;

/**
 * An interface for a graphical user interface view.
 */
public interface IGUIView extends IView {
  /**
   * Gets the load path from the load bar.
   *
   * @return String of the load path
   */
  String getLoadPath();

  /**
   * Gets the save path from the save bar.
   *
   * @return String of the save path
   */
  String getSavePath();

  /**
   * Gets the brighten amount.
   *
   * @return String of the brighten amount.
   */
  String getBrightenAmt();

  /**
   * Gets width.
   * @return width
   */
  String getWidthAmt();

  /**
   * Get height.
   * @return height
   */
  String getHeightAmt();

  /**
   * Gets which component is to be focused.
   *
   * @return String the component to be focused.
   */
  String getFocusComp();

  /**
   * Gets the name of the displaying IImage.
   *
   * @return String the name of the displaying Image.
   */
  String getDisplayedImage();

  /**
   * Adds a listener to the view to be notified of changes.
   *
   * @param listener a listener for ViewEvents.
   */
  void addListener(ViewListener listener);

  /**
   * Makes the window visible.
   */
  void makeVisible();

  /**
   * Refresh the view with the new Image.
   *
   * @param image the IImage that will be displayed.
   */
  void refresh(IImage image, List<List<Integer>> histograms);

  /**
   * Update the name of the currently displayed Image.
   *
   * @param newImageName the new name from the view of the currently displayed image.
   */
  void updateDisplayedImage(String newImageName);
}
