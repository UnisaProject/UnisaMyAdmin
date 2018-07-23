package za.ac.unisa.myadmin.student.services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.impls.StudentAnnualServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.StudentAnnualRepository;

@Configuration
public class StudentAnnualServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "studentAnnualServiceImpl")
	public StudentAnnualService getStudentAnnualServiceimpl() {
		StudentAnnualServiceImpl service = new StudentAnnualServiceImpl();
		service.setStudentAnnualRepository(getBean(StudentAnnualRepository.class));

		return service;
	}

}
