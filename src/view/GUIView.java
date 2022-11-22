package view;

import imageinfo.BasicImage;
import imageinfo.IImage;
import imageinfo.ImageUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ViewListener;

/**
 * A Graphical User Interface view for the ImageProcessor.
 */
public class GUIView extends JFrame implements IGUIView, ActionListener {

  private final Set<ViewListener> listeners;

  private final Map<String, ViewEvent> actions;
  private ButtonPanel bP;
  private ImageInfoPanel iIP;

  private MessagePanel mP;

  private String displayedImage;

  private String loadPath;

  private String savePath;

  /**
   * Constructs a GUIView.
   */
  public GUIView() {
    super();
    this.setTitle("Image Processor!");
    this.setSize( 1200, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocation(0,0);
    this.setResizable(false);

    this.listeners = new HashSet<ViewListener>();
    bP = new ButtonPanel(this);
    iIP = new ImageInfoPanel(this);
    mP = new MessagePanel(this);
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

    loadPath = "";
    savePath = "";
  }

  @Override
  public void renderMessage(String message) throws IOException {
    this.mP.refreshMessage(message);

    // set a jlabel to have certain text
  }


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
  public String getFocusComp() {
    return this.bP.getComponent();
  }

  @Override
  public String getDisplayedImage() {
    return this.displayedImage;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    ViewEvent event = this.actions.getOrDefault(e.getActionCommand(), null);

      if(event.equals(ViewEvent.LOAD)) {
        System.out.println("here");
        final JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter imageTypes = new FileNameExtensionFilter(
                "JPG, PNG, BMP, & PPM Images", "ppm", "bmp", "png", "jpg");
        fileChooser.setFileFilter(imageTypes);
        int file = fileChooser.showOpenDialog(this);
        if (file == JFileChooser.APPROVE_OPTION) {
          File fileSave = fileChooser.getSelectedFile();
          this.loadPath = fileSave.getAbsolutePath();
        }
      }
      else if(event.equals(ViewEvent.SAVE)) {
        final JFileChooser fileChooser = new JFileChooser(".");
        FileNameExtensionFilter imageTypes = new FileNameExtensionFilter(
                "JPG, PNG, BMP, & PPM Images", "ppm", "bmp", "png", "jpg");
        fileChooser.setFileFilter(imageTypes);
        int file = fileChooser.showOpenDialog(this);
        if (file == JFileChooser.APPROVE_OPTION) {
          File fileSave = fileChooser.getSelectedFile();
          this.savePath = fileSave.getAbsolutePath();
        }
      }

    if (event == null) {
      throw new IllegalStateException("An unknown action has been performed");
    }

    this.notifyListeners(event);
  }
}
