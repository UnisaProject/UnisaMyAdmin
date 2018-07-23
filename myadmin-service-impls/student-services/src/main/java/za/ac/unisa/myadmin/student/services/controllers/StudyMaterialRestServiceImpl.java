package za.ac.unisa.myadmin.student.services.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudyMaterialDetailInfo;
import za.ac.unisa.myadmin.student.services.impls.SCMClientRestServiceImpl;
import za.ac.unisa.myadmin.student.services.impls.SCMWebService;
import za.ac.unisa.myadmin.student.services.impls.StudyMaterialLocation;
import za.ac.unisa.myadmin.student.services.student.StudentModuleEnrolmentService;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping({"/studentservices"})
public class StudyMaterialRestServiceImpl {

	@Autowired
	@Qualifier("StudentModuleEnrolmentServiceStudentValidationDecorator")
	private StudentModuleEnrolmentService moduleEnrolmentService;

	@Autowired
	@Qualifier("SCMClientRestService")
	private SCMClientRestServiceImpl scmClientRestService;

	@Autowired
	@Qualifier("SCMWebService")
	private SCMWebService scmClientService;

	@PostMapping(path = "/studymaterial/courselist", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(@RequestBody StudentInfo student) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		return moduleEnrolmentService.requestStudentModuleEnrolments(student);
	}

	@GetMapping(path = "/studymaterial/viewmaterial", produces = APPLICATION_JSON_VALUE)
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(@RequestParam(value = "moduleCode") String moduleCode, @RequestParam(value = "academicYear") Integer academicYear, @RequestParam(value = "semesterCode") String semesterCode) throws Exception {
		return scmClientService.getStudyMaterialList(moduleCode, academicYear, semesterCode);
	}

	@PostMapping(path = "/studymaterial/download")
	public ResponseEntity<?> generateActivatedMaterialsPDFReport(@RequestBody StudyMaterialDetailInfo materialInfo) throws OperationFailedException {
		String filename = scmClientService.getfileName(materialInfo.getShortDescription());
		String type = scmClientService.getType(materialInfo.getShortDescription());
		if (type != null) {
			type = StudyMaterialLocation.getStudyMaterialTypeDirectoryName(type);
		}
		try {
			String filePath = StudyMaterialLocation.getMaterialFilePath(materialInfo.getCourseCode(), type, filename);
			InputStream in = new FileInputStream(filePath);
			byte[] array = IOUtils.toByteArray(in);

			String CONTENT_DESPOSITION = "Content-Disposition";
			String CONTENT_ATTACHEMENT = "attachment; filename=\"" + filename + "\"";
			return ResponseEntity.ok()
				.header(CONTENT_DESPOSITION, CONTENT_ATTACHEMENT)
				.header("Cache-Control", "private")
				.header("Pragma", "cache")
				.contentType(
					MediaType.parseMediaType("application/pdf")).body(array);
		} catch (Exception ex) {
			throw new OperationFailedException("Error downloading file from server.");
		}
	}
}
