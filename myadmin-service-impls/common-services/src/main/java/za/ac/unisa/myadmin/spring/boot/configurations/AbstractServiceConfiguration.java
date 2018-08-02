package za.ac.unisa.myadmin.spring.boot.configurations;

import java.util.Arrays;

import javax.servlet.Servlet;
import javax.servlet.ServletContextListener;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * This abstract class must be extended by the service configuration
 * implementations in the Spring Boot webapp projects.
 * 
 * e.g: public class ExamServicesConfiguration extends
 * AbstractServiceConfiguration
 * 
 * It provides the operation for wiring and instantiating the CXF rest service
 * implementations and make them available via the Spring bus to the Spring
 * framework and also publish the it at the given HTTP address.
 * 
 * This class also provide various handlers to the rest layer such as performing
 * date-string formatting and handle application exceptions as HTTP errors and
 * provide detail information about these errors in the HTTP Response headers.
 * 
 * @author Jannie Louwrens
 *
 */
@ComponentScan({ "za.ac.unisa.myadmin.spring.boot.configurations" })
public abstract class AbstractServiceConfiguration {

	@Autowired
	protected ConfigurableApplicationContext applicationContext;

	// the SpringBus supplies extensions for Apache CXF to work with the Spring
	// Framework
	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	// A JacksonJsonProvider bean is needed to fix Java to JSON conversion issues:
	// "ERROR JAXRSUtils:1793 - No message body writer has been found for class..."
	@Bean
	public JacksonJsonProvider jacksonJsonProvider() {
		return new JacksonJsonProvider();
	}

	@Bean
	public RestExceptionHandler restExceptionHandler() {
		return new RestExceptionHandler();
	}

	@Bean
	public DateParameterHandler dateParameterHandler() {
		return new DateParameterHandler();
	}

	@Bean
	public DateConverterProvider dateConverterProvider() {
		return new DateConverterProvider();
	}

	@Bean
	public ServletContextListener defaultMetricsServletContextListener() {
		return new DefaultMetricsServletContextListener();
	}

	@Bean
	public ServletRegistrationBean<Servlet> defaultConsoleReporterMetricsServlet() {
		return new ServletRegistrationBean<>(new DefaultConsoleReporterMetricsServlet(), "/metrics-console/*");
	}

	// This method will publish the rest endpoint at the given HTTP address
	protected Server createRestEndpoint(Object implementor, String address) {
		JAXRSServerFactoryBean serverFactoryBean = new JAXRSServerFactoryBean();
		serverFactoryBean.setServiceBeans(Arrays.asList(implementor));
		serverFactoryBean.setAddress(address.toLowerCase());
		serverFactoryBean.setProviders(Arrays.asList(jacksonJsonProvider(), restExceptionHandler(),
				dateParameterHandler(), dateConverterProvider()));
		serverFactoryBean.setBus(springBus());
		return serverFactoryBean.create();
	}

	protected <T extends Object> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

}
