package md.hajji.equipmentservice.mappers;

import md.hajji.equipmentservice.dtos.CategoryResponseDTO;
import md.hajji.equipmentservice.entities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDTO toCategoryResponseDTO(Category category);
    Category toCategory(CategoryResponseDTO categoryResponseDTO);
}
