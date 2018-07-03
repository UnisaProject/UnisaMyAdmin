package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import za.ac.unisa.myadmin.student.services.student.StudentModuleEnrolmentService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping({"/studentservices"})
public class StudyMaterialRestServiceImpl {

	//	@Qualifier("StudentModuleEnrolmentService")
	@Autowired
	@Qualifier("StudentModuleEnrolmentServiceStudentValidationDecorator")
	private StudentModuleEnrolmentService moduleEnrolmentService;
	@Autowired
	@Qualifier("SCMClientRestService")
	private SCMClientRestServiceImpl scmClientRestService;

	@PostMapping(path = "/studymaterial/courselist", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(@RequestBody StudentInfo student) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		return moduleEnrolmentService.requestStudentModuleEnrolments(student);
	}

	@GetMapping(path = "/studymaterial/viewMaterial")
	public List<StudyMaterialDetailInfo> getModuleStudyMaterials(@RequestParam(value = "moduleCode") String moduleCode, @RequestParam(value = "academicYear") Integer academicYear, @RequestParam(value = "semesterCode") String semesterCode) throws Exception {
		return scmClientRestService.getStudyMaterialList(moduleCode, academicYear, semesterCode);
	}
}
