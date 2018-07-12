package za.ac.unisa.myadmin.server.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
public class RestProvidersConfiguration {

	// A JacksonJsonProvider bean is needed to fix Java to JSON conversion issues:
	// "ERROR JAXRSUtils:1793 - No message body writer has been found for class..."
	@Bean
	public JacksonJsonProvider jsonProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public ExceptionHandler exceptionHandler() {
		return new ExceptionHandler();
	}

	@Bean
	public DateParameterHandler dateParameterHandler() {
		return new DateParameterHandler();
	}

	@Bean
	public DateConverterProvider dateConverterProvider() {
		return new DateConverterProvider();
	}

}
