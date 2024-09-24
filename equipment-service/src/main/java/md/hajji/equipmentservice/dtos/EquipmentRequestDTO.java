package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.enums.EquipmentStatus;
import md.hajji.equipmentservice.exceptions.InvalidImageFormatException;
import md.hajji.equipmentservice.exceptions.NullResourceFieldException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public record EquipmentRequestDTO(
        String name,
        MultipartFile image,
        boolean restricted,
        Long categoryId
) {
    public void validate(){
        if (name == null || name.isBlank())
            throw new NullResourceFieldException("equipment name");

        if(image == null || image.isEmpty())
            throw new NullResourceFieldException("equipment image");

        if(!(Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE) ||
                Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE)))
            throw new InvalidImageFormatException();

        if (categoryId == null || categoryId < 0)
            throw new NullResourceFieldException("category id");
    }
}
