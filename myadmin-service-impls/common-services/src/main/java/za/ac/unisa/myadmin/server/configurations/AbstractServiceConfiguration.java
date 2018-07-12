package za.ac.unisa.myadmin.server.configurations;

import java.util.ArrayList;
import java.util.List;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;


public abstract class AbstractServiceConfiguration {

	@Autowired
	protected ConfigurableApplicationContext applicationContext;

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	@SuppressWarnings("unchecked")
	protected Server createRestEndpoint(Object implementor, String address) {
		JAXRSServerFactoryBean serverFactoryBean = new JAXRSServerFactoryBean();
		serverFactoryBean.setServiceBean(implementor);
		serverFactoryBean.setAddress(address.toLowerCase());
		serverFactoryBean.setProviders(getRestProviders());
		serverFactoryBean.setBus(getBean(SpringBus.class));
		return serverFactoryBean.create();
	}

	protected <T extends Object> T getBean(Class<T> requiredType) throws BeansException {
		return applicationContext.getBean(requiredType);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private List getRestProviders() {
		List provider = new ArrayList();
		provider.add(getBean(JacksonJsonProvider.class));
		provider.add(getBean(DateParameterHandler.class));
		provider.add(getBean(DateConverterProvider.class));
		provider.add(getBean(ExceptionHandler.class));
		return provider;
	}

}
