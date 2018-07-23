package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.registration.services.RegistrationServicesConstants;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.impls.RegistrationPeriodServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.RegistrationPeriodRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.RegistrationPeriodRestServiceImpl;

@Configuration
public class RegistrationPeriodServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "registrationPeriodServiceImpl")
	public RegistrationPeriodService getRegistrationPeriodServiceImpl() {
		RegistrationPeriodServiceImpl service = new RegistrationPeriodServiceImpl();
		service.setRegistrationPeriodRepository(getBean(RegistrationPeriodRepository.class));

		return service;
	}

	@Bean(name = "registrationPeriodServiceImplRestEndPoint")
	public Server registrationPeriodServiceImplRestEndPoint() {
		RegistrationPeriodRestServiceImpl restServiceImpl = new RegistrationPeriodRestServiceImpl();
		restServiceImpl.setNextDecorator(getRegistrationPeriodServiceImpl());

		return createRestEndpoint(restServiceImpl,
				"/rest/" + RegistrationServicesConstants.REGISTRATION_PERIOD_SERVICE_NAME);
	}

}
