package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.enums.ReservationPriority;
import md.hajji.equipmentservice.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;

public record ReservationResponseDTO(
        String id,
        String userId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        ReservationStatus status,
        ReservationPriority priority,
        String description,
        EquipmentReservationResponseDTO equipment
) { }
