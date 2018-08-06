package za.ac.unisa.myadmin.exam.integration.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Service client to retrieve previous exam paper material
 */
public class ExamPaperMaterialWebServiceClient {

	/**
	 * A reference to a logger
	 */
	private static Log LOG = LogFactory.getLog(ExamPaperMaterialWebServiceClient.class);

	/**
	 * Endpoint of the service to call.
	 */
	private String serviceUrl;

	/**
	 * Setter for property 'serviceUrl'.
	 *
	 * @param serviceUrl Value to set for property 'serviceUrl'.
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}


	public List<ExamPaperMaterialInfo> getPreviousExamPapers(String courseCode){
		Response response = ClientBuilder.newBuilder()
			.build()
			.target(serviceUrl)
			.queryParam("coursecode", courseCode)
			.request()
			.get();


		return null;
	}
}
