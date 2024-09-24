package md.hajji.equipmentservice.exceptions;

public class ReservationDurationOutOfRangeException extends InvalidReservationDurationException{
    public ReservationDurationOutOfRangeException() {
        super("reservation duration out of range, max duration 3H");
    }
}
