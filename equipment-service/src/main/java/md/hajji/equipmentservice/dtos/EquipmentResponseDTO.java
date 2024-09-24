package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.enums.EquipmentStatus;

public record EquipmentResponseDTO(
        String id,
        String name,
        String image,
        String qrcode,
        boolean restricted,
        EquipmentStatus status,
        CategoryResponseDTO category
) {}
