package md.hajji.equipmentservice.exceptions;

import java.time.LocalTime;

public class NegativeReservationDurationException extends InvalidReservationDurationException{

    public NegativeReservationDurationException() {
        super("negative reservation duration");
    }
}
