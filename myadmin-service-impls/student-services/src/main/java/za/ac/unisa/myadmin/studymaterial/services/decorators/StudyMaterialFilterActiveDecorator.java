package za.ac.unisa.myadmin.studymaterial.services.decorators;

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
 * This decorator removes any study material returned by the service that does not have a file size or is not active yet.
 */
public class StudyMaterialFilterActiveDecorator extends StudyMaterialServiceDecorator implements StudyMaterialService {


	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		List<StudyMaterialDetailInfo> list = getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);

		return list.stream()
			.filter(studyMaterialDetailInfo ->
				!studyMaterialDetailInfo.getFilesize().equalsIgnoreCase("unavailable") &&
					Instant.parse(studyMaterialDetailInfo.getImplementationDate()).isAfter(Instant.now()))
			.collect(Collectors.toList());
	}
}
