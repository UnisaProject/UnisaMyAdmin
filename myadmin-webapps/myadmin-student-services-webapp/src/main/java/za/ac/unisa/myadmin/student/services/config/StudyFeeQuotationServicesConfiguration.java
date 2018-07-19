package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import za.ac.unisa.myadmin.fees.services.FeesServicesConstants;
import za.ac.unisa.myadmin.fees.services.StudyFeeQuotationService;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.student.services.impls.StudyFeeQuotationServiceImpl;
import za.ac.unisa.myadmin.student.services.rest.impls.StudyFeeQuotationRestServiceImpl;

@Configuration
public class StudyFeeQuotationServicesConfiguration extends AbstractServiceConfiguration {

	@Bean(name = "studyFeeQuotationServiceImpl")
	public StudyFeeQuotationService getStudyFeeQuotationServiceImpl() {
		return new StudyFeeQuotationServiceImpl();
	}

	@Bean(name = "studyFeeQuotationServiceImplRestEndPoint")
	public Server studyFeeQuotationServiceImplRestEndPoint() {
		StudyFeeQuotationRestServiceImpl restServiceImpl = new StudyFeeQuotationRestServiceImpl();
		restServiceImpl.setNextDecorator(getStudyFeeQuotationServiceImpl());

		return createRestEndpoint(restServiceImpl, "/rest/" + FeesServicesConstants.STUDY_FEE_QUOTE_SERVICE_NAME);
	}

}
