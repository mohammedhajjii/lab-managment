package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.enums.ReservationPriority;
import md.hajji.equipmentservice.exceptions.*;
import md.hajji.equipmentservice.utils.ReservationTimes;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationRequestDTO(
        String userId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        ReservationPriority priority,
        String description,
        String equipmentId
) {
    public void validate(){

        System.out.println("validation started ");
        if (userId == null ||  userId.isBlank())
            throw new NullResourceFieldException("user id");

        if (date.isBefore(LocalDate.now().plusDays(2)))
            throw new InvalidReservationDateException(date);

        Duration reservationDuration = Duration.between(startTime, endTime);

        if(reservationDuration.isNegative())
            throw new NegativeReservationDurationException();

        if(reservationDuration.toHours() > 3)
            throw new ReservationDurationOutOfRangeException();

        if (startTime.isBefore(ReservationTimes.START_TIME)
                || endTime.isAfter(ReservationTimes.END_TIME))
            throw new TimeOutOfRangeException();

        if (equipmentId == null || equipmentId.isBlank())
            throw new NullResourceFieldException("equipmentId");

        System.out.println("validation ended");
    }
}
