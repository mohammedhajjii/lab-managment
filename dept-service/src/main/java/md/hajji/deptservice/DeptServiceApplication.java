package md.hajji.deptservice;

import md.hajji.deptservice.services.DepartmentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeptServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeptServiceApplication.class, args);
	}


	CommandLineRunner start(DepartmentService departmentService){
		return args -> {
			departmentService.addDepartment("computer sciences");
			departmentService.addDepartment("Mechanics and robotics");
			departmentService.addDepartment("Chemistry and products");


			System.out.println("--------------------------- all --------------------");

			departmentService.getDepartments()
					.forEach(System.out::println);
		};
	}

}
