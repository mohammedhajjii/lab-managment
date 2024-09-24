package md.hajji.equipmentservice.services.impl;

import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.ReservationRequestDTO;
import md.hajji.equipmentservice.dtos.ReservationResponseDTO;
import md.hajji.equipmentservice.entities.Equipment;
import md.hajji.equipmentservice.entities.Reservation;
import md.hajji.equipmentservice.enums.EquipmentStatus;
import md.hajji.equipmentservice.enums.ReservationStatus;
import md.hajji.equipmentservice.exceptions.*;
import md.hajji.equipmentservice.mappers.ReservationMapper;
import md.hajji.equipmentservice.repositories.EquipmentRepository;
import md.hajji.equipmentservice.repositories.ReservationRepository;
import md.hajji.equipmentservice.services.EquipmentService;
import md.hajji.equipmentservice.services.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final EquipmentRepository equipmentRepository;
    private final EquipmentService equipmentService;
    private final ReservationMapper reservationMapper;


    @Override
    public ReservationResponseDTO createReservation(ReservationRequestDTO reservationRequestDTO) {


        reservationRequestDTO.validate();

        System.out.println("record validate passed");

        Equipment equipment = equipmentRepository
               .findById(reservationRequestDTO.equipmentId())
               .orElseThrow(() -> new ResourceNotFoundException("equipment", reservationRequestDTO.equipmentId()));

        System.out.println("equipment exists passed");

        if (equipment.getStatus() != EquipmentStatus.AVAILABLE)
            throw new EquipmentOutOfServiceException(equipment.getId());

        System.out.println("equipment status passed");

        boolean equipmentIsAlreadyReserved = equipmentService.isAvailableOn(
               reservationRequestDTO.equipmentId(),
               reservationRequestDTO.date(),
               reservationRequestDTO.startTime(),
               reservationRequestDTO.endTime()
        );

        if(!equipmentIsAlreadyReserved)
            throw new EquipmentAlreadyReservedException(equipment.getId());

        System.out.println("equipment available passed");

        Reservation reservation = reservationMapper
               .toReservation(reservationRequestDTO);

        reservation.setEquipment(equipment);

        reservation.setStatus(
               equipment.isRestricted() ?
                       ReservationStatus.WAITLISTED :
                       ReservationStatus.ACCEPTED
        );

        reservationRepository.save(reservation);

       return reservationMapper.toReservationResponseDTO(reservation);
    }

    @Override
    public ReservationResponseDTO getReservation(String id) {
        return reservationRepository.findById(id)
                .map(reservationMapper::toReservationResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("reservation", id));
    }

    @Override
    public List<ReservationResponseDTO> getAllReservations() {
        return reservationRepository.findAll()
                .stream()
                .map(reservationMapper::toReservationResponseDTO)
                .toList();
    }

    @Override
    public List<ReservationResponseDTO> getWaitlistedReservations() {
        return reservationRepository.findByStatus(ReservationStatus.WAITLISTED)
                .stream()
                .map(reservationMapper::toReservationResponseDTO)
                .toList();
    }

    @Override
    public int updateReservationStatus(String id, ReservationStatus status) {
        return reservationRepository.updateReservationStatus(id, status);
    }


    @Override
    public ReservationResponseDTO postPoneReservation(String reservationId, LocalDate date, LocalTime from, LocalTime to) {

        Reservation reservation = reservationRepository
                .findById(reservationId)
                .orElseThrow(() -> new ResourceNotFoundException("reservation", reservationId));

    //neeed implementation idea

        return null;
    }

    @Override
    public Map<LocalDate, List<ReservationResponseDTO>> getReservationWeekCalendarOfEquipment(String equipmentId) {
        LocalDate today = LocalDate.now();
        LocalDate previousMonday = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate nextFriday = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));

        List<Reservation> reservations = reservationRepository.findByEquipmentIdAndDateBetween(equipmentId, previousMonday, nextFriday);

        System.out.println(reservations);

        return reservations
                .stream()
                .filter(Reservation::isActive)
                .map(reservationMapper::toReservationResponseDTO)
                .collect(Collectors.groupingBy(ReservationResponseDTO::date));
    }


}
