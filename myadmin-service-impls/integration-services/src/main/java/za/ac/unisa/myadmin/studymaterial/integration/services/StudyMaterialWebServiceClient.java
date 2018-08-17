package za.ac.unisa.myadmin.studymaterial.integration.services;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studymaterial.integration.services.dto.ModuleInfoRequest;
import za.ac.unisa.myadmin.studymaterial.integration.services.dto.ResourceDTO;
import za.ac.unisa.myadmin.studymaterial.integration.services.dto.StudyMaterialResponse;
import za.ac.unisa.myadmin.studymaterial.integration.services.utils.WebClientUtil;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.datatype.XMLGregorianCalendar;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
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

	private static final String SERVICE_UNAVAILBLE_MSG = "Please note that UNISA study materials are not currently " +
		"available for viewing. Access to the materials should be restored within an hour. " +
		"UNISA apologizes for any inconvenience caused.";

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

	/**
	 * Convert a semester string into a semester code
	 * @param semester Semester code
	 * @return
	 */
	private static int convertSemesterStringToId(String semester){
		switch (semester) {
			case "S1":
				return 1;
			case "S2":
				return 2;
			case "Y1":
				return 0;
			case "Y2":
				return 6;
			default:
				return Integer.parseInt(semester);
		}
	}

	/**
	 * Get a list of study material for the specified criteria
	 * @param course Course code to get material for.
	 * @param academicYear Academic year.
	 * @param semester Semester
	 * @return A list of study material.
	 * @throws OperationFailedException When something goes wrong.
	 */
	public List<StudyMaterialDetailInfo> getStudyMaterialList(String course, Integer academicYear, String semester) throws OperationFailedException {

		// Build the request to send to the remote service
		ModuleInfoRequest request = new ModuleInfoRequest();
		request.setAcademicYear(academicYear);
		request.setModuleCode(course);
		request.setSemesterPeriod(convertSemesterStringToId(semester));

		StudyMaterialResponse responseEntity = null;
		Response response = null;

		try {
			response = getStudyMaterialWebClient().post(Entity.entity(request, MediaType.APPLICATION_XML));
			responseEntity = response.readEntity(StudyMaterialResponse.class);
		}
		catch (ProcessingException pe){
			throw new RuntimeException(SERVICE_UNAVAILBLE_MSG);
		}
		catch (Exception e) {
			LOG.warn("Exception while trying to execute request", e);
			if (response != null && response.getStatus() != 200) {
				LOG.error("Failed : HTTP error code : " + response.getStatus());
				throw new OperationFailedException("Please note that UNISA study materials are not currently available for viewing. Access to the materials should be restored within an hour. UNISA apologizes for any inconvenience caused.");
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
			.map(StudyMaterialWebServiceClient::toDto)
			.filter(Objects::nonNull)
			.collect(Collectors.toList());
	}

	/**
	 * Create an Invocation builder to access the study material service
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private Invocation.Builder getStudyMaterialWebClient() throws NoSuchAlgorithmException, KeyManagementException {
		return WebClientUtil.getWebClient(serviceTrustAllSsl, serviceUrl).request();
	}

	/**
	 * Convert a <code>ResourceDTO</code> to a <code>StudyMaterialDetailInfo</code>
	 * @param resource
	 * @return A <code>StudyMaterialDetailInfo</code> or <code>null</code> if the document type is "mo"
	 */
	private static StudyMaterialDetailInfo toDto(ResourceDTO resource){
		if (resource.getDocumentType().equalsIgnoreCase("mo")) {
			LOG.debug("TPstudymaterial SCMWebService:getStudyMaterialList DocumentType is  " + resource.getDocumentType());
			return null;
		}
		String itemDisplayName = createItemDisplayName(
			resource.getDocumentType(),
			resource.getUnitNumber(),
			getLanguage(resource.getShortDescription()),
			resource.getModule());

		StudyMaterialDetailInfo studymaterialDetails = new StudyMaterialDetailInfo();
		studymaterialDetails.setCourseCode(resource.getModule());
		studymaterialDetails.setAcademicYear(resource.getYear());
		studymaterialDetails.setPeriod(resource.getPeriod());
		studymaterialDetails.setSemester(resource.getPeriod());
		studymaterialDetails.setShortDescription(resource.getShortDescription());
		studymaterialDetails.setFilesize(resource.getFileSize());
		studymaterialDetails.setDescription(itemDisplayName);
		studymaterialDetails.setImplementationDate(toDate(resource.getDateAvailable()));
		studymaterialDetails.setPath(resource.getPath());
		return studymaterialDetails;
	}

	/**
	 * Convert a <code>XMLGregorianCalendar</code> to <code>Date</code>
	 * @param calendar The calendar to convert.
	 * @return
	 */
	private static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	/**
	 * Creates a displayable name for the material.
	 * @param documentType Type of the document.
	 * @param unitNumber Unit number.
	 * @param language Language.
	 * @param module Module
	 * @return a displayable name for the material.
	 */
	private static String createItemDisplayName(String documentType, String unitNumber, String language, String module) {
		return convertCode(documentType) + "  " + unitNumber + " (" + convertCode(language) + ")" + " for " + module;
	}

	/**
	 * Gets the language from a short description
	 * @param itemShortDesdc
	 * @return
	 */
	private static String getLanguage(String itemShortDesdc) {
		int lastIndexOff_ = itemShortDesdc.lastIndexOf("_") + 1;
		return itemShortDesdc.substring(lastIndexOff_).trim().toUpperCase();
	}


}
