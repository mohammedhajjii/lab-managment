package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.enums.EquipmentStatus;
import md.hajji.equipmentservice.exceptions.InvalidImageFormatException;
import md.hajji.equipmentservice.exceptions.NullResourceFieldException;
import org.springframework.http.MediaType;

import java.util.Objects;


public record EquipmentUpdateDTO(
        String name,
        EquipmentStatus status,
        boolean restricted,
        Long categoryId
) {
    public void validate(){
        if (name == null || name.isBlank())
            throw new NullResourceFieldException("equipment name");

        if (categoryId == null || categoryId < 0)
            throw new NullResourceFieldException("category id");
    }
}
