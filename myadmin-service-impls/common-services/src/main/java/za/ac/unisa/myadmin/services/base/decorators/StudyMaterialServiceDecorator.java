package za.ac.unisa.myadmin.services.base.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.studymaterial.services.StudyMaterialService;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.util.List;

public class StudyMaterialServiceDecorator implements StudyMaterialService {

	private StudyMaterialService nextDecorator;

	public StudyMaterialService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(StudyMaterialService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}


	@Override
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException{
		return getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);
	}

}
