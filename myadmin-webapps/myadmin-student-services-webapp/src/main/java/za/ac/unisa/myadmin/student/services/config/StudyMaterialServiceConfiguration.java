package za.ac.unisa.myadmin.student.services.config;

import org.apache.cxf.endpoint.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import za.ac.unisa.myadmin.spring.boot.configurations.AbstractServiceConfiguration;
import za.ac.unisa.myadmin.studymaterial.integration.services.StudyMaterialWebServiceClient;
import za.ac.unisa.myadmin.studymaterial.services.StudyMaterialService;
import za.ac.unisa.myadmin.studymaterial.services.StudyMaterialServicesConstants;
import za.ac.unisa.myadmin.studymaterial.services.decorators.StudyMaterialFilterActiveDecorator;
import za.ac.unisa.myadmin.studymaterial.services.impls.StudyMaterialServiceImpl;
import za.ac.unisa.myadmin.studymaterial.services.rest.impls.StudyMaterialRestServiceImpl;

@Configuration
public class StudyMaterialServiceConfiguration  extends AbstractServiceConfiguration {

	@Value("${integration.studymaterial.trustAllSsl}")
	private boolean studyMaterialServiceTrustAllSsl;

	@Value("${integration.studymaterial.serviceUrl}")
	private String studyMaterialServiceUrl;

	@Bean("studyMaterialServiceImpl")
	public StudyMaterialService studyMaterialService(){
		StudyMaterialWebServiceClient studyMaterialWebServiceClient = new StudyMaterialWebServiceClient();
		studyMaterialWebServiceClient.setServiceTrustAllSsl(studyMaterialServiceTrustAllSsl);
		studyMaterialWebServiceClient.setServiceUrl(studyMaterialServiceUrl);

		StudyMaterialServiceImpl studyMaterialService = new StudyMaterialServiceImpl();
		studyMaterialService.setStudyMaterialWebService(studyMaterialWebServiceClient);
		return studyMaterialService;
	}

	@Bean("studyMaterialFilterActiveDecorator")
	public StudyMaterialService studyMaterialFilterActiveDecorator(){
		StudyMaterialFilterActiveDecorator studyMaterialFilterActiveDecorator = new StudyMaterialFilterActiveDecorator();
		studyMaterialFilterActiveDecorator.setNextDecorator(studyMaterialService());
		return studyMaterialFilterActiveDecorator;
	}

	@Bean(name = "studyMaterialServiceImplRestEndPoint")
	public Server studyFeeQuotationServiceImplRestEndPoint() {
		StudyMaterialRestServiceImpl restServiceImpl = new StudyMaterialRestServiceImpl();
		restServiceImpl.setNextDecorator(studyMaterialFilterActiveDecorator());

		return createRestEndpoint(restServiceImpl, "/rest/" + StudyMaterialServicesConstants.STUDY_MATERIAL_SRVICE_NAME);
	}
}
