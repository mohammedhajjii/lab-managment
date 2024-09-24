package md.hajji.equipmentservice.exceptions;

public class NullResourceFieldException extends RuntimeException{

    public NullResourceFieldException(String fieldName) {
        super("field '" + fieldName + "' is null!");
    }
}
