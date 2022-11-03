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
The following commands should be typed one after another as our program runs:
ALSO IN OUR SCRIPTS
load ourImages/imageFound.ppm foundImage
brighten 10 foundImage brighterImage
horizontal-flip brighterImage hFlipImage
vertical-flip hFlipImage vFlipImage
red-component vFlipImage greyImage
save ourImages/greyImage greyscaleImage

PPM IMAGE EXAMPLE IS FROM:
It is the first sample ppm file. 
CS35: Lab 1 - Ppm Image Format, www.cs.swarthmore.edu/~soni/cs35/f13/Labs/extras/01/ppm_info.html. 

