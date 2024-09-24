package md.hajji.equipmentservice.services;

import md.hajji.equipmentservice.dtos.CategoryResponseDTO;
import java.util.List;

public interface CategoryService {
    CategoryResponseDTO addCategory(String categoryName);
    CategoryResponseDTO updateCategory(Long id, String categoryName);
    void deleteCategory(Long id);
    CategoryResponseDTO getCategory(Long id);
    List<CategoryResponseDTO> getAllCategories();
    boolean existsCategoryByName(String name);
    boolean existsCategoryByNameExcluding(String name, Long excludeId);
}

