package md.hajji.equipmentservice.services.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.CategoryResponseDTO;
import md.hajji.equipmentservice.entities.Category;
import md.hajji.equipmentservice.exceptions.DuplicateResourceException;
import md.hajji.equipmentservice.exceptions.NullResourceFieldException;
import md.hajji.equipmentservice.exceptions.ResourceNotFoundException;
import md.hajji.equipmentservice.mappers.CategoryMapper;
import md.hajji.equipmentservice.repositories.CategoryRepository;
import md.hajji.equipmentservice.services.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    public CategoryResponseDTO addCategory(String categoryName) {

        if(categoryName == null || categoryName.isBlank())
            throw new NullResourceFieldException("category name");

        if (categoryRepository.existsByName(categoryName))
            throw new DuplicateResourceException("categoryName", categoryName);

        Category category = Category.builder()
                .name(categoryName)
                .build();

        categoryRepository.save(category);

        return categoryMapper.toCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, String categoryName) {

        if (categoryName == null || categoryName.isBlank())
            throw new NullResourceFieldException("category name");

        if(categoryRepository.existsByNameExclude(categoryName, id))
            throw new DuplicateResourceException("categoryName", categoryName);

        Category  category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", String.valueOf(id)));

        category.setName(categoryName);

        return categoryMapper.toCategoryResponseDTO(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("category", String.valueOf(id)));
        categoryRepository.delete(category);

    }

    @Override
    public CategoryResponseDTO getCategory(Long id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponseDTO)
                .orElseThrow(() -> new ResourceNotFoundException("category", String.valueOf(id)));
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::toCategoryResponseDTO)
                .toList();
    }

    @Override
    public boolean existsCategoryByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public boolean existsCategoryByNameExcluding(String name, Long excludeId) {
        return categoryRepository.existsByNameExclude(name, excludeId);
    }


}
