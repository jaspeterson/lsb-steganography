package steg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Encoder {

    public static void encode(StegImage baseImage, String fileName)
            throws FileNotFoundException, ImageSizeExceedsLimitException, IOException {
        File inputFile = new File(fileName);
        // check file size in comparison to baseImage -- member function?
        FileInputStream inFile = new FileInputStream(inputFile);
        byte fileContent[] = new byte[(int) inputFile.length()];
        inFile.read(fileContent);
        inFile.close();
        // Compare size of file vs StegImage space
        if (baseImage.checkSize(fileContent.length * 4)) {
            // Encode file into the image
            for (int i = 0; i < fileContent.length; i++) {
                baseImage.setNextValue(fileContent[i] & 0xC0);
                baseImage.setNextValue(fileContent[i] & 0x30);
                baseImage.setNextValue(fileContent[i] & 0x0C);
                baseImage.setNextValue(fileContent[i] & 0x03);
            }
        } else {
            throw new ImageSizeExceedsLimitException("The provided file is too large for the base image");
        }
    }

    public static void decode(StegImage encodedImage) {

    }
}
