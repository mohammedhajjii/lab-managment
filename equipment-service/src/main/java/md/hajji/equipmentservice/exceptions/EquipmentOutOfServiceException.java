package md.hajji.equipmentservice.exceptions;

public class EquipmentOutOfServiceException extends UnavailableEquipmentException{
    public EquipmentOutOfServiceException(String equipmentId) {
        super(equipmentId, " equipment is out of service");
    }
}
