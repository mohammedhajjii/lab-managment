package md.hajji.equipmentservice.utils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.OptionalLong;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ReservationTimes {
    public final static LocalTime START_TIME = LocalTime.of(8, 0);
    public final static LocalTime END_TIME = LocalTime.of(19, 59);
    public final static Duration OFFSET_DURATION = Duration.ofMinutes(59);
    public final static Duration MAX_DURATION = Duration.ofHours(2).plus(Duration.ofMinutes(59));



    public static boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    public static LocalDate nextDay(LocalDate date){
        return switch (date.getDayOfWeek()){
            case SATURDAY -> date.plusDays(2);
            case FRIDAY -> date.plusDays(3);
            default -> date.plusDays(1);
        };
    }
}
