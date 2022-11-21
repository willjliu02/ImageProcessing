package view;

import controller.ViewListener;

public interface IGUIView extends IView {
  /**
   * Gets the load path from the load bar.
   * @return String of the load path
   */
  String getLoadPath();

  /**
   * Gets the save path from the save bar.
   * @return String of the save path
   */
  String getSavePath();

  /**
   * Gets the brighten amount.
   * @return String of the brighten amount.
   */
  String getBrightenAmt();

  /**
   * Gets the name of the displaying IImage.
   * @return String the name of the displaying Image.
   */
  String getDisplayedImage();

  /**
   * Adds a listener to the view to be notified of changes.
   * @param listener a listener for ViewEvents.
   */
  void addListener(ViewListener listener);

  /**
   * Makes the window visible.
   */
  void makeVisible();
}
