package md.hajji.equipmentservice.exceptions;

import java.time.LocalDate;

public class InvalidReservationDateException extends RuntimeException{

    public InvalidReservationDateException(LocalDate date) {
        super("invalid date: '" + date + "' reservation must be made at least 48H in advance");
    }
}
