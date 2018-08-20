package za.ac.unisa.myadmin.exam.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.exam.integration.services.ExamPaperMaterialWebServiceClient;
import za.ac.unisa.myadmin.exam.services.*;
import za.ac.unisa.myadmin.exam.services.decorators.*;
import za.ac.unisa.myadmin.exam.services.impls.*;
import za.ac.unisa.myadmin.exam.services.repositories.ExamAdmissionRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPaperRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPeriodRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExaminationRepository;
import za.ac.unisa.myadmin.exam.services.rest.impls.*;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;

@Configuration
public class ExamServicesConfiguration extends AbstractServiceConfiguration {

	@Value("${integration.exampapermaterial.trustAllSsl}")
	private boolean examPaperMaterialServiceTrustAllSsl;

	@Value("${integration.exampapermaterial.serviceUrl}")
	private String examPaperMaterialServiceUrl;


	@Bean(name = "examAdmissionServiceImpl")
	public ExamAdmissionService getExamAdmissionServiceImpl() {
		ExamAdmissionServiceImpl service = new ExamAdmissionServiceImpl();
		service.setExamAdmissionRepository(getBean(ExamAdmissionRepository.class));

		ExamAdmissionServiceYearCalculationDecorator yearCalculationDecorator = new ExamAdmissionServiceYearCalculationDecorator();
		yearCalculationDecorator.setNextDecorator(service);

		return yearCalculationDecorator;
	}

	@Bean(name = "examAdmissionServiceImplRestEndPoint")
	public Server examAdmissionServiceImplRestEndPoint() {
		ExamAdmissionRestServiceImpl restServiceImpl = new ExamAdmissionRestServiceImpl();
		restServiceImpl.setNextDecorator(getExamAdmissionServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + ExamServicesConstants.EXAM_ADMISSION_SERVICE_NAME);
	}

	@Bean(name = "examinationServiceImpl")
	public ExaminationService getExaminationServiceImpl() {
		ExaminationServiceImpl service = new ExaminationServiceImpl();
		service.setExaminationRepository(getBean(ExaminationRepository.class));

		ExaminationServiceComplianceDecorator complianceDecorator = new ExaminationServiceComplianceDecorator();
		complianceDecorator.setNextDecorator(service);

		return complianceDecorator;
	}

	@Bean(name = "examinationServiceImplRestEndPoint")
	public Server examinationServiceImplRestEndPoint() {
		ExaminationRestServiceImpl restServiceImpl = new ExaminationRestServiceImpl();
		restServiceImpl.setNextDecorator(getExaminationServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + ExamServicesConstants.EXAMINATION_SERVICE_NAME);
	}

	@Bean(name = "examPaperServiceImpl")
	public ExamPaperService getExamPaperServiceImpl() {
		ExamPaperServiceImpl service = new ExamPaperServiceImpl();
		service.setExamPaperRepository(getBean(ExamPaperRepository.class));

		ExamPaperServiceComplianceDecorator complianceDecorator = new ExamPaperServiceComplianceDecorator();
		complianceDecorator.setNextDecorator(service);

		return complianceDecorator;
	}

	@Bean(name = "examPaperServiceImplRestEndPoint")
	public Server examPaperServiceImplRestEndPoint() {
		ExamPaperRestServiceImpl restServiceImpl = new ExamPaperRestServiceImpl();
		restServiceImpl.setNextDecorator(getExamPaperServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + ExamServicesConstants.EXAM_PAPER_SERVICE_NAME);
	}

	@Bean(name = "examPeriodServiceImpl")
	public ExamPeriodService getExamPeriodServiceImpl() {
		ExamPeriodServiceImpl service = new ExamPeriodServiceImpl();
		service.setExamPeriodRepository(getBean(ExamPeriodRepository.class));

		ExamPeriodServiceVirtualDecorator virtualDecorator = new ExamPeriodServiceVirtualDecorator();
		virtualDecorator.setNextDecorator(service);
		virtualDecorator.setExamAdmissionService(getBean(ExamAdmissionService.class));

		ExamPeriodServiceExclusionDecorator exclusionDecorator = new ExamPeriodServiceExclusionDecorator();
		exclusionDecorator.setNextDecorator(virtualDecorator);

		return exclusionDecorator;
	}

	@Bean(name = "examPeriodServiceImplRestEndPoint")
	public Server examPeriodServiceImplRestEndPoint() {
		ExamPeriodRestServiceImpl restServiceImpl = new ExamPeriodRestServiceImpl();
		restServiceImpl.setNextDecorator(getExamPeriodServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + ExamServicesConstants.EXAM_PERIOD_SERVICE_NAME);
	}

	@Bean(name = "examPaperMaterialService")
	public ExamPaperMaterialService examPaperMaterialService(){
		ExamPaperMaterialWebServiceClient webServiceClient = new ExamPaperMaterialWebServiceClient();
		webServiceClient.setServiceUrl(examPaperMaterialServiceUrl);
		webServiceClient.setServiceTrustAllSsl(examPaperMaterialServiceTrustAllSsl);

		ExamPaperMaterialServiceImpl examPaperMaterialService = new ExamPaperMaterialServiceImpl();
		examPaperMaterialService.setWebServiceClient(webServiceClient);


		ExamPaperMaterialServiceValidationDecorator validationDecorator = new ExamPaperMaterialServiceValidationDecorator();
		validationDecorator.setNextDecorator(examPaperMaterialService);

		return validationDecorator;
	}


	@Bean(name = "examPaperMaterialServiceRestEndPoint")
	public Server examPaperMaterialServiceRestEndPoint() {
		ExamPaperMaterialRestServiceImpl restServiceImpl = new ExamPaperMaterialRestServiceImpl();
		restServiceImpl.setNextDecorator(examPaperMaterialService());

		return createRestEndpoint(restServiceImpl, "/rest/" + ExamServicesConstants.EXAM_PAPERMATERIAL_SERVICE_NAME);
	}
}
