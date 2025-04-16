import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class App {
    public static void main(String[] args) {
        App app = new App();
        String imageTest = "img/img02.png"; //converted img file name

        BufferedImage image = app.loadImage(imageTest);
        if (image != null) {
            app.brightnessToASCII(image);
        }
    }

    // Helper method to load an image
    public BufferedImage loadImage(String filePath) {
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);

            if (image != null) {
                return image;
            } else {
                System.out.println("Failed to load the image.");
            }
        } catch (IOException e) {
            System.err.println("Error reading the image file: " + e.getMessage());
        }
        return null;
    }

    // Method to print the size of the image
    public void printImageSize(BufferedImage image) {
        System.out.println("Image size: " + image.getWidth() + " x " + image.getHeight());
    }

    // Method to print RGB values of all pixels
    public int[][][] getPixelRGB(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][][] rgbValues = new int[width][height][3]; // 3 for R, G and B

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int color = image.getRGB(x, y);

                /*
                * 0x00ff0000 -> This mask isolates the red component by setting all bits except the red bits to 0.
                * >> 16 -> Shifts the red bits 16 positions to the right, aligning them to the least significant bits (0-7).
                */
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;

                /*
                *Example:
                *   If color = 0xFF123456 (ARGB format):

                *   Red: (0x123456 & 0x00ff0000) >> 16 = 0x12 = 18
                *   Green: (0x123456 & 0x0000ff00) >> 8 = 0x34 = 52
                *   Blue: (0x123456 & 0x000000ff) = 0x56 = 86
                *   Output:
                *   The extracted RGB values are:

                *   Red: 18
                *   Green: 52
                *   Blue: 86
                */

               rgbValues[x][y][0] = red;
               rgbValues[x][y][1] = blue;
               rgbValues[x][y][2] = green;
            }
        }

        return rgbValues;
    }

    public void printRGBMatrix(BufferedImage image) {
        int[][][] rgbValues = getPixelRGB(image); // Get the RGB values of all pixels

        for (int x = 0; x < rgbValues.length; x++) {
            for (int y = 0; y < rgbValues[x].length; y++) {
                int red = rgbValues[x][y][0];
                int green = rgbValues[x][y][1];
                int blue = rgbValues[x][y][2];

                System.out.println("Pixel (" + x + ", " + y + "): (" + red + ", " + green + ", " + blue + ")");
            }
        }
    }

    /*
    * Converts the RGB matrix into a brightness matrix.
    * The Brightness will be the same size and shape as the RGB matrix, but each element will be a single value between 0 and 255
    * that represent the overall brightness of the pixel.
    * 
    * Using the average of the rgb to set the brightness -> (R + G + B) / 3
    */

    public int[][] getPixelBrightness(BufferedImage image) {
        int[][][] rgbValues = getPixelRGB(image); // Get the RGB values of all pixels
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] brightnessIndex = new int[width][height];

        for (int x = 0; x < rgbValues.length; x++){
            for (int y = 0; y < rgbValues[x].length; y++){
                int red = rgbValues[x][y][0];
                int green = rgbValues[x][y][1];
                int blue = rgbValues[x][y][2];

                brightnessIndex[x][y] = (red + green + blue) / 3;
            }
        }
        
        return brightnessIndex;
    }

    public void printBrightnessMatrix(BufferedImage image){
        int[][] brightnessIndex = getPixelBrightness(image);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int x = 0; x < width; x++){
            for (int y = 0; y < height; y++){
                int brightness = brightnessIndex[x][y];
                System.out.print("(" + brightness + ")");
            }
        }
        
    }

    /*
    * Convert brightness numbers to ASCII characters
    */

    public void brightnessToASCII(BufferedImage image){
        String asciiScale = "^\",:;Il!i~+_-?][}{1)(|\\\\/tfjrxnuvczXYUJCLQ0OZmwqpdbkhao*#MW&8%B@$";
        int scaleLength = asciiScale.length();
        int[][] brightnessIndex = getPixelBrightness(image);
        int width = image.getWidth();
        int height = image.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int brightness = brightnessIndex[x][y];

                int charIndex = (brightness * (scaleLength - 1)) / 255;
                char asciiChar = asciiScale.charAt(charIndex);

                System.out.print(asciiChar);
                System.out.print(asciiChar);
                System.out.print(asciiChar);
            }
            System.out.println(); // Move to the next line after each row
        }
            
    }
}