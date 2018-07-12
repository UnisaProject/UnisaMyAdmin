package za.ac.unisa.myadmin.exam.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.exam.services.controllers.ExamAdmissionRestServiceImpl;
import za.ac.unisa.myadmin.server.configurations.AbstractServiceConfiguration;

@Configuration
@ComponentScan({ "za.ac.unisa.myadmin.server.configurations" })
public class ExamAdmissionServiceConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "examAdmissionServiceImplRestEndPoint")
	public Server examAdmissionServiceImplRestEndPoint() {
		ExamAdmissionRestServiceImpl restServiceImpl = new ExamAdmissionRestServiceImpl();

		return createRestEndpoint(restServiceImpl, "/rest/examservices/");
	}


}
