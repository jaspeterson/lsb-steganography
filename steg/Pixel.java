package steg; 

//Save as rgb? or as an integer representation of a pixel?
//the integer may facilitate bitshifting and encoding of other data
//maybe I can create methods that return chopped sections of the pixel
//this will allow me to use the same pixel for both images...

public class Pixel {
    private int pixelVal = 0; 

    Pixel(int pixelVal) {
        this.pixelVal = pixelVal; 
    }

    public int getPartition(int index) {
        return pixelVal & (3 << (24 - (index * 2))); 
    }

    public void addToRed(int partition) {
        int mask = 3 << 16; 
        pixelVal = (pixelVal & ~mask) | ((partition << 16) & mask); 
    }

    public void addToGreen(int partition) {
        int mask = 3 << 8; 
        pixelVal = (pixelVal & ~mask) | ((partition << 8) & mask); 
    }

    public void addToBlue(int partition) {
        int mask = 3; 
        pixelVal = (pixelVal & ~mask) | (partition & mask); 
    }

    public int getPixelVal() {
        return pixelVal; 
    }

    public int getRed() {
        return (pixelVal >>> 16) & 0xFF; 
    }

    public int getGreen() {
        return (pixelVal >>> 8) & 0xFF; 
    }

    public int getBlue() {
        return pixelVal & 0xFF; 
    }

    public void setPixelVal(int pixelVal) {
        this.pixelVal = pixelVal; 
    }
}