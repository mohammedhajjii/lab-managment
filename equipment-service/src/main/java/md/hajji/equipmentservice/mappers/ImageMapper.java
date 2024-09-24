package md.hajji.equipmentservice.mappers;

import lombok.SneakyThrows;
import md.hajji.equipmentservice.mappers.annotations.Base64BytesEncoder;
import md.hajji.equipmentservice.mappers.annotations.MultipartFileBytesMapper;
import org.mapstruct.Mapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Base64BytesEncoder
    default String base64BytesEncode(byte[] bytes){
        return "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
    }

    @MultipartFileBytesMapper
    @SneakyThrows(IOException.class)
    default byte[] multipartFileToBytes(MultipartFile multipartFile){
        if(multipartFile == null)
            return null;
        return multipartFile.getBytes();
    }
}
