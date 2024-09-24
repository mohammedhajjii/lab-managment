package md.hajji.deptservice.exceptions;

public class NullDeptNameException extends RuntimeException{

    public NullDeptNameException() {
        super("name of department cannot be null");
    }
}
