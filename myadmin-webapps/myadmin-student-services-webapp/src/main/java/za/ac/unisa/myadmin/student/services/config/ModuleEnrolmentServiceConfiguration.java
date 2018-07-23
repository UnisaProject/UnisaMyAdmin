package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.impls.ModuleEnrolmentServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.ModuleEnrolmentRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.StudyMaterialRestServiceImpl;
import za.ac.unisa.myadmin.studymaterial.integration.services.StudyMaterialWebServiceimpl;

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

	@Bean(name = "studyMaterialWebServiceimpl")
	public StudyMaterialWebServiceimpl getStudyMaterialWebServiceimpl() {
		return new StudyMaterialWebServiceimpl();
	}

	@Bean(name = "studyMaterialServiceImplRestEndPoint")
	public Server studyMaterialServiceImplRestEndPoint() {
		StudyMaterialRestServiceImpl restServiceImpl = new StudyMaterialRestServiceImpl();
		restServiceImpl.setNextDecorator(getModuleEnrolmentServiceImpl());
		restServiceImpl.setScmClientService(getBean(StudyMaterialWebServiceimpl.class));

		return createRestEndpoint(restServiceImpl, "/rest/studymaterialservice");
	}

}
