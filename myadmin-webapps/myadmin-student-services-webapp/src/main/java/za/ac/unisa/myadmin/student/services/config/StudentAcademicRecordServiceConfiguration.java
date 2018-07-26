package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.decorators.StudentAcademicRecordServiceComplianceDecorator;
import za.ac.unisa.myadmin.student.services.decorators.StudentAcademicRecordServiceValidationDecorator;
import za.ac.unisa.myadmin.student.services.impls.StudentAcademicRecordServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;
import za.ac.unisa.myadmin.student.services.repositories.GenericMessageRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.StudentAcademicRecordRestServiceImpl;

@Configuration
public class StudentAcademicRecordServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "studentAcademicRecordService")
	public StudentAcademicRecordService getStudentAcademicRecordServiceImpl() {
		StudentAcademicRecordServiceImpl service = new StudentAcademicRecordServiceImpl();
		service.setGenericCodeRepository(getBean(GenericCodeRepository.class));

		return service;
	}

	@Bean("studentAcademicRecordServiceValidationDecorator")
	public StudentAcademicRecordService studentAcademicRecordServiceValidationDecorator() {
		StudentAcademicRecordServiceValidationDecorator validationDecorator = new StudentAcademicRecordServiceValidationDecorator();
		validationDecorator.setStudentService(getBean(StudentService.class));
		validationDecorator.setGenericMessageRepository(getBean(GenericMessageRepository.class));
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
}
