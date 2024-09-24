package md.hajji.equipmentservice.services;

import md.hajji.equipmentservice.dtos.EquipmentImageDTO;
import md.hajji.equipmentservice.dtos.EquipmentRequestDTO;
import md.hajji.equipmentservice.dtos.EquipmentResponseDTO;
import md.hajji.equipmentservice.dtos.EquipmentUpdateDTO;
import md.hajji.equipmentservice.entities.Equipment;
import md.hajji.equipmentservice.enums.EquipmentStatus;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface EquipmentService{

    EquipmentResponseDTO addEquipment(EquipmentRequestDTO equipmentRequestDTO);
    EquipmentResponseDTO updateEquipment(String id, EquipmentUpdateDTO equipmentUpdateDTO);
    EquipmentResponseDTO setEquipmentImage(String id, EquipmentImageDTO equipmentImageDTO);
    void deleteEquipment(String id);
    EquipmentResponseDTO getEquipment(String id);
    List<EquipmentResponseDTO> getAllEquipments();
    boolean existsByName(String name);
    boolean existsByNameExclude(String name, String exclude);
    boolean isAvailableOn(String equipmentId, LocalDate date, LocalTime from, LocalTime to);
    EquipmentStatus getEquipmentStatus(String id);
}
