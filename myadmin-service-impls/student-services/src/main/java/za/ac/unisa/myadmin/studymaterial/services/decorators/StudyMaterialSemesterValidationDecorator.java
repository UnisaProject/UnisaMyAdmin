package za.ac.unisa.myadmin.studymaterial.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.services.base.decorators.StudyMaterialServiceDecorator;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.util.Date;
import java.util.List;

import static za.ac.unisa.myadmin.studymaterial.services.StudyMaterialServicesConstants.NO_STUDY_MATERIAL_ISSUED_STATUS;

/**
 * This decorator will check if the requested material is for the second semester, and if the second semester material
 * has been issued already. If the second semester material has not yet been issues the student will be informed to only
 * look for it once the second semester has started.
 */
public class StudyMaterialSemesterValidationDecorator extends StudyMaterialServiceDecorator {

	private RegistrationPeriodService registrationPeriodService;

	public void setRegistrationPeriodService(RegistrationPeriodService registrationPeriodService) {
		this.registrationPeriodService = registrationPeriodService;
	}

	@Override
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		// This decorator only applies for requests for second semester material
		if("2".equals(semesterCode)){
			int count = registrationPeriodService.getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(academicYear,
				2, NO_STUDY_MATERIAL_ISSUED_STATUS, new Date()).size();
			if(count == 0){
				throw new OperationFailedException("Study Material will only be available in the second semester");
			}
		}
		return getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);
	}
}
