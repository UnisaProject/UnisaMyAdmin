package za.ac.unisa.myadmin.exam.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServicesApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ServicesApplication.class);
		app.run(args);
	}

}
