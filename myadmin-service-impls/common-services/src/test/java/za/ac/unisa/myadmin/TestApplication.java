package za.ac.unisa.myadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration
@SpringBootApplication
public class TestApplication {

	public static void main(String... args) {
		SpringApplication.run(TestApplication.class);
	}
}