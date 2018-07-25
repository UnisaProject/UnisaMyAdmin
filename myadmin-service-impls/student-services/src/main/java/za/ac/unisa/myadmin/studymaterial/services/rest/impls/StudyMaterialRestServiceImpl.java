package za.ac.unisa.myadmin.studymaterial.services.rest.impls;

import za.ac.unisa.myadmin.services.base.decorators.StudyMaterialServiceDecorator;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;
import za.ac.unisa.myadmin.studymaterial.services.rest.StudyMaterialRestService;

import javax.ws.rs.core.UriInfo;
import java.util.List;

public class StudyMaterialRestServiceImpl extends StudyMaterialServiceDecorator implements StudyMaterialRestService {


	@Override
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear,
			String semesterCode, UriInfo uriInfo) throws Exception {
		return getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);
	}

}
