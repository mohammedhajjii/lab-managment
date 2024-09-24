package md.hajji.equipmentservice.mappers;

import lombok.SneakyThrows;
import md.hajji.equipmentservice.dtos.EquipmentRequestDTO;
import md.hajji.equipmentservice.dtos.EquipmentReservationResponseDTO;
import md.hajji.equipmentservice.dtos.EquipmentResponseDTO;
import md.hajji.equipmentservice.dtos.EquipmentUpdateDTO;
import md.hajji.equipmentservice.entities.Equipment;
import md.hajji.equipmentservice.mappers.annotations.Base64BytesEncoder;
import md.hajji.equipmentservice.mappers.annotations.MultipartFileBytesMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;


@Mapper(componentModel = "spring", uses = {ImageMapper.class, CategoryMapper.class})
public interface EquipmentMapper {


   @Mappings({
           @Mapping(source = "image", target = "image", qualifiedBy = Base64BytesEncoder.class),
           @Mapping(source = "qrcode", target = "qrcode", qualifiedBy = Base64BytesEncoder.class)
   })
    EquipmentResponseDTO toEquipmentResponseDTO(Equipment equipment);


    @Mappings({
            @Mapping(source = "categoryId", target = "category.id"),
            @Mapping(source = "image", target = "image", qualifiedBy = MultipartFileBytesMapper.class)
    })
    Equipment toEquipment(EquipmentRequestDTO equipmentRequestDTO);


    @Mappings({
            @Mapping(source = "qrcode", target = "qrcode", qualifiedBy = Base64BytesEncoder.class),
            @Mapping(source = "image", target = "image", qualifiedBy = Base64BytesEncoder.class)
    })
    EquipmentReservationResponseDTO toEquipmentReservationResponseDTO(Equipment equipment);


//    @Mappings({
//            @Mapping(source = "categoryId", target = "category.id", ignore = true)
//    })
    void updateEquipmentFromExistingEquipmentRequestDTO(EquipmentUpdateDTO equipmentUpdateDTO, @MappingTarget Equipment equipment);

}
