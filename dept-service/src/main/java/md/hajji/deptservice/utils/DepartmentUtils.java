package md.hajji.deptservice.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DepartmentUtils {

    public static String normalizeDepartmentName(String departmentName) {
        return Arrays.stream(departmentName.split(" "))
                .map(item -> String.valueOf(item.toUpperCase().charAt(0)))
                .collect(Collectors.joining(""));
    }
}
