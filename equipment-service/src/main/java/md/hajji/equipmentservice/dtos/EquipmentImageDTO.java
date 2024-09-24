package md.hajji.equipmentservice.dtos;

import md.hajji.equipmentservice.exceptions.InvalidImageFormatException;
import md.hajji.equipmentservice.exceptions.NullResourceFieldException;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

public record EquipmentImageDTO(
        MultipartFile image
)
{
    public void validate(){
        if(image == null || image.isEmpty())
            throw new NullResourceFieldException("equipment image");

        if(!(Objects.equals(image.getContentType(), MediaType.IMAGE_PNG_VALUE) ||
                Objects.equals(image.getContentType(), MediaType.IMAGE_JPEG_VALUE)))
            throw new InvalidImageFormatException();
    }
}
