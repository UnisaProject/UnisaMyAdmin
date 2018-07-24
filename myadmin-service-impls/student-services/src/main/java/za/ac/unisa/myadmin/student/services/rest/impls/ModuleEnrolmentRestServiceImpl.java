package za.ac.unisa.myadmin.student.services.rest.impls;

import org.apache.commons.io.IOUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.module.services.rest.ModuleEnrolmentRestService;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.studymaterial.integration.services.utils.StudyMaterialLocation;
import za.ac.unisa.myadmin.studymaterial.services.dto.StudyMaterialDetailInfo;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class ModuleEnrolmentRestServiceImpl extends ModuleEnrolmentServiceDecorator
		implements ModuleEnrolmentRestService {



	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws OperationFailedException, MissingParameterException, InvalidParameterException,
			DoesNotExistException {
		return getNextDecorator().requestStudentModuleEnrolments(student);
	}

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
