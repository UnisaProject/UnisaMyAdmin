package za.ac.unisa.myadmin.exam.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.ExamPaperService;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.ExamServicesConstants;
import za.ac.unisa.myadmin.exam.services.ExaminationService;
import za.ac.unisa.myadmin.exam.services.decorators.ExamAdmissionServiceYearCalculationDecorator;
import za.ac.unisa.myadmin.exam.services.decorators.ExamPaperServiceComplianceDecorator;
import za.ac.unisa.myadmin.exam.services.decorators.ExamPeriodServiceExclusionDecorator;
import za.ac.unisa.myadmin.exam.services.decorators.ExamPeriodServiceVirtualDecorator;
import za.ac.unisa.myadmin.exam.services.decorators.ExaminationServiceComplianceDecorator;
import za.ac.unisa.myadmin.exam.services.impls.ExamAdmissionServiceImpl;
import za.ac.unisa.myadmin.exam.services.impls.ExamPaperServiceImpl;
import za.ac.unisa.myadmin.exam.services.impls.ExamPeriodServiceImpl;
import za.ac.unisa.myadmin.exam.services.impls.ExaminationServiceImpl;
import za.ac.unisa.myadmin.exam.services.repositories.ExamAdmissionRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPaperRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPeriodRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExaminationRepository;
import za.ac.unisa.myadmin.exam.services.rest.impls.ExamAdmissionRestServiceImpl;
import za.ac.unisa.myadmin.exam.services.rest.impls.ExamPaperRestServiceImpl;
import za.ac.unisa.myadmin.exam.services.rest.impls.ExamPeriodRestServiceImpl;
import za.ac.unisa.myadmin.exam.services.rest.impls.ExaminationRestServiceImpl;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;

@Configuration
public class ExamServicesConfiguration extends AbstractServiceConfiguration {

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

}
