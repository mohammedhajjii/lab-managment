package md.hajji.equipmentservice.repositories;

import md.hajji.equipmentservice.entities.Reservation;
import md.hajji.equipmentservice.enums.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, String> {
    List<Reservation> findByEquipmentId(String equipmentId);
    List<Reservation> findByStatus(ReservationStatus status);
    List<Reservation> findByEquipmentIdAndDateEquals(String equipmentId, LocalDate date);

    @Query("SELECT CASE WHEN count(r) > 0 THEN true ELSE false END " +
            "FROM Reservation r WHERE (r.equipment.id = :eid) " +
            "AND (r.date = :date) " +
            "AND ((r.startTime BETWEEN :start AND :end) OR (r.endTime BETWEEN :start AND :end ))")
    boolean existsByEquipmentIdAndDateEqualsAndInTimeRange
            (@Param("eid") String equipmentId,
             @Param("date") LocalDate date,
             @Param("start") LocalTime start,
             @Param("end") LocalTime end);

    //for reservation calendar
    List<Reservation> findByEquipmentIdAndDateBetween( String id, LocalDate start, LocalDate end);

    @Modifying
    @Query("UPDATE Reservation r SET r.status = :status where r.id = :id")
    int updateReservationStatus(@Param("id") String id, @Param("status") ReservationStatus status);
}
