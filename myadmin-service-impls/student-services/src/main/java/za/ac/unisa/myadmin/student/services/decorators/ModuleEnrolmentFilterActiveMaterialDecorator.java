package za.ac.unisa.myadmin.student.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class ModuleEnrolmentFilterActiveMaterialDecorator extends ModuleEnrolmentServiceDecorator implements ModuleEnrolmentService {


	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		List<StudyMaterialDetailInfo> list = getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);

		return list.stream()
			.filter(studyMaterialDetailInfo ->
				!studyMaterialDetailInfo.getFilesize().equalsIgnoreCase("unavailable") &&
					Instant.parse(studyMaterialDetailInfo.getImplementationDate()).isAfter(Instant.now()))
			.collect(Collectors.toList());
	}
}
