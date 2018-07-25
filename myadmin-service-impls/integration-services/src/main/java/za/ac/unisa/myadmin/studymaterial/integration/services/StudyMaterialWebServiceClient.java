package za.ac.unisa.myadmin.studymaterial.integration.services;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studymaterial.integration.services.dto.ModuleInfoRequest;
import za.ac.unisa.myadmin.studymaterial.integration.services.dto.StudyMaterialResponse;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static za.ac.unisa.myadmin.studymaterial.integration.services.utils.StudyMaterialCodesConverter.convertCode;

/**
 * Rest client for the study material XML Service
 */
public class StudyMaterialWebServiceClient {

	/**
	 * A reference to a logger
	 */
	private static Log LOG = LogFactory.getLog(StudyMaterialWebServiceClient.class);

	/**
	 * Endpoint of the service to call.
	 */
	private String serviceUrl;

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
		if(serviceTrustAllSsl){
			LOG.warn("Service is configured to trust all SSL certificates!");
		}
		this.serviceTrustAllSsl = serviceTrustAllSsl;
	}

	//private EmailService emailService;

	private static int convertSemesterStringToId(String semester){
		if (semester.equals("S1")) {
			semester = "1";
		} else if (semester.equals("S2")) {
			semester = "2";
		} else if (semester.equals("Y1")) {
			semester = "0";
		} else if (semester.equals("Y2")) {
			semester = "6";
		}
		return Integer.parseInt(semester);
	}

	public List<StudyMaterialDetailInfo> getStudyMaterialList(String course, Integer academicYear, String semester) throws OperationFailedException {

		// Build the request to send to the remote service
		ModuleInfoRequest request = new ModuleInfoRequest();
		request.setAcademicYear(academicYear);
		request.setModuleCode(course);
		request.setSemesterPeriod(convertSemesterStringToId(semester));

		StudyMaterialResponse responseEntity = null;
		Response response = null;

		try {
			ClientBuilder webClientBuilder = ClientBuilder.newBuilder();
			if(serviceTrustAllSsl){
				webClientBuilder.hostnameVerifier((s, sslSession) -> true);
				webClientBuilder.sslContext(trustAllContext());
			}
			response = webClientBuilder.build()
				.target(serviceUrl)
				.request()
				.post(Entity.entity(request, MediaType.APPLICATION_XML));

			responseEntity = response.readEntity(StudyMaterialResponse.class);
		} catch (Exception e) {
			LOG.warn("Exception while trying to execute request", e);
			if (response != null && response.getStatus() != 200) {
				LOG.error("Failed : HTTP error code : " + response.getStatus());
				throw new RuntimeException("Please note that UNISA study materials are not currently available for viewing. Access to the materials should be restored within an hour. UNISA apologizes for any inconvenience caused.");
			}
		} finally {
			if (response != null) {
				response.close();
			}
		}

		// If we did not get a response there is no material available
		if(responseEntity == null){
			return Collections.emptyList();
		}

		// Map each of the service's DTOs to a StudyMaterialDetailInfo
		return responseEntity.getResource()
			.stream()
			.map(resourceDTO -> {
				if (resourceDTO.getDocumentType().equalsIgnoreCase("mo")) {
					LOG.debug("TPstudymaterial SCMWebService:getStudyMaterialList DocumentType is  " + resourceDTO.getDocumentType());
					return null;
				}
				String itemDisplayName = setItemDisplayName(
					resourceDTO.getDocumentType(),
					resourceDTO.getUnitNumber(),
					getlanguage(resourceDTO.getShortDescription()),
					resourceDTO.getModule());

				StudyMaterialDetailInfo studymaterialDetails = new StudyMaterialDetailInfo();
				studymaterialDetails.setCourseCode(resourceDTO.getModule());
				studymaterialDetails.setAcademicYear(resourceDTO.getYear());
				studymaterialDetails.setSemester(resourceDTO.getPeriod());
				studymaterialDetails.setShortDescription(resourceDTO.getShortDescription());
				studymaterialDetails.setFilesize(resourceDTO.getFileSize());
				studymaterialDetails.setDescription(itemDisplayName);
				studymaterialDetails.setImplementationDate(toDate(resourceDTO.getDateAvailable()));
				studymaterialDetails.setPath(resourceDTO.getPath());
				return studymaterialDetails;
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}


	private Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	private String setItemDisplayName(String documentType, String unitNumber, String language, String module) {

		return convertCode(documentType) + "  " + unitNumber + " (" + convertCode(language) + ")" + " for " + module;
	}

	private String getlanguage(String itemShortDesdc) {
		int lastIndexOff_ = itemShortDesdc.lastIndexOf("_") + 1;
		return itemShortDesdc.substring(lastIndexOff_).trim().toUpperCase();
	}

	/**
	 * Creates a SSL Context that allows ALL SSL Certificates, even invalid ones to be accepted.
	 * @return
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	private static SSLContext trustAllContext() throws KeyManagementException, NoSuchAlgorithmException {
		SSLContext ctx = SSLContext.getInstance("SSL");
		TrustManager[] certs = new TrustManager[]{ new X509TrustManager() {
			@Override
			public X509Certificate[] getAcceptedIssuers() { return null; }

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType)throws CertificateException {}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {}
		}
		};
		ctx.init(null, certs, new SecureRandom());
		return ctx;
	}
}
