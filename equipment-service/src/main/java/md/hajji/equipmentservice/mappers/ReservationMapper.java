package md.hajji.equipmentservice.mappers;

import md.hajji.equipmentservice.dtos.ReservationRequestDTO;
import md.hajji.equipmentservice.dtos.ReservationResponseDTO;
import md.hajji.equipmentservice.entities.Reservation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EquipmentMapper.class})
public interface ReservationMapper {

    ReservationResponseDTO toReservationResponseDTO(Reservation reservation);
    Reservation toReservation(ReservationRequestDTO reservationRequestDTO);
}
