package za.ac.unisa.myadmin.studymaterial.services.impls;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.studymaterial.integration.services.StudyMaterialWebServiceimpl;
import za.ac.unisa.myadmin.studymaterial.services.StudyMaterialService;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import java.util.List;

public class StudyMaterialServiceImpl implements StudyMaterialService {


	private StudyMaterialWebServiceimpl studyMaterialWebService;

	public void setStudyMaterialWebService(StudyMaterialWebServiceimpl studyMaterialWebService) {
		this.studyMaterialWebService = studyMaterialWebService;
	}


	@Override
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear, String semesterCode)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return studyMaterialWebService.getStudyMaterialList(moduleCode, academicYear, semesterCode);
	}

}
