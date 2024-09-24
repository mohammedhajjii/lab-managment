package md.hajji.equipmentservice.exceptions;

public class InvalidReservationDurationException extends InvalidReservationTimeRangeException{
    public InvalidReservationDurationException(String raison) {
        super(raison);
    }
}
