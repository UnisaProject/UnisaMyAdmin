package za.ac.unisa.myadmin.payment.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import za.ac.unisa.myadmin.payment.services.PaymentService;
import za.ac.unisa.myadmin.payment.services.PaymentServicesConstants;
import za.ac.unisa.myadmin.payment.services.decorators.PaymentServiceComplianceDecorator;
import za.ac.unisa.myadmin.payment.services.decorators.PaymentServiceCustomValidationDecorator;
import za.ac.unisa.myadmin.payment.services.decorators.PaymentServiceStudentDecorator;
import za.ac.unisa.myadmin.payment.services.impls.PaymentServiceImpl;
import za.ac.unisa.myadmin.payment.services.rest.impls.PaymentRestServiceImpl;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.impls.StudentServiceImpl;
import za.ac.unisa.myadmin.student.services.repositories.StudentRepository;

@Configuration
@EnableJpaRepositories({ "za.ac.unisa.myadmin.student.services.repositories" })
@EntityScan({ "za.ac.unisa.myadmin.student.services.jpa.models" })
public class PaymentServicesConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "studentServiceImpl")
	public StudentService getStudentServiceImpl() {
		StudentServiceImpl service = new StudentServiceImpl();
		service.setStudentRepository(getBean(StudentRepository.class));
		return service;
	}

	@Bean(name = "paymentServiceImpl")
	public PaymentService getPaymentServiceImpl() {
		PaymentServiceImpl service = new PaymentServiceImpl();

		PaymentServiceStudentDecorator studentDecorator = new PaymentServiceStudentDecorator();
		studentDecorator.setNextDecorator(service);
		studentDecorator.setStudentService(getStudentServiceImpl());

		PaymentServiceCustomValidationDecorator customValidationDecorator = new PaymentServiceCustomValidationDecorator();
		customValidationDecorator.setNextDecorator(studentDecorator);

		PaymentServiceComplianceDecorator complianceDecorator = new PaymentServiceComplianceDecorator();
		complianceDecorator.setNextDecorator(customValidationDecorator);

		return complianceDecorator;
	}

	@Bean(name = "paymentServiceImplRestEndPoint")
	public Server paymentServiceImplRestEndPoint() {
		PaymentRestServiceImpl restServiceImpl = new PaymentRestServiceImpl();
		restServiceImpl.setNextDecorator(getPaymentServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + PaymentServicesConstants.PAYMENT_SERVICE_NAME);
	}

}
