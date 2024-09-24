package md.hajji.deptservice.repositories;

import md.hajji.deptservice.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepository extends JpaRepository<Department, String> {
    boolean existsByName(String name);

    @Query("select case when count(d) > 0 then true  else false end " +
            "from Department d where d.name = :name AND d.id <> :id")
    boolean existsByName(@Param("name") String departmentName, @Param("id") String exclude);
}
