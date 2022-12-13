We have a class diagram saved as "class diagram.png" to visualize all the classes we made.
 
- **DESIGN EXPLANATION:**
- CONTROLLER FOLDER:
  - IController: Represents a controller with the functionality of processing an image. 
  - ImageProcessorController: Supports specific commands for editing PPM files. These commands are 
  outlined in the commands section.
    - OLD UPDATE: We added functionality for the blur, sharpen, greyscale, and sepia commands here.
      Moreover, the constructor is also able to accept a file with a script of commands now.
      Other file types are also accepted now.
  - GUIController: NEW, Represents a controller that can process PPM images as well as basic file 
    types. Compatible with a graphical user interface. 
- IMAGE INFO FOLDER: 
  - BasicImage: Represents basic information for an image.
  - IImage: Represents an image, supporting functionality for certain image edits.
  - IImageState: Represents an immutable image, supporting specific functionality in returning 
    information. The width, height, max value, and pixels are saved for the image.
  - ImageUtil: Saves information from reading a PPM file.
    - OLD UPDATE: We added the ability to process reading other common image formats like JPG, PNG, 
      and BMP.
  - IPixel: Provides access to value, intensity, luma, and RGB values
  - Pixel: Saves information per pixel regarding value, intensity, luma, and RGB
- MODEL FOLDER:
  - Brighten: Represents a command to brighten an image by a specified increment. Maxes out at 255 
    per RGB value. A negative increment would mean darkening an image. 
  - FlipImage: Represents a command to either vertically or horizontally flip an image. 
    - UPDATE: We changed this to an abstract class so more flips could be added without code 
    - repetition.
  - Horizontal Flip: Class that extends FlipImage and horizontally flips the image. 
  - Vertical Flip: Class that extends FlipImage and vertically flips the image. 
  - FocusComponent: Creates a grayscale image based off the specified component. 
    - UPDATE: This is now an abstract class to avoid code duplication.
  - FocusRed: Class that extends FocusComponent and creates a greyscale with the red component
  - FocusGreen: Class that extends FocusComponent and creates a greyscale with the
    green component
  - FocusBlue: Class that extends FocusComponent and creates a greyscale with the blue 
    component
  - FocusValue: Class that extends FocusComponent and creates a greyscale 
    with the value component
  - FocusIntensity: NEW, Class that extends FocusComponent and creates a greyscale with the 
    intensity component
  - FocusLuma: Class that extends FocusComponent and creates a greyscale with the
    luma component
  - Color Transformation: Transforms the colors of the image, currently supporting making a
    greyscale one and applying a sepia filter. 
    - UPDATE: We changed this to an abstract class so that more color transformations plus  
    greyscale and sepia would be compatible without code repetition. 
  - Greyscale: Class that extends Color Transformation and adds the greyscale filter. 
  - Sepia: Class that extends Color Transformation and adds the sepia filter. 
  - Filter: Applies a filter on the image, currently supporting either blurring or sharpening. 
    - UPDATE: We changed this to an abstract class to avoid code repetition.
  - Blur: Class that extends Filter and represents blurring an image.
  - Sharpen: Class that extends Filter and represents sharpening an image. 
  - IImageProcessor: Allows different commands for editing to be placed on an image.
  - ImageCommand: Represents applying an edit on a given image.
  - ImageProcessorModel: Saves image references, while also performing image edits
    and saving new references to those.
  - SaveImage: Saves an image to a specified location.
  - MockImageCommand: Mock to test image commands.
  - MockImageProcessor: Mock to test the image processor. 
  - MockGUIView: mock to test the GUI view.
  - GrabHistogram: abstract class for getting the histogram values for a specific component
    of an image
  - RedHistogram: Extends GrabHistogram and makes a histogram from the red component. 
  - BlueHistogram: Extends GrabHistogram and makes a histogram from the blue component.
  - GreenHistogram: Extends GrabHistogram and makes a histogram from the green component.
  - ValueHistogram: Extends GrabHistogram and makes a histogram from the value component.
  - DownSize: NEW, EC addition, downscales the image. 
- VIEW FOLDER:
  - IView: Represents view functionality, which currently only consists of rendering messages 
    to update the command line.
  - TextImageView: Represents a view for a specific image model with functionality for rendering 
    messages.

COMMANDS FOR THE TEXT BASED VIEW:
The following commands should be typed one after another as our program runs:
ALSO IN OUR SCRIPT
load res/boston.jpg boston
brighten 10 boston bostonBright
horizontal-flip bostonBright bostonHFlip
vertical-flip bostonHFlip bostonVFlip
red-component bostonVFlip bostonRedComp
blur bostonRedComp bostonBlur
save res/bostonSuperEdited.jpg bostonBlur
sharpen boston bostonSharp
greyscale bostonSharp bostonGrey
save res/bostonSharpAndGrey.jpg bostonGrey
sepia boston bostonSepia
save res/bostonSepia.jpg bostonSepia

USING THE GUI VIEW:
The GUI view is set up with components for the user to interact with at the bottom of the screen.
There is a button that can be clicked to show a pop up with instructions, as well as buttons for 
the image commands. Since the brighten command requires an increment, a textbox is present for the 
user to enter the amount they want to brighten by. We also have a textbox that the user can enter a 
component in before pressing focus, which will then create a greyscale from that component. This 
avoids repetition in buttons, since otherwise we would have to make a button for every component. 
When an image is loaded, it will show up on the left hand side of the center area of the screen.
All corresponding histogram for RGB and value components will show up on the right. Messages will 
update on the top of the screen depending on what the user does. For example, it might show 
"Request Processed!" when they enter a valid command. 

JPG IMAGE EXAMPLE IS FROM:
Rivera, Sofia. “The Best Views around Boston.” Boston Magazine, Boston Magazine, 1 Feb. 2021, 
www.bostonmagazine.com/arts-entertainment/best-views-around-boston/. 

EC ADDITION:
Downscaling: We implemented this feature by adding a function object for downscaling. We slightly
updated the view to be a little larger to account for these added components on our view. Example
images are in our res folder. 