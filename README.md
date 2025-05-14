# Image to ASCII Converter

## Description
This Java project converts an image into ASCII art by calculating the brightness of each pixel and mapping it to a corresponding ASCII character. The output is an image file containing the ASCII representation.

## Examples 

1. **Example 1:**
    - ![output1.png](./output/output1.png)
  
2. **Example 2:**
    - ![output3.png](./output/output3.png)

## Requirements
Before running the program, make sure you have Java installed on your system.

1. **Install the Java Development Kit (JDK):**
   - Download and install the JDK from the official Oracle website: [JDK Download](https://www.oracle.com/java/technologies/javase-downloads.html).
   - Follow the installation instructions for your operating system.
   - Verify the installation by running the following command:
     
     ```bash
     java -version
     ```

## Setup Instructions

1. **Clone or Download the Project:**
   - Clone the repository or download the project files:

     ```bash
     git clone https://github.com/Pedro-Lapenta/ImageToASCII.git
     ```

2. **Compile the Java Files:**
   - Navigate to the project directory and compile the `App.java` file:

     ```bash
     javac App.java
     ```

3. **Run the Program:**
   - After compiling, run the program:

     ```bash
     java App
     ```
   - The program will prompt for the image file name (should be located in the `img/` folder) and the output file name (will be saved in the `output/` folder).

## How the Image is Converted to ASCII

1. **Loading the Image:**
   - The program starts by loading the image from a specified file path using the `ImageIO` library. The image is represented as a `BufferedImage` object in memory.

2. **Calculating Brightness:**
   - For each pixel in the image, the program extracts the RGB (Red, Green, Blue) values and calculates the pixel's **brightness**. 
   - The brightness is calculated using a weighted average of the RGB values:

     ```java
     (299 * red + 587 * green + 114 * blue) / 1000
     ```

   - This brightness value ranges from 0 (darkest) to 255 (brightest).

3. **Mapping Brightness to ASCII Characters:**
   - The program maps each pixel's brightness to an ASCII character based on a predefined ASCII scale. Brighter pixels are mapped to more complex characters, while darker pixels are mapped to simpler ones.

     Example of an ASCII scale:
     ```java
     "^\",:;Il!i~+_-?][}{1)(|\\tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$"
     ```

4. **Creating the ASCII Image:**
   - The program generates an image with the ASCII characters. It uses the "Courier New" font to maintain consistent character width and height, so the ASCII art aligns properly.
   - The ASCII characters are placed in the image to reflect the original image's dimensions, essentially recreating the original image using text.

5. **Saving the ASCII Image:**
   - Once the ASCII art is generated, the program saves the result as a `.png` image in the `output/` folder.

## Project Structure
- `App.java`: Main file containing the ASCII conversion logic.
- `img/`: Folder containing input images.
- `output/`: Folder where the generated ASCII images are saved.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
