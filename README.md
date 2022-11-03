We have a class diagram saved as "class diagram.png" to visualize all the classes we made.
 
- **DESIGN EXPLANATION:**
- CONTROLLER FOLDER:
  - IController: Represents a controller with the functionality of processing an image. 
  - ImageProcessorController: Supports specific commands for editing PPM files. These commands are 
  outlined in the commands section.
- IMAGE INFO FOLDER: 
  - BasicImage: Represents basic information for an image.
  - IImage: Represents an image, supporting functionality for certain image edits.
  - IImageState: Represents an immutable image, supporting specific functionality in returning 
    information. The width, height, max value, and pixels are saved for the image.
  - ImageUtil: Saves information from reading a PPM file.
  - IPixel: Provides access to value, intensity, luma, and RGB values
  - Pixel: Saves information per pixel regarding value, intensity, luma, and RGB
- MODEL FOLDER:
  - Brighten: Represents a command to brighten an image by a specified increment. Maxes out at 255 
    per RGB value. A negative increment would mean darkening an image. 
  - FlipImage: Represents a command to either vertically or horizontally flip an image. 
  - FocusComponent: Creates a grayscale image based off the specified component. 
  - IImageProcessor: Allows different commands for editing to be placed on an image. 
  - IImageState: ?? Represents the state of a processor.
  - ImageCommand: Represents applying an edit on a given image.
  - ImageProcessorModel: Saves image references, while also performing image edits
    and saving new references to those. 
  - LoadImage: Loads a new image to save
  - SaveImage: Saves an image to a specified location.
- VIEW FOLDER:
  - IView: Represents view functionality, which currently only consists of rendering messages 
    to update the command line.
  - TextImageView: Represents a view for a specific image model with functionality for rendering 
    messages.

COMMANDS:
The following commands should be typed as our program runs:
load ourImages/cayuga_1.ppm cayuga
brighten 10 cayuga cayugaBrighter
horizontal-flip cayuga cayugaHFlip
vertical-flip cayuga cayugaVFlip
red-component cayuga cayugaGreyscale
save ourImages/cayugaGrey cayugaGreyscale

PPM IMAGE EXAMPLE IS FROM:
“Images for Assignment 2.” Edited by Dan Huttenlocher, Sample Images for CS 664 - 
Computer Vision, 2003, www.cs.cornell.edu/courses/cs664/2003fa/images/. 
- We used the file titled cayuga_1.ppm from the second zip download. 

