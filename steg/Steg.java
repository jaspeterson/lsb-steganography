package steg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Steg {
    public static void main(String[] args) {
        System.out.println("Begin Program");
        final long startTime = System.currentTimeMillis();
        if (args.length == 3) {
            // Read image 1
            String fileName1 = args[0];
            String encodeFile = args[1];
            String action = args[2];
            String outFileName = "../out/steg_"
                    + fileName1.substring(fileName1.lastIndexOf('/') + 1, fileName1.length());
            if (action.equals("encode")) {
                File imageFile1 = new File(fileName1);
                StegImage baseImage = null;
                try {
                    baseImage = ImageScanner.scanImage(imageFile1);

                    // Encode image 2 into image 1
                    try {
                        Encoder.encode(baseImage, encodeFile);

                        BufferedImage imageOut = ImageWriter.writeImage(baseImage);
                        File outFile = new File(outFileName);
                        try {
                            ImageIO.write(imageOut, "jpg", outFile);
                        } catch (IOException e) {
                            System.out.println("Error: Could not write to file \"" + outFileName + "\"");
                        }
                    } catch (ImageSizeExceedsLimitException e) {
                        System.out.println(e.getMessage());
                    } catch (IOException e) {
                        System.out.println("Cannot write to file \"" + outFileName + "\"");
                        e.printStackTrace();
                    } /*
                       * catch (FileNotFoundException e) { System.out.println("File \"" + outFileName
                       * + "\" is not found"); e.printStackTrace(); }
                       */
                } catch (IOException e) {
                    System.out.println("Error: File \"" + fileName1 + "\" does not exist");
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println("Error: Error in file \"" + fileName1 + "\"");
                    e.printStackTrace();
                }
            } else if (action.equals("decode")) {

            } else {
                System.out.println("Provide valid action: 'encode'/'decode'");
            }
        } else {
            System.out.println("Insufficient arguments");
        }
        final long endTime = System.currentTimeMillis();
        System.out.println("Total execution time: " + (endTime - startTime));
    }
}