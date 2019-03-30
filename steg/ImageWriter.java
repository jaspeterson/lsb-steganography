package steg;

import java.awt.image.BufferedImage;

public class ImageWriter {
    public static BufferedImage writeImage(StegImage finalImage) {
        BufferedImage imageOut = new BufferedImage(finalImage.getWidth(), finalImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < finalImage.getHeight(); i++) {
            for (int j = 0; j < finalImage.getWidth(); j++) {
                imageOut.setRGB(j,i,finalImage.getPixel(i,j).getPixelVal());
            }
        }
        return imageOut;
    }
}