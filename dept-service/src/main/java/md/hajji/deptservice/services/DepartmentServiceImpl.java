package md.hajji.deptservice.services;

import lombok.AllArgsConstructor;
import md.hajji.deptservice.dtos.DepartmentResponseDTO;
import md.hajji.deptservice.entities.Department;
import md.hajji.deptservice.exceptions.DepartmentAlreadyExistsException;
import md.hajji.deptservice.exceptions.DepartmentNotFoundException;
import md.hajji.deptservice.exceptions.NullDeptNameException;
import md.hajji.deptservice.repositories.DepartmentRepository;
import md.hajji.deptservice.utils.DepartmentUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;


    @Override
    public DepartmentResponseDTO addDepartment(String departmentName) {

        if (departmentName == null || departmentName.isBlank())
            throw new NullDeptNameException();

        if (departmentRepository.existsByName(departmentName))
            throw new DepartmentAlreadyExistsException(departmentName);

        Department department = departmentRepository.save(
                Department.builder()
                        .name(departmentName)
                        .code(DepartmentUtils.normalizeDepartmentName(departmentName))
                        .creationTimestamp(new Date().getTime())
                        .build()
        );

        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    @Override
    public List<DepartmentResponseDTO> getDepartments() {
        return  departmentRepository.findAll()
                .stream()
                .map(department -> modelMapper.map(department, DepartmentResponseDTO.class))
                .toList();
    }


    @Override
    public DepartmentResponseDTO getDepartmentById(String id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        return modelMapper.map(department, DepartmentResponseDTO.class);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(String departmentId, String departmentName) {

        if(departmentId == null || departmentId.isBlank())
            throw new NullDeptNameException();


        if (departmentRepository.existsByName(departmentName))
            throw new DepartmentAlreadyExistsException(departmentName);

        Department department = departmentRepository
                .findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException(departmentName));

        department.setName(departmentName);
        department.setCode(DepartmentUtils.normalizeDepartmentName(departmentName));


        return modelMapper.map(
                departmentRepository.save(department),
                DepartmentResponseDTO.class
        );
    }

    @Override
    public void deleteDepartment(String id) {
        Department department = departmentRepository
                .findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));
        departmentRepository.delete(department);
    }

    @Override
    public boolean existsDepartmentByName(String departmentName) {
        return departmentRepository.existsByName(departmentName);
    }

    @Override
    public boolean existsDepartmentByName(String departmentName, String exclude) {
        return departmentRepository.existsByName(departmentName, exclude);
    }
}
