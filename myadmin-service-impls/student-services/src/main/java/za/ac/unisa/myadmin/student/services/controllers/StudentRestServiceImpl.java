package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.service.rest.impl.utils.RestImplUtils;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.student.StudentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping({"/studentservices"})
public class StudentRestServiceImpl {

	@Autowired
	@Qualifier("StudentServiceComplianceDecorator")
	private StudentService studentService;


	@GetMapping(path = {"/students"}, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<StudentInfo> searchStudents(@RequestParam(value = "surname", required = false) String surname,
											@RequestParam(value = "firstName", required = false) String firstName,
											@RequestParam(value = "dateOfBirth", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
											@RequestParam(value = "identityNumber", required = false) String identityNumber,
											@RequestParam(value = "passportNumber", required = false) String passportNumber,
											HttpServletRequest httpServletRequest)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		Set<String> allowedParameters = new HashSet<>();
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		allowedParameters.add("identityNumber");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(surname, firstName,
				dateOfBirth, identityNumber);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		allowedParameters.add("passportNumber");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(surname, firstName,
				dateOfBirth, passportNumber);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsBySurnameAndFirstNamesAndBirthDate(surname, firstName,
				dateOfBirth);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsBySurnameAndFirstNames(surname, firstName);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsBySurname(surname);
		}
		allowedParameters.clear();
		allowedParameters.add("firstName");
		if (RestImplUtils.validateParameters(allowedParameters, httpServletRequest.getParameterMap())) {
			return studentService.getStudentsByFirstNames(firstName);
		}
		throw new MissingParameterException();
	}

}
