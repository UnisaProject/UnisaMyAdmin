package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.student.StudentModuleEnrolmentService;

import java.util.List;


@RestController
@RequestMapping({"/studentservices"})
public class StudyMaterialRestServiceImpl {

	//	@Qualifier("StudentModuleEnrolmentService")
	@Autowired
	@Qualifier("StudentModuleEnrolmentServiceStudentValidationDecorator")
	private StudentModuleEnrolmentService moduleEnrolmentService;

	@PostMapping(path = "/studymaterial/courselist")
	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(@RequestBody StudentInfo student) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		return moduleEnrolmentService.requestStudentModuleEnrolments(student);
	}

//	@GetMapping(path = "/studymaterial/viewMaterial")
//	public List<StudentModuleEnrolmentInfo> getModuleStudyMaterials(StudentInfo student) throws OperationFailedException {
//		return moduleEnrolmentService.getModuleStudyMaterials(student);
//	}
}
