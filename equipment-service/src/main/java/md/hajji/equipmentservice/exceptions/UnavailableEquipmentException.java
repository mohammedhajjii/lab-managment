package md.hajji.equipmentservice.exceptions;

public class UnavailableEquipmentException extends RuntimeException{

    public UnavailableEquipmentException(String equipmentId, String raison) {
        super("Equipment: '" + equipmentId + "' is unavailable for raison: '" + raison + "'");
    }
}
