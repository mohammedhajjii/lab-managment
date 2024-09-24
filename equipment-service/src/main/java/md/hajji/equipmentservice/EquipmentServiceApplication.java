package md.hajji.equipmentservice;

import md.hajji.equipmentservice.configurations.QrCodeDimension;
import md.hajji.equipmentservice.dtos.CategoryResponseDTO;
import md.hajji.equipmentservice.entities.Category;
import md.hajji.equipmentservice.mappers.CategoryMapper;
import md.hajji.equipmentservice.repositories.CategoryRepository;
import md.hajji.equipmentservice.services.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableConfigurationProperties({QrCodeDimension.class})
public class EquipmentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EquipmentServiceApplication.class, args);
    }


    CommandLineRunner start(CategoryService categoryService){
        return args -> {
            List<CategoryResponseDTO> categories = List.of(
                    categoryService.addCategory("c1"),
                    categoryService.addCategory("c2"),
                    categoryService.addCategory("c3")
            );

            System.out.println("---------------- categories ------------------");
            categories.forEach(System.out::println);
            System.out.println("------------- getById -----------------");
            CategoryResponseDTO c1 = categoryService.getCategory( categories.get(0).id());
            System.out.println(c1);
            System.out.println("------------- getAll -----------------");
            categoryService.getAllCategories()
                    .forEach(System.out::println);
            System.out.println("------------- update ----------------");
            CategoryResponseDTO updatedCategory = categoryService.updateCategory(c1.id(), "c11");
            System.out.println(updatedCategory);
            System.out.println("------------- delete ---------------");
            categoryService.deleteCategory(c1.id());
            System.out.println("------------- getAll ----------------");
            categoryService.getAllCategories().forEach(System.out::println);
        };
    }
}
