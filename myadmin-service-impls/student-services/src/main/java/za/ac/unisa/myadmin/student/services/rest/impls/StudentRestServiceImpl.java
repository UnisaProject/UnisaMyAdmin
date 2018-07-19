package za.ac.unisa.myadmin.student.services.rest.impls;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.UriInfo;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.services.base.decorators.StudentServiceDecorator;
import za.ac.unisa.myadmin.services.utilities.RestServiceUtility;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.rest.StudentRestService;

public class StudentRestServiceImpl extends StudentServiceDecorator implements StudentRestService {

	@Override
	public StudentInfo getStudentByNumber(Integer studentNumber) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().getStudentByNumber(studentNumber);
	}

	@Override
	public List<StudentInfo> searchStudents(String surname, String firstName, Date dateOfBirth,
			String identityNumber, String passportNumber, UriInfo uriInfo) throws MissingParameterException,
			InvalidParameterException, OperationFailedException, DoesNotExistException {
		Set<String> allowedParameters = new HashSet<>();
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		allowedParameters.add("identityNumber");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndIdNumber(surname, firstName,
				dateOfBirth, identityNumber);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		allowedParameters.add("passportNumber");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDateAndPassportNumber(surname, firstName,
				dateOfBirth, passportNumber);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		allowedParameters.add("dateOfBirth");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsBySurnameAndFirstNamesAndBirthDate(surname, firstName,
				dateOfBirth);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		allowedParameters.add("firstName");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsBySurnameAndFirstNames(surname, firstName);
		}
		allowedParameters.clear();
		allowedParameters.add("surname");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsBySurname(surname);
		}
		allowedParameters.clear();
		allowedParameters.add("firstName");
		if (RestServiceUtility.validateParameters(allowedParameters, uriInfo)) {
			return getNextDecorator().getStudentsByFirstNames(firstName);
		}
		throw new MissingParameterException();
	}

}
