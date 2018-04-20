package za.ac.unisa.myadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class ExamTimetableApplication extends SpringBootServletInitializer {

	// By extending SpringBootServletInitializer and override the configure method
	// so that the Spring application sources can be configured by
	// SpringApplicationBuilder. This enables Spring component detection inside a
	// Jboss/Wildfly application server.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExamTimetableApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ExamTimetableApplication.class);
		app.run(args);
	}

}
