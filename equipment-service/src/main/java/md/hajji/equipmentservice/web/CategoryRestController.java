package md.hajji.equipmentservice.web;

import lombok.RequiredArgsConstructor;
import md.hajji.equipmentservice.dtos.CategoryResponseDTO;
import md.hajji.equipmentservice.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryService categoryService;


//    @PostMapping
//    public ResponseEntity<CategoryResponseDTO> createCategory(
//            @RequestBody final String categoryName) {
//        return ResponseEntity.status(HttpStatus.CREATED)
//                .body(categoryService.addCategory(categoryName));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories() {
//        return ResponseEntity.ok(categoryService.getAllCategories());
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<CategoryResponseDTO> getCategory(@PathVariable final Long id) {
//        return ResponseEntity.ok(categoryService.getCategory(id));
//    }
//
//    @PutMapping("{id}")
//    public ResponseEntity<CategoryResponseDTO> renameCategory(
//            @PathVariable final Long id,
//            @RequestBody final String categoryName) {
//
//        return ResponseEntity.ok(categoryService.updateCategory(id, categoryName));
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<CategoryResponseDTO> deleteCategory(@PathVariable final Long id) {
//        categoryService.deleteCategory(id);
//        return ResponseEntity.noContent().build();
//    }
}
