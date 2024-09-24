package md.hajji.deptservice.web;

import lombok.AllArgsConstructor;
import md.hajji.deptservice.dtos.DepartmentResponseDTO;
import md.hajji.deptservice.services.DepartmentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/departments")
@CrossOrigin("*")
@AllArgsConstructor
public class DepartmentRestController {


    private final DepartmentService departmentService;

    @PostMapping
    public DepartmentResponseDTO createDepartment(@RequestBody final String departmentName) {
        return departmentService.addDepartment(departmentName);
    }

    @GetMapping("{id}")
    public DepartmentResponseDTO getDepartment(@PathVariable final String id) {
        return departmentService.getDepartmentById(id);
    }

    @GetMapping
    public List<DepartmentResponseDTO> getAllDepartments() {
        return departmentService.getDepartments();
    }

    @GetMapping("exists/{name}")
    public boolean existsDepartment(@PathVariable final String name) {
        return departmentService.existsDepartmentByName(name);
    }

    @GetMapping("exists/{name}/exclude/{id}")
    public boolean existsDepartment(@PathVariable final String name, @PathVariable final String id) {
        return departmentService.existsDepartmentByName(name, id);
    }

    @PutMapping("{id}")
    public DepartmentResponseDTO updateDepartment(@PathVariable final String id,
                                                  @RequestBody final String departmentName) {

        return departmentService.updateDepartment(id, departmentName);
    }


    @DeleteMapping("{id}")
    public void deleteDepartment(@PathVariable final String id) {
        departmentService.deleteDepartment(id);
    }

}
