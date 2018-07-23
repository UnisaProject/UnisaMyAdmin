package za.ac.unisa.myadmin.student.services.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping({"/studentservices"})
public class StudentRestServiceImpl {

	@Autowired
	@Qualifier("StudentServiceComplianceDecorator")
	private StudentService studentService;

	@GetMapping(path = {"/students"})
	public List<StudentInfo> searchStudents(@RequestParam(value = "surname", required = false) String surname,
											@RequestParam(value = "firstName", required = false) String firstName,
											@RequestParam(value = "dateOfBirth", required = false) String dateOfBirthString,
											@RequestParam(value = "identityNumber", required = false) String identityNumber,
											@RequestParam(value = "passportNumber", required = false) String passportNumber,
											HttpServletRequest httpServletRequest)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		//TODO from angular client dateformat annotation was not working.
		Date dateOfBirth = null;
		if (dateOfBirthString != null) {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			try {
				dateOfBirth = format.parse(dateOfBirthString);
			} catch (ParseException e) {
				throw new InvalidParameterException("Date of Birth format invalid.");
			}
		}
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
