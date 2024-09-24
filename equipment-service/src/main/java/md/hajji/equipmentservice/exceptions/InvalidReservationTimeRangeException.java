package md.hajji.equipmentservice.exceptions;

import java.time.LocalTime;

public class InvalidReservationTimeRangeException extends RuntimeException {

    public InvalidReservationTimeRangeException(String raison) {
        super("Invalid reservation time range: " + raison);
    }
}
