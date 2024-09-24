package md.hajji.equipmentservice.exceptions;

public class TimeOutOfRangeException extends InvalidReservationTimeRangeException{

    public TimeOutOfRangeException() {
        super("time out of range 8AM - 8PM");
    }
}
