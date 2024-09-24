package md.hajji.equipmentservice.exceptions;

public class EquipmentAlreadyReservedException extends UnavailableEquipmentException {
    public EquipmentAlreadyReservedException(String equipmentId) {
        super(equipmentId, " equipment is already reserved");
    }
}
