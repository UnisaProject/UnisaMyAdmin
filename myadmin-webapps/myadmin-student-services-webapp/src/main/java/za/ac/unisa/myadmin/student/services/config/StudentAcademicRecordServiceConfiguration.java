package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.unisa.myadmin.generic.services.UnisaGenericService;
import za.ac.unisa.myadmin.module.services.AcademicModuleRecordService;
import za.ac.unisa.myadmin.qualification.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.decorators.AcademicModuleRecordServiceComplianceDecorator;
import za.ac.unisa.myadmin.student.services.decorators.AcademicModuleRecordServiceValidationDecorator;
import za.ac.unisa.myadmin.student.services.decorators.StudentAcademicRecordServiceComplianceDecorator;
import za.ac.unisa.myadmin.student.services.decorators.StudentAcademicRecordServiceValidationDecorator;
import za.ac.unisa.myadmin.student.services.impls.AcademicModuleRecordServiceImpl;
import za.ac.unisa.myadmin.student.services.impls.RegistrationPeriodServiceImpl;
import za.ac.unisa.myadmin.student.services.impls.StudentAcademicRecordServiceImpl;
import za.ac.unisa.myadmin.student.services.impls.UnisaGenericServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;
import za.ac.unisa.myadmin.student.services.repositories.GenericMessageRepository;
import za.ac.unisa.myadmin.student.services.repositories.RegistrationPeriodRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.AcademicModuleRecordRestServiceImpl;
import za.ac.unisa.myadmin.student.services.rest.impls.StudentAcademicRecordRestServiceImpl;

@Configuration
public class StudentAcademicRecordServiceConfiguration extends AbstractServiceConfiguration {
	@Bean(name = "genericeServiceImpl")
	public UnisaGenericService getUnisaGenericServiceImpl() {
		UnisaGenericServiceImpl service = new UnisaGenericServiceImpl();
		service.setGenericMessageRepository(getBean(GenericMessageRepository.class));
		service.setGenericCodeRepository(getBean(GenericCodeRepository.class));

		return service;
	}
	@Bean(name = "studentAcademicRecordService")
	public StudentAcademicRecordService getStudentAcademicRecordServiceImpl() {
		StudentAcademicRecordServiceImpl service = new StudentAcademicRecordServiceImpl();
		service.setGenericService(getUnisaGenericServiceImpl());
		//service.setGenericCodeRepository(getBean(GenericCodeRepository.class));

		return service;
	}

	@Bean(name = "academicModuleRecordService")
	public AcademicModuleRecordService getAcademicModuleRecordServiceImpl() {
		AcademicModuleRecordServiceImpl service = new AcademicModuleRecordServiceImpl();
		//service.setGenericCodeRepository(getBean(GenericCodeRepository.class));

		return service;
	}

	@Bean("academicModuleRecordServiceValidationDecorators")
	public AcademicModuleRecordService getAcademicModuleRecordServiceDecorators() {
		AcademicModuleRecordServiceValidationDecorator validationDecorator = new AcademicModuleRecordServiceValidationDecorator();
		//validationDecorator.setStudentService(getBean(StudentService.class));
		//validationDecorator.setGenericMessageRepository(getBean(GenericMessageRepository.class));
		validationDecorator.setNextDecorator(getAcademicModuleRecordServiceImpl());

		AcademicModuleRecordServiceComplianceDecorator complianceDecorator = new AcademicModuleRecordServiceComplianceDecorator();
		complianceDecorator.setNextDecorator(validationDecorator);

		return complianceDecorator;
	}

	@Bean("studentAcademicRecordServiceValidationDecorator")
	public StudentAcademicRecordService studentAcademicRecordServiceValidationDecorator() {
		StudentAcademicRecordServiceValidationDecorator validationDecorator = new StudentAcademicRecordServiceValidationDecorator();
		validationDecorator.setStudentService(getBean(StudentService.class));
		validationDecorator.setGenericService(getUnisaGenericServiceImpl());
		//validationDecorator.setGenericMessageRepository(getBean(GenericMessageRepository.class));
		validationDecorator.setNextDecorator(getStudentAcademicRecordServiceImpl());

		StudentAcademicRecordServiceComplianceDecorator complianceDecorator = new StudentAcademicRecordServiceComplianceDecorator();
		complianceDecorator.setNextDecorator(validationDecorator);

		return complianceDecorator;
	}

	@Bean(name = "studentAcademicRecordImplRestEndPoint")
	public Server studentAcademicRecordImplRestEndPoint() {
		StudentAcademicRecordRestServiceImpl restServiceImpl = new StudentAcademicRecordRestServiceImpl();
		restServiceImpl.setNextDecorator(studentAcademicRecordServiceValidationDecorator());

		return createRestEndpoint(restServiceImpl, "/rest/" + StudentServicesConstants.STUDENT_ACADEMIC_RECORD_SERVICE_NAME);
	}

	@Bean(name = "academicModuleRecordServiceImplRestEndPoint")
	public Server AcademicModuleRecordServiceImplRestEndPoint() {
		AcademicModuleRecordRestServiceImpl restServiceImpl = new AcademicModuleRecordRestServiceImpl();
		restServiceImpl.setNextDecorator(getAcademicModuleRecordServiceDecorators());

		return createRestEndpoint(restServiceImpl, "/rest/" + StudentServicesConstants.STUDENT_ACADEMIC_MODULE_RECORD_SERVICE_NAME);
	}
}
