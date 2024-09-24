package md.hajji.equipmentservice.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String resourceName, String argument) {
        super("Resource '"+ resourceName + "' not found for argument '"+ argument +"'");
    }
}
