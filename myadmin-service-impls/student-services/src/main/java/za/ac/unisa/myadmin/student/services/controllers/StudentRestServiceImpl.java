package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.student.StudentService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping({"/studentservices"})
public class StudentRestServiceImpl {

	@Autowired
	@Qualifier("StudentService")
	private StudentService studentService;

	/**
	 * Forgotten Student Number resource
	 *
	 * @param studentRequest
	 * @return Valid student otherwise error response.
	 * @throws OperationFailedException
	 * @throws MissingParameterException
	 * @throws InvalidParameterException
	 * @throws DoesNotExistException
	 */
	@PostMapping(path = "/student/find", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public StudentInfo requestStudentDetails(@RequestBody StudentInfo studentRequest) throws OperationFailedException, MissingParameterException, InvalidParameterException, DoesNotExistException {
		List<StudentInfo> returnStudentInfo;
		//Validate required request params
		if (!StringUtils.hasText(studentRequest.getSurname())) {
			throw new OperationFailedException("Please enter your surname.");
		}
		if (!StringUtils.hasText(studentRequest.getFirstNames())) {
			throw new OperationFailedException("Please enter your first name(s).");
		}
		if (studentRequest.getDateOfBirth() == null) {
			throw new OperationFailedException("Please enter your date of birth.");
		}

		if (!StringUtils.hasText(studentRequest.getIdentityNumber()) && !StringUtils.hasText(studentRequest.getPassportNumber())) {
			returnStudentInfo = studentService.getStudentsBySurnameAndFirstNamesAndBirthDate(StringUtils.trimWhitespace(studentRequest.getSurname()), StringUtils.trimWhitespace(studentRequest.getFirstNames()), studentRequest.getDateOfBirth());
		} else if (StringUtils.hasText(studentRequest.getIdentityNumber())) {
			returnStudentInfo = studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(StringUtils.trimWhitespace(studentRequest.getSurname()), StringUtils.trimWhitespace(studentRequest.getFirstNames()), studentRequest.getDateOfBirth(), StringUtils.trimWhitespace(studentRequest.getIdentityNumber()));
		} else {
			returnStudentInfo = studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(StringUtils.trimWhitespace(studentRequest.getSurname()), StringUtils.trimWhitespace(studentRequest.getFirstNames()), studentRequest.getDateOfBirth(), StringUtils.trimWhitespace(studentRequest.getPassportNumber()));
		}
		if (CollectionUtils.isEmpty(returnStudentInfo)) {
			throw new OperationFailedException("Student number not found. Check that your details have been entered correctly. If correct, please contact study-info@unisa.ac.za for assistance.");
		}
		if (returnStudentInfo.size() > 1) {
			throw new OperationFailedException("There is more than one student number that complies with the information submitted. Please contact study-info@unisa.ac.za for assistance.");
		}
		return returnStudentInfo.get(0);
	}

}
