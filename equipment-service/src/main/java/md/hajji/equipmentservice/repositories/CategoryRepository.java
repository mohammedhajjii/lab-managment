package md.hajji.equipmentservice.repositories;

import md.hajji.equipmentservice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNullApi;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    boolean existsById(Long id);

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN count(c) > 0 THEN true ELSE false END " +
            "FROM Category c WHERE c.name = :name AND c.id <> :id")
    boolean existsByNameExclude(@Param("name") String name, @Param("id") Long exclude);
}
