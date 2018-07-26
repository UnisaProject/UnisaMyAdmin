package za.ac.unisa.myadmin.studymaterial.services.decorators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.services.base.decorators.StudyMaterialServiceDecorator;
import za.ac.unisa.myadmin.studymaterial.services.StudyMaterialService;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This decorator removes any study material returned that is yet not active yet.
 * Also, any material that does not have a file size (i.e the content is missing) is not returned, but an email is
 * sent to an administrator.
 */
public class StudyMaterialFilterActiveDecorator extends StudyMaterialServiceDecorator implements StudyMaterialService {

	private static final Logger LOG = LoggerFactory.getLogger(StudyMaterialFilterActiveDecorator.class);

	/**
	 * Email address to which to send missing content emails.
	 */
	private String missingContentEmail;

	public void setMissingContentEmail(String missingContentEmail) {
		this.missingContentEmail = missingContentEmail;
	}

	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		List<StudyMaterialDetailInfo> list = getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);

		return list.stream()
			.filter((studyMaterialDetailInfo) -> {
				if(studyMaterialDetailInfo.getFilesize().equalsIgnoreCase("unavailable")){
					sendMissingMaterialEmail(studyMaterialDetailInfo);
					return false; // Not a valid material
				}
				return studyMaterialDetailInfo.getImplementationDate().toInstant().isAfter(Instant.now());
			})
			.collect(Collectors.toList());
	}

	/**
	 * Send an email to notify an administrator that material is missing
	 * @param studyMaterialDetailInfo
	 */
	private void sendMissingMaterialEmail(StudyMaterialDetailInfo studyMaterialDetailInfo){
		LOG.warn("Material is missing! Sending notification to " + missingContentEmail);
		// TODO Need an email service
	}
}
