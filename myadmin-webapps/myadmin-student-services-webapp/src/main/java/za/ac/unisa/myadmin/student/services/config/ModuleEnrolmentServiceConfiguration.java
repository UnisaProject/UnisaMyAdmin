package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.ModuleServicesConstants;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.impls.ModuleEnrolmentServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.ModuleEnrolmentRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.ModuleEnrolmentRestServiceImpl;

@Configuration
public class ModuleEnrolmentServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "moduleEnrolmentServiceImpl")
	public ModuleEnrolmentService getModuleEnrolmentServiceImpl() {
		ModuleEnrolmentServiceImpl service = new ModuleEnrolmentServiceImpl();
		service.setModuleEnrolmentRepository(getBean(ModuleEnrolmentRepository.class));
		service.setRegistrationPeriodService(getBean(RegistrationPeriodService.class));
		service.setStudentAnnualService(getBean(StudentAnnualService.class));
		return service;
	}


	@Bean(name = "moduleEnrolmentServiceImplRestEndPoint")
	public Server studyMaterialServiceImplRestEndPoint() {
		ModuleEnrolmentRestServiceImpl restServiceImpl = new ModuleEnrolmentRestServiceImpl();
		restServiceImpl.setNextDecorator(getModuleEnrolmentServiceImpl());
		return createRestEndpoint(restServiceImpl, "/rest/" + ModuleServicesConstants.MODULE_ENROLMENT_SERVICE_NAME);
	}

}
