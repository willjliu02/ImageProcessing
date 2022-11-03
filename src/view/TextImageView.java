package view;

import java.io.IOException;

import model.IImageProcessorState;

/**
 * This class represents operations that should be offered by
 * a view for a simple Image Processor.
 */
public class TextImageView implements IView {

  protected final IImageProcessorState model;

  protected Appendable appendable;

  /**
   * Constructor to save the game model and default appendable.
   * @param model model to make a view of
   */
  public TextImageView(IImageProcessorState model) {
    if (model == null) {
      throw new IllegalArgumentException("Null model provided.");
    }
    this.model = model;
    this.appendable = System.out;
  }

  /**
   * Constructor to save the game model and default appendable.
   * @param model model to make a view of
   * @param appendable appendable to save
   */
  public TextImageView(IImageProcessorState model, Appendable appendable) {
    this(model);
    if (appendable == null) {
      throw new IllegalArgumentException("Null appendable provided.");
    }
    this.appendable = appendable;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      appendable.append(message);
    } catch (IOException e) {
      throw new IOException("Transmission failed.");
    }
  }
}
