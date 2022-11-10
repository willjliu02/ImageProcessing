We have a class diagram saved as "class diagram.png" to visualize all the classes we made.
 
- **DESIGN EXPLANATION:**
- CONTROLLER FOLDER:
  - IController: Represents a controller with the functionality of processing an image. 
  - ImageProcessorController: Supports specific commands for editing PPM files. These commands are 
  outlined in the commands section. 
    - UPDATE: We added functionality for the blur, sharpen, greyscale, and sepia commands here.
      Moreover, the constructor is also able to accept a file with a script of commands now. 
- IMAGE INFO FOLDER: 
  - BasicImage: Represents basic information for an image.
  - IImage: Represents an image, supporting functionality for certain image edits.
  - IImageState: Represents an immutable image, supporting specific functionality in returning 
    information. The width, height, max value, and pixels are saved for the image.
  - ImageUtil: Saves information from reading a PPM file.
    - UPDATE: We added the ability to process reading other common image formats like JPG, PNG, 
      and BMP.
  - IPixel: Provides access to value, intensity, luma, and RGB values
  - Pixel: Saves information per pixel regarding value, intensity, luma, and RGB
- MODEL FOLDER:
  - Brighten: Represents a command to brighten an image by a specified increment. Maxes out at 255 
    per RGB value. A negative increment would mean darkening an image. 
  - FlipImage: Represents a command to either vertically or horizontally flip an image. 
  - FocusComponent: Creates a grayscale image based off the specified component. 
  - Color Transformation: NEW, transforms the colors of the image, currently supporting making a
    greyscale one and applying a sepia filter. 
  - Filter: NEW, applies a filter on the image, currently supporting either blurring or sharpening. 
  - IImageProcessor: Allows different commands for editing to be placed on an image.
  - ImageCommand: Represents applying an edit on a given image.
  - ImageProcessorModel: Saves image references, while also performing image edits
    and saving new references to those.
  - SaveImage: Saves an image to a specified location.
- VIEW FOLDER:
  - IView: Represents view functionality, which currently only consists of rendering messages 
    to update the command line.
  - TextImageView: Represents a view for a specific image model with functionality for rendering 
    messages.

COMMANDS:
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

PPM IMAGE EXAMPLE IS FROM:
It is the starfield image on this page. 
“PPMA Files Portable Pixel Map (ASCII).” PPMA Files, 22 July 2011,
people.sc.fsu.edu/~jburkardt/data/ppma/ppma.html. 

JPG IMAGE EXAMPLE IS FROM:
Rivera, Sofia. “The Best Views around Boston.” Boston Magazine, Boston Magazine, 1 Feb. 2021, 
www.bostonmagazine.com/arts-entertainment/best-views-around-boston/. 

