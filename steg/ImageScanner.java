package steg;

import java.io.File;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageScanner {
    public static StegImage scanImage(File imageFile) throws IOException, Exception {
        BufferedImage imageBuffer = ImageIO.read(imageFile);
        System.out.println(imageBuffer.getType());
        // create image class - return?
        StegImage image = new StegImage(imageBuffer.getHeight(), imageBuffer.getWidth());
        for (int i = 0; i < imageBuffer.getHeight(); i++) {
            for (int j = 0; j < imageBuffer.getWidth(); j++) {
                Pixel p = null;
                try {
                    p = new Pixel(imageBuffer.getRGB(j, i));
                } catch (Exception e) {
                    System.out.println("Error: trouble getting rgb value");
                    throw e;
                }
                try {
                    image.addPixel(i, j, p);
                } catch (Exception e) {
                    throw e;
                }
            }
        }
        return image;
    }
}