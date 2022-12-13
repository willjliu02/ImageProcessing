package view;

import imageinfo.IImage;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JOptionPane;
import javax.swing.text.View;

import controller.ViewListener;

/**
 * A Graphical User Interface view for the ImageProcessor.
 */
public class GUIView extends JFrame implements IGUIView, ActionListener {

  private final Set<ViewListener> listeners;

  private final Map<String, ViewEvent> actions;
  private final ButtonPanel bP;
  private final ImageInfoPanel iIP;

  private final MessagePanel mP;

  private String displayedImage;

  private String loadPath;

  private String savePath;

  /**
   * Constructs a GUIView.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processor!");
    this.setSize(1300, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(0, 0);
    this.setResizable(false);

    this.listeners = new HashSet<ViewListener>();
    bP = new ButtonPanel(this);
    iIP = new ImageInfoPanel();
    mP = new MessagePanel();
    //needs this?

    this.setLayout(new BorderLayout());
    this.add(bP, BorderLayout.SOUTH);
    this.add(mP, BorderLayout.NORTH);
    this.add(iIP, BorderLayout.CENTER);

    this.displayedImage = "";

    this.actions = new HashMap<String, ViewEvent>();
    this.actions.put("load", ViewEvent.LOAD);
    this.actions.put("save", ViewEvent.SAVE);
    this.actions.put("focus", ViewEvent.FOCUS);
    this.actions.put("brighten", ViewEvent.BRIGHTEN);
    this.actions.put("greyscale", ViewEvent.GREYSCALE);
    this.actions.put("sepia", ViewEvent.SEPIA);
    this.actions.put("blur", ViewEvent.BLUR);
    this.actions.put("sharpen", ViewEvent.SHARPEN);
    this.actions.put("horizontal-flip", ViewEvent.HORIZONTALFLIP);
    this.actions.put("vertical-flip", ViewEvent.VERTICALFLIP);
    this.actions.put("downscale", ViewEvent.DOWNSCALE);

    loadPath = "";
    savePath = "";
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.mP.refreshMessage(message);
  }


  /**
   * Calls to execute the command.
   *
   * @param e event chosen
   */
  private void notifyListeners(ViewEvent e) {
    for (ViewListener listener : this.listeners) {
      listener.listenTo(e);
    }
  }

  @Override
  public void addListener(ViewListener listener) {
    this.listeners.add(listener);
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void refresh(IImage image, List<List<Integer>> histograms) {
    List<Integer> redHist = histograms.get(0);
    List<Integer> blueHist = histograms.get(1);
    List<Integer> greenHist = histograms.get(2);
    List<Integer> valueHist = histograms.get(3);
    this.iIP.refreshImage(image);
    this.iIP.refreshHistograms(redHist, blueHist, greenHist, valueHist);
    //send to the correct panel
  }

  @Override
  public void updateDisplayedImage(String newImageName) {
    this.displayedImage = newImageName;
  }

  private String resetTextField(JTextField field) {
    String path = field.getText();
    field.setText("");
    return path;
  }

  @Override
  public String getLoadPath() {
    System.out.println(loadPath);
    return this.loadPath;
  }

  @Override
  public String getSavePath() {
    return this.savePath;
  }

  @Override
  public String getBrightenAmt() {
    //return resetTextField(this.brightenAmt);
    return this.bP.getBrightAmt();
  }

  @Override
  public String getWidthAmt() {
    //return resetTextField(this.brightenAmt);
    return this.bP.getWidthAmt();
  }

  @Override
  public String getHeightAmt() {
    //return resetTextField(this.brightenAmt);
    return this.bP.getHeightAmt();
  }

  @Override
  public String getFocusComp() {
    return this.bP.getComponent();
  }

  @Override
  public String getDisplayedImage() {
    return this.displayedImage;
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getActionCommand().equals("instructions")) {
      JOptionPane.showMessageDialog(this,
              "Welcome to Image Processor! Current accepted commands are:\n" +
                      "load to load an image from a desired location.\n" +
                      "horizontalFlip to flip an image horizontally.\n" +
                      "verticalFlip to flip an image vertically.\n" +
                      "blur to blur an image.\n" +
                      "sharpen to sharpen an image.\n" +
                      "greyscale to greyscale an image.\n" +
                      "sepia to add a sepia filter.\n" +
                      "save to save the image to a desired location. Remember to add the\n" +
                      "image extension you want to your save name. \n" +
                      "brighten will brighten the image by an increment, which you\n" +
                      "type in the textbox above the image. A negative increment.\n" +
                      "will darken.\n" +
                      "focus will make a greyscale based off the component you\n" +
                      "specify in the box above it. Type red, green, blue, value\n" +
                      "luma, or intensity.\n" +
                      "downscale downscales an image. \n" +
                      "NOTE: make sure to load an image before trying to edit it!\n" +
                      "NOTE 2: The histograms will show you graphs of components of\n" +
                      "your image for editing information.");
      return;
    }

    ViewEvent event = this.actions.getOrDefault(e.getActionCommand(), null);

    if (event.equals(ViewEvent.LOAD)) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter imageTypes = new FileNameExtensionFilter(
              "JPG, PNG, BMP, & PPM Images", "ppm", "bmp", "png", "jpg");
      fileChooser.setFileFilter(imageTypes);
      int file = fileChooser.showOpenDialog(this);
      if (file == JFileChooser.APPROVE_OPTION) {
        this.loadPath = fileChooser.getSelectedFile().getAbsolutePath();
      }
    }
    else if (event.equals(ViewEvent.SAVE)) {
      final JFileChooser fileChooser = new JFileChooser(".");
      FileNameExtensionFilter imageTypes = new FileNameExtensionFilter(
              "JPG, PNG, BMP, & PPM Images", "ppm", "bmp", "png", "jpg");
      fileChooser.setFileFilter(imageTypes);
      int file = fileChooser.showSaveDialog(this);
      if (file == JFileChooser.APPROVE_OPTION) {
        this.savePath = fileChooser.getSelectedFile().getAbsolutePath();
      }
    }

    if (event == null) {
      throw new IllegalStateException("An unknown action has been performed");
    }

    //!! use this data
    if(!(event.equals(ViewEvent.LOAD) || event.equals(ViewEvent.SAVE))) {
      String maskInfo = JOptionPane.showInputDialog("Enter mask info!");
    }
    this.notifyListeners(event);
  }
}
