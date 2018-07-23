package za.ac.unisa.myadmin.tracking.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TrackingServicesApplication extends SpringBootServletInitializer {

	// By extending SpringBootServletInitializer and override the configure method
	// so that the Spring application sources can be configured by
	// SpringApplicationBuilder. This enables Spring component detection inside a
	// Jboss/Wildfly application server.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TrackingServicesApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TrackingServicesApplication.class);
		app.run(args);
	}

}
