package za.ac.unisa.myadmin.exam.integration.services;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;

import java.util.List;

@Ignore // Do not run integration test on builds
public class ExamPaperMaterialWebServiceClientTest {

	private static final Logger LOG = LoggerFactory.getLogger(ExamPaperMaterialWebServiceClientTest.class);

	private static final String SERVICE_URL = "ADD YOUR SERVICE HERE";
	private static final String COURSE_CODE = "EDPHOD8";

	@Test
	public void getPreviousExamPapers() throws OperationFailedException {
		ExamPaperMaterialWebServiceClient client = new ExamPaperMaterialWebServiceClient();
		client.setServiceTrustAllSsl(true);
		client.setServiceUrl(SERVICE_URL);
		List<ExamPaperMaterialInfo> list = client.getExamPapersByModuleCode(COURSE_CODE);
		LOG.debug("Got response");
	}
}
