package steg;

public class ImageSizeExceedsLimitException extends Exception {
    public ImageSizeExceedsLimitException() {}
    public ImageSizeExceedsLimitException(String message) {
        super(message);
    }
}