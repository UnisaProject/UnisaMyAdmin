package za.ac.unisa.myadmin.studymaterial.services.rest.impls;

import org.apache.commons.io.IOUtils;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.services.base.decorators.StudyMaterialServiceDecorator;
import za.ac.unisa.myadmin.studymaterial.integration.services.utils.StudyMaterialLocation;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;
import za.ac.unisa.myadmin.studymaterial.services.rest.StudyMaterialRestService;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class StudyMaterialRestServiceImpl extends StudyMaterialServiceDecorator implements StudyMaterialRestService {


	@Override
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(String moduleCode, Integer academicYear,
			String semesterCode, UriInfo uriInfo) throws Exception {
		return getNextDecorator().getModuleStudyMaterials(moduleCode, academicYear, semesterCode);
	}

	@Override
	public Response generateActivatedMaterialsPDFReport(StudyMaterialDetailInfo materialInfo)
			throws OperationFailedException {
		try {
			String filePath = StudyMaterialLocation.getMaterialFilePath(materialInfo.getCourseCode(),
					materialInfo.getShortDescription());
			InputStream in = new FileInputStream(filePath);
			byte[] array = IOUtils.toByteArray(in);

			String CONTENT_DESPOSITION = "Content-Disposition";
			String CONTENT_ATTACHEMENT = "attachment; filename=\"" + filePath + "\"";
			return Response.ok()
				.header(CONTENT_DESPOSITION, CONTENT_ATTACHEMENT)
				.header("Cache-Control", "private")
				.header("Pragma", "cache")
				.entity(array)
				.build();
		} catch (Exception ex) {
			throw new OperationFailedException("Error downloading file from server.");
		}
	}
}
