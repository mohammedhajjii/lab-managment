package md.hajji.equipmentservice.repositories;

import md.hajji.equipmentservice.entities.Category;
import md.hajji.equipmentservice.entities.Equipment;
import md.hajji.equipmentservice.enums.EquipmentStatus;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EquipmentRepository extends JpaRepository<Equipment, String> {

    boolean existsById(String id);

    boolean existsByName(String name);

    @Query("SELECT CASE WHEN count(e) > 0 THEN true ELSE false END " +
            "FROM Equipment e WHERE e.name = :name AND e.id <> :exclude")
    boolean existsByNameExclude(String name, String exclude);


    @Query("SELECT e.status FROM Equipment e WHERE e.id = :id")
    EquipmentStatus findEquipmentStatus(@Param("id") String id);
}
