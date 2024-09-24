package md.hajji.equipmentservice.exceptions;

public class InvalidImageFormatException extends RuntimeException {

    public InvalidImageFormatException() {
        super("invalid image format, accepted formats are PNG, JPEG");
    }
}
