package md.hajji.equipmentservice.utils;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationDateTime(
        LocalDate date,
        LocalTime start,
        Duration duration
) {

    public ReservationDateTime next(){
        if(start.plus(duration).isAfter(ReservationTimes.END_TIME))
            return new ReservationDateTime(
                    ReservationTimes.nextDay(date),
                    ReservationTimes.START_TIME,
                    this.duration
            );

        return new ReservationDateTime(date, start.plus(duration),duration);
    }
}