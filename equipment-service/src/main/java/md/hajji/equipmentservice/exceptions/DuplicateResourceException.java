package md.hajji.equipmentservice.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String resourceName, String resourceValue) {
        super("Duplicate resource: " + resourceName + " for value: " + resourceValue);
    }
}
