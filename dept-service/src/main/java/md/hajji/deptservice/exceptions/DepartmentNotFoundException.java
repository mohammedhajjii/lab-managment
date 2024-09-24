package md.hajji.deptservice.exceptions;

public class DepartmentNotFoundException extends RuntimeException{

    public DepartmentNotFoundException(String searchBy) {
        super("Department" + searchBy + " not found");
    }
}
