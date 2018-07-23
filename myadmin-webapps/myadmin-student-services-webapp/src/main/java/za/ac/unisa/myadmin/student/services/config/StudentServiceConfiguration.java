package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.impls.StudentServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.StudentRepository;
import za.ac.unisa.myadmin.student.services.rest.impls.StudentRestServiceImpl;

@Configuration
public class StudentServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "studentServiceimpl")
	public StudentService getStudentServiceImpl() {
		StudentServiceImpl service = new StudentServiceImpl();
		service.setStudentRepository(getBean(StudentRepository.class));

		return service;
	}

	@Bean(name = "studentServiceImplRestEndPoint")
	public Server studentServiceImplRestEndPoint() {
		StudentRestServiceImpl restServiceImpl = new StudentRestServiceImpl();
		restServiceImpl.setNextDecorator(getStudentServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + StudentServicesConstants.STUDENT_SERVICE_NAME);
	}

}
