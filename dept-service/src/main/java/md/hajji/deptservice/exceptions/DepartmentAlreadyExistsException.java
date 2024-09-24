package md.hajji.deptservice.exceptions;

public class DepartmentAlreadyExistsException extends RuntimeException{

    public DepartmentAlreadyExistsException(String departmentName) {
        super("Department with name " + departmentName + " already exists");
    }
}
