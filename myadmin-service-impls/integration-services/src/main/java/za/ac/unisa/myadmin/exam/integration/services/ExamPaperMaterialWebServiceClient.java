package za.ac.unisa.myadmin.exam.integration.services;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.integration.services.dto.ExamPaperDto;
import za.ac.unisa.myadmin.exam.integration.services.dto.ExamPaperResponse;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperMaterialInfo;
import za.ac.unisa.myadmin.studymaterial.integration.services.utils.PeriodCodesConverter;
import za.ac.unisa.myadmin.studymaterial.integration.services.utils.WebClientUtil;

import javax.ws.rs.client.WebTarget;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.stream.Collectors;

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
	 * Path of the service at the <code>serviceUrl</code>
	 */
	private static final String SERVICE_PATH = "/rcmclient/Default.aspx";

	/**
	 * Flag if all SSL certificates should be trusted.
	 * This should NEVER by enabled for a production environment.
	 * Only use for dev or test where the remote service might not have a valid SSL certificate
	 */
	private boolean serviceTrustAllSsl = false;

	/**
	 * Setter for property 'serviceUrl'.
	 *
	 * @param serviceUrl Value to set for property 'serviceUrl'.
	 */
	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	/**
	 * Setter for property 'serviceTrustAllSsl'.
	 *
	 * @param serviceTrustAllSsl Value to set for property 'serviceTrustAllSsl'.
	 */
	public void setServiceTrustAllSsl(boolean serviceTrustAllSsl) {
		this.serviceTrustAllSsl = serviceTrustAllSsl;
	}

	public List<ExamPaperMaterialInfo> getExamPapersByModuleCode(String moduleCode) throws OperationFailedException {
		WebTarget target;
		try{
			target = WebClientUtil.getWebClient(serviceTrustAllSsl, serviceUrl + SERVICE_PATH);
		} catch (NoSuchAlgorithmException | KeyManagementException e){
			LOG.warn("Exception while trying to create web target", e);
			throw new OperationFailedException(e);
		}
		ExamPaperResponse examPaperResponse = target.queryParam("coursecode", moduleCode)
			.request()
			.get(ExamPaperResponse.class);


		return examPaperResponse.getExamPapers()
			.stream()
			.map(this::toDto)
			.collect(Collectors.toList());
	}

	private ExamPaperMaterialInfo toDto(ExamPaperDto examPaperDto){
		ExamPaperMaterialInfo examPaperMaterialInfo = new ExamPaperMaterialInfo();
		examPaperMaterialInfo.setPeriod(examPaperDto.getPeriod());
		examPaperMaterialInfo.setPeriodDesc(PeriodCodesConverter.convert(examPaperDto.getPeriod()));
		examPaperMaterialInfo.setAcademicYear(examPaperDto.getExamYear());
		examPaperMaterialInfo.setCourseCode(examPaperDto.getCourseCode());
		examPaperMaterialInfo.setLanguage("E".equalsIgnoreCase(examPaperDto.getLanguage()) ? "English" : "Afrikaans");
		examPaperMaterialInfo.setUnitNumber(examPaperDto.getPaperNumber());
		examPaperMaterialInfo.setFilesize(FileUtils.byteCountToDisplaySize(examPaperDto.getFileSize()));
		examPaperMaterialInfo.setPath(stripAnchor(examPaperDto.getLink()));
		return examPaperMaterialInfo;
	}

	/**
	 * Strip the anchor in the path link provided by the service
	 * e.g <pre>&lt;a href="/rcmclient/default.aspx?ID=18078492&amp;NAME=EDPHOD8-2015-10-A-1"&gt;</pre>
	 * @param link
	 * @return
	 */
	private String stripAnchor(String link){
		return serviceUrl + link.substring(9, link.length() - 2);
	}
}
