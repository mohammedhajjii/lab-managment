package md.hajji.equipmentservice.services;

import md.hajji.equipmentservice.dtos.ReservationRequestDTO;
import md.hajji.equipmentservice.dtos.ReservationResponseDTO;
import md.hajji.equipmentservice.enums.ReservationStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public interface ReservationService {

    ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO);
    ReservationResponseDTO getReservation(String id);
    List<ReservationResponseDTO> getAllReservations();
    List<ReservationResponseDTO> getWaitlistedReservations();
    int updateReservationStatus(String id, ReservationStatus status);
    ReservationResponseDTO postPoneReservation(String reservationId, LocalDate date, LocalTime from, LocalTime to);

    Map<LocalDate, List<ReservationResponseDTO>> getReservationWeekCalendarOfEquipment(String equipmentId);
}
