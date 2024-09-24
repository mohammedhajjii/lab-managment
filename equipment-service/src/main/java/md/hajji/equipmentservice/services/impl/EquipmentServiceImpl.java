package md.hajji.equipmentservice.services.impl;

import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.EquipmentImageDTO;
import md.hajji.equipmentservice.dtos.EquipmentRequestDTO;
import md.hajji.equipmentservice.dtos.EquipmentResponseDTO;
import md.hajji.equipmentservice.dtos.EquipmentUpdateDTO;
import md.hajji.equipmentservice.entities.Category;
import md.hajji.equipmentservice.entities.Equipment;
import md.hajji.equipmentservice.entities.Reservation;
import md.hajji.equipmentservice.enums.EquipmentStatus;
import md.hajji.equipmentservice.exceptions.DuplicateResourceException;
import md.hajji.equipmentservice.exceptions.InvalidImageFormatException;
import md.hajji.equipmentservice.exceptions.NullResourceFieldException;
import md.hajji.equipmentservice.exceptions.ResourceNotFoundException;
import md.hajji.equipmentservice.mappers.EquipmentMapper;
import md.hajji.equipmentservice.mappers.ImageMapper;
import md.hajji.equipmentservice.repositories.CategoryRepository;
import md.hajji.equipmentservice.repositories.EquipmentRepository;
import md.hajji.equipmentservice.repositories.ReservationRepository;
import md.hajji.equipmentservice.services.EquipmentService;
import md.hajji.equipmentservice.services.QrCodeService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@Transactional
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

    private final EquipmentRepository equipmentRepository;
    private final ReservationRepository reservationRepository;
    private final CategoryRepository categoryRepository;
    private final EquipmentMapper equipmentMapper;
    private final ImageMapper imageMapper;
    private final QrCodeService qrCodeService;

    @Override
    public EquipmentResponseDTO addEquipment(EquipmentRequestDTO equipmentRequestDTO) {

        equipmentRequestDTO.validate();

        if (equipmentRepository.existsByName(equipmentRequestDTO.name()))
            throw new DuplicateResourceException("equipment name", equipmentRequestDTO.name());

        if (!categoryRepository.existsById(equipmentRequestDTO.categoryId()))
            throw new ResourceNotFoundException("category", String.valueOf(equipmentRequestDTO.categoryId()));

        Equipment equipment = equipmentMapper.toEquipment(equipmentRequestDTO);
        equipment.setStatus(EquipmentStatus.AVAILABLE);
        equipmentRepository.save(equipment);

        equipment.setQrcode(qrCodeService.generateQrCodeFromString(equipment.getId()));

        equipmentRepository.save(equipment);

        return equipmentMapper.toEquipmentResponseDTO(equipment);
    }

    @Override
    public EquipmentResponseDTO updateEquipment(String id, EquipmentUpdateDTO equipmentUpdateDTO) {

        equipmentUpdateDTO.validate();

        if (equipmentRepository.existsByNameExclude(equipmentUpdateDTO.name(), id))
            throw new DuplicateResourceException("equipment name", equipmentUpdateDTO.name());


        Equipment equipment = equipmentRepository.findById(id)
                        .orElseThrow(() -> new ResourceNotFoundException("equipment", id));

        if (!equipmentUpdateDTO.categoryId().equals(equipment.getCategory().getId())){
            long categoryId = equipmentUpdateDTO.categoryId();
            Category category = categoryRepository
                    .findById(categoryId)
                    .orElseThrow(() -> new ResourceNotFoundException("'category'", String.valueOf(categoryId)));
            equipment.setCategory(category);
        }

        equipmentMapper.updateEquipmentFromExistingEquipmentRequestDTO(equipmentUpdateDTO, equipment);

        equipmentRepository.save(equipment);

        return equipmentMapper.toEquipmentResponseDTO(equipment);
    }

    @Override
    public EquipmentResponseDTO setEquipmentImage(String id, EquipmentImageDTO equipmentImageDTO) {
        equipmentImageDTO.validate();

        Equipment equipment = equipmentRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("equipment", id));

        equipment.setImage(imageMapper.multipartFileToBytes(equipmentImageDTO.image()));
        equipmentRepository.save(equipment);

        return equipmentMapper.toEquipmentResponseDTO(equipment);
    }

    @Override
    public void deleteEquipment(String id) {
        Equipment equipment = equipmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("equipment", id));
        equipmentRepository.delete(equipment);
    }

    @Override
    public EquipmentResponseDTO getEquipment(String id) {
        return equipmentRepository.findById(id)
                .map(equipmentMapper::toEquipmentResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("equipment", id));
    }

    @Override
    public List<EquipmentResponseDTO> getAllEquipments() {
        return equipmentRepository.findAll()
                .stream()
                .map(equipmentMapper::toEquipmentResponseDTO)
                .toList();
    }


    @Override
    public boolean existsByName(String name) {
        return equipmentRepository.existsByName(name);
    }

    @Override
    public boolean existsByNameExclude(String name, String exclude) {
        return equipmentRepository.existsByNameExclude(name, exclude);
    }

    @Override
    public boolean isAvailableOn(String equipmentId, LocalDate date, LocalTime from, LocalTime to) {
        return reservationRepository
                .findByEquipmentIdAndDateEquals(equipmentId, date)
                .stream()
                .filter(Reservation::isActive)
                .noneMatch(reservation -> reservation.hasOverlapWith(from, to));
    }

    @Override
    public EquipmentStatus getEquipmentStatus(String id) {
        return equipmentRepository.findById(id)
                .map(Equipment::getStatus)
                .orElseThrow(() -> new ResourceNotFoundException("equipment", id));
    }
}
