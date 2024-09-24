package md.hajji.deptservice.services;

import md.hajji.deptservice.dtos.DepartmentResponseDTO;
import java.util.List;

public interface DepartmentService {
    DepartmentResponseDTO addDepartment( String departmentName);
    List<DepartmentResponseDTO> getDepartments();
    DepartmentResponseDTO getDepartmentById(String id);
    DepartmentResponseDTO updateDepartment(String departmentId, String departmentName);
    void deleteDepartment(String id);
    boolean existsDepartmentByName(String departmentName);
    boolean existsDepartmentByName(String departmentName, String exclude);
}
