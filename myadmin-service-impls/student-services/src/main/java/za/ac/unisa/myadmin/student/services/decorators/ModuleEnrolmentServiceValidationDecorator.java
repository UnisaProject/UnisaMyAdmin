package za.ac.unisa.myadmin.student.services.decorators;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import Srcds01h.Abean.Srcds01sMntStudContactDetail;

import org.apache.commons.lang3.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.services.base.decorators.ModuleEnrolmentServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;

/**
 * Decorator that validates student input before parsing
 * to real service
 * Created by Adrian on 2018-06-29.
 */
public class ModuleEnrolmentServiceValidationDecorator extends ModuleEnrolmentServiceDecorator
		implements ModuleEnrolmentService {

	private StudentService studentService;

	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
		throws MissingParameterException, InvalidParameterException, OperationFailedException,
		DoesNotExistException {

		validateStudentExists(student);
		validateStudentContactDetail(student);

		return getNextDecorator().requestStudentModuleEnrolments(student);
	}

	/**
	 *
	 * @param student
	 * @return
	 * @throws PropertyVetoException
	 * @throws OperationFailedException
	 */
	private StudentInfo executeProxy(StudentInfo student) throws PropertyVetoException, OperationFailedException {
		final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
		final ActionListener exceptionListener = e -> exceptionReference
			.set(new OperationFailedException(e.getActionCommand()));
		StudentInfo proxyStudent = new StudentInfo();

		// Get a reference to the proxy
		final Srcds01sMntStudContactDetail studentContactDetailProxy = constructStudentContactDetailProxy();
		studentContactDetailProxy.addExceptionListener(exceptionListener);
		studentContactDetailProxy.setInWsStudentNr(student.getStudentNumber());
		studentContactDetailProxy.execute();
		String errorMsg = studentContactDetailProxy.getOutCsfStringsString500();

		if (StringUtils.isNotEmpty(errorMsg)) {
			throw new OperationFailedException(errorMsg);
		}

		//Trim leading and trailing whitespace
		proxyStudent.setSurname(StringUtils.trim(studentContactDetailProxy.getOutWsStudentSurname()));
		proxyStudent.setFirstNames(StringUtils.trim(studentContactDetailProxy.getOutWsStudentFirstNames()));
		try {
			proxyStudent.setDateOfBirth(studentContactDetailProxy.getOutWsStudentBirthDate().getTime());
		} catch (NullPointerException e) {

		}

		return proxyStudent;
	}

	/**
	 * Validates that the student exists in the database.
	 * @param student Student information to validate.
	 * @throws DoesNotExistException
	 * @throws MissingParameterException
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 */
	private void validateStudentExists(StudentInfo student) throws DoesNotExistException, MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			studentService.getStudentByNumber(student.getStudentNumber());
		} catch (DoesNotExistException e) {
			throw new DoesNotExistException("The student number you have entered is not valid. Please check and try again.");
		}
	}

	/**
	 * Validates that the student information provided matches the information in the student system.
	 * @param student Student information to validate.
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 */
	private void validateStudentContactDetail(StudentInfo student) throws InvalidParameterException, OperationFailedException {
		StudentInfo proxyStudent;
		try {
			proxyStudent = executeProxy(student);
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
		validateStudentContactDetail(student, proxyStudent);
	}


	/**
	 * Validates that the student exists in the database.
	 * @param student Student information to validate.
	 * @param proxyStudent Student information to validate against.
	 * @throws InvalidParameterException
	 * @throws OperationFailedException
	 */
	private void validateStudentContactDetail(StudentInfo student, StudentInfo proxyStudent) throws InvalidParameterException, OperationFailedException {
		if (!student.getSurname().equalsIgnoreCase(proxyStudent.getSurname())) {
			throw new InvalidParameterException("The surname you entered does not correspond with our records. Please check and try again." +
				" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
		}

		if (!student.getFirstNames().equalsIgnoreCase(proxyStudent.getFirstNames())) {
			throw new InvalidParameterException("The full names you entered do not correspond with our records. Please check and try again." +
				" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
		}

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date1 = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate date2 = proxyStudent.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		if (date1.format(formatter).compareTo(date2.format(formatter)) != 0) {
			throw new OperationFailedException("The date of birth you entered does not correspond with our records. Please check and try again." +
				" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
		}
	}

	private Srcds01sMntStudContactDetail constructStudentContactDetailProxy() throws PropertyVetoException {
		Srcds01sMntStudContactDetail studentContactDetailProxy = new Srcds01sMntStudContactDetail();
		studentContactDetailProxy.clear();
		studentContactDetailProxy.setInSecurityWsUserNumber(StudentServicesConstants.STUDY_MATERIAL_PROXY_USER_NUMBER);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientVersionNumber(
			CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientRevisionNumber(
			CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsAction(
			StudentServicesConstants.STUDY_MATERIAL_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(
			CommonServicesConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		return studentContactDetailProxy;
	}

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

}
