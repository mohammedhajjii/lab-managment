package md.hajji.equipmentservice.entities;

import jakarta.persistence.*;
import lombok.*;
import md.hajji.equipmentservice.enums.ReservationPriority;
import md.hajji.equipmentservice.enums.ReservationStatus;
import org.hibernate.annotations.UuidGenerator;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @ToString
public class Reservation {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    private String id;
    private String userId;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    @Enumerated(EnumType.STRING)
    private ReservationPriority priority;
    private String description;
    @ManyToOne
    private Equipment equipment;


    public Duration getDuration(){
        return Duration.between(startTime, endTime);
    }

    public boolean isActive(){
        return status == ReservationStatus.ACCEPTED || status == ReservationStatus.CANCEL_REQUESTED;
    }

   public boolean hasOverlapWith(LocalTime from, LocalTime to){
        return !(to.isBefore(this.startTime) || from.isAfter(this.endTime));
   }

   public boolean hasPriorityHigherThan(Reservation other){
        return this.priority.compareTo(other.getPriority()) > 0;
   }


}
