package md.hajji.equipmentservice.dtos;


public record EquipmentReservationResponseDTO (
        String id,
        String name,
        String image,
        String qrcode
) {}
