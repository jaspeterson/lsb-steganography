package steg;

public class StegImage {
    private int width = 0;
    private int height = 0;
    private Pixel[][] imageMap = null;
    private int currentHeight = 0;
    private int currentWidth = 0;
    private int currentPixelPosition = 0;

    StegImage(int height, int width) {
        this.height = height;
        this.width = width;
        imageMap = new Pixel[height][width];
    }

    public void addPixel(int h, int w, Pixel p) throws Exception {
        if (h < height && h >= 0 && w < width && w >= 0) {
            imageMap[h][w] = p;
        } else {
            throw new Exception();
        }
    }

    public int setNextValue(int value) {
        Pixel current = imageMap[currentHeight][currentWidth];
        currentWidth = (currentWidth + 1) % width;
        if (currentWidth == 0) {
            currentHeight++;
        }
        switch (currentPixelPosition) {
        case 0:
            current.addToRed(value);
            break;
        case 1:
            current.addToGreen(value);
            break;
        case 2:
            current.addToBlue(value);
        }
        currentPixelPosition = (currentPixelPosition + 1) % 3;
        return 0;
    }

    public boolean checkSize(int sizeToCheck) {
        return (height * width * 3) > sizeToCheck;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Pixel getPixel(int h, int w) {
        return imageMap[h][w];
    }

    // custom exception?
}