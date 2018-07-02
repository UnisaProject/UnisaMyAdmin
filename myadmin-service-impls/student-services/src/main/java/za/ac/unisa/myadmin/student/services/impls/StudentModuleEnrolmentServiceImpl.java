package za.ac.unisa.myadmin.student.services.impls;

import Srcds01h.Abean.Srcds01sMntStudContactDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.StudentServiceConstants;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentModuleEnrolmentInfo;
import za.ac.unisa.myadmin.student.services.models.StudentModuleEnrolmentEntity;
import za.ac.unisa.myadmin.student.services.models.StudentModuleEnrolmentEntityId;
import za.ac.unisa.myadmin.student.services.registration.RegistrationPeriodService;
import za.ac.unisa.myadmin.student.services.repositories.StudentModuleEnrolmentRepository;
import za.ac.unisa.myadmin.student.services.student.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.student.StudentModuleEnrolmentService;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Adrian on 2018-06-26.
 */
@Service("StudentModuleEnrolmentService")
public class StudentModuleEnrolmentServiceImpl implements StudentModuleEnrolmentService {

	public static final Set<String> TEMP_STUDENT_STATUS_CODES = Stream.of("TN", "RG").collect(Collectors.toSet());
	private static final Logger LOG = LoggerFactory.getLogger(StudentModuleEnrolmentServiceImpl.class);
	@Autowired
	private StudentModuleEnrolmentRepository moduleEnrolmentRepository;

	@Autowired
	private RegistrationPeriodService registrationPeriodService;

	@Autowired
	private StudentAnnualService studentAnnualService;

	@Override
	public StudentModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber, Integer semesterPeriod) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		StudentModuleEnrolmentEntityId studentAnnualEntityId = new StudentModuleEnrolmentEntityId(academicYear, academicPeriod, studyUnitCode, studentNumber, semesterPeriod);
		Optional<StudentModuleEnrolmentEntity> entity = moduleEnrolmentRepository.findById(studentAnnualEntityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException(studentAnnualEntityId.toString());
		}
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumber(studentNumber)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber, Integer academicYear) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumberAndAcademicYear(studentNumber, academicYear)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterAndStatusIn(Integer studentNumber, Integer year, Integer semester, List<String> statusCodesList) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumberAndAcademicYearAndSemesterPeriodAndStatusCodeIn(studentNumber, year, semester, statusCodesList)
				.stream()
				.map(entity -> entity.toDto())
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<StudentModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return null;
	}

	@Override
	public List<StudentModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {

		//This proxy could probably go into the Validation decorator.
		validateStudentWithStudentContactDetailProxy(student);

		//Get Student Latest academicYear
		//try{
		StudentAnnualInfo studentAnnualInfo = studentAnnualService.getStudentAnnualByStudentNumberOrderByYearDesc(student.getStudentNumber()).get(0);
//		}catch(OperationFailedException e){
//			LOG.debug(this + ": " + student.getStudentNumber() + " not having last academic year");
//			throw new OperationFailedException("Cannot determine current/last academic year for student " + student.getStudentNumber() + ".Please check the student number and try again.");
//		}
		//Get Student RegistrationStatus
		//Skip just get it if exists...
		//StudentModuleEnrolmentInfo moduleEnrolmentInfo = this.getStudentModuleEnrolmentByStudentNumberAndAcademicYear(student.getStudentNumber(), studentAnnualInfo.getAcademicYear()).get(0);

		//registrationPeriodService.getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate();
		List<String> statusCodes = new ArrayList<>();
		Collections.addAll(statusCodes, "TN", "RG");
		List<StudentModuleEnrolmentInfo> returnStudentModuleEnrolments = this.getStudentModuleEnrolmentByStudentNumberAndAcademicYear(student.getStudentNumber(), studentAnnualInfo.getAcademicYear());

		return returnStudentModuleEnrolments.stream()
			.filter(returnStudentModuleEnrolment -> TEMP_STUDENT_STATUS_CODES.contains(returnStudentModuleEnrolment.getStatusCode()))
			.collect(Collectors.toList());
	}

	/**
	 * Get an instance of the StudentContactDetail proxy
	 *
	 * @return
	 * @throws PropertyVetoException
	 */
	private Srcds01sMntStudContactDetail constructStudentContactDetailProxy() throws PropertyVetoException {
		Srcds01sMntStudContactDetail studentContactDetailProxy = new Srcds01sMntStudContactDetail();
		studentContactDetailProxy.clear();
		studentContactDetailProxy.setInSecurityWsUserNumber(StudentServiceConstants.STUDY_QUOTE_FEE_PROXY_USER_NUMBER);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientVersionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_VERSION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientRevisionNumber(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_REVISION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsAction(StudentServiceConstants.PARCEL_TRACKING_PROXY_CLIENT_SERVER_COMMUNICATIONS_ACTION);
		studentContactDetailProxy.setInCsfClientServerCommunicationsClientDevelopmentPhase(StudentServiceConstants.PROXY_CLIENT_SERVER_COMMUNICATIONS_CLIENT_DEVELOPMENT_PHASE);
		return studentContactDetailProxy;
	}

	private void validateStudentWithStudentContactDetailProxy(StudentInfo student) throws OperationFailedException {
		try {
			boolean isValid = true;
			StudentInfo proxyStudent = new StudentInfo();
			String proxySurname = "";
			String proxyFirstNames = "";
			Date proxyDateOfBirth = null;
			final AtomicReference<OperationFailedException> exceptionReference = new AtomicReference<>();
			final ActionListener exceptionListener = e -> exceptionReference
				.set(new OperationFailedException(e.getActionCommand()));

			// Get a reference to the proxy
			final Srcds01sMntStudContactDetail studentContactDetailProxy = constructStudentContactDetailProxy();
			studentContactDetailProxy.addExceptionListener(exceptionListener);
			studentContactDetailProxy.setInWsStudentNr(student.getStudentNumber());
			studentContactDetailProxy.execute();
			String errorMsg = studentContactDetailProxy.getOutCsfStringsString500();

			if (StringUtils.hasText(errorMsg)) {
				isValid = false;
				throw new OperationFailedException(errorMsg);
			}
			//Trim leading and trailing whitespace
			proxySurname = StringUtils.trimWhitespace(studentContactDetailProxy.getOutWsStudentSurname());
			proxyFirstNames = StringUtils.trimWhitespace(studentContactDetailProxy.getOutWsStudentFirstNames());
			DateFormat strDate = new SimpleDateFormat("yyyy-MM-dd");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			try {
				proxyDateOfBirth = studentContactDetailProxy.getOutWsStudentBirthDate().getTime();
			} catch (NullPointerException e) {

			}
			//tpStudyMaterialForm.setDateOfBirth(tpStudyMaterialForm.getBirthYear() + "-" + tpStudyMaterialForm.getBirthMonth() + "-" + tpStudyMaterialForm.getBirthDay());

			if (student.getSurname().length() <= 0) {
				isValid = false;
				throw new OperationFailedException("Please enter the surname");
			}

			if (!student.getSurname().equalsIgnoreCase(proxySurname)) {
				LOG.debug(this + ": " + student.getStudentNumber() + " surname");
				isValid = false;
				throw new OperationFailedException("The surname you entered does not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the <A HREF=\"mailto:myUnisaHelp@unisa.ac.za\">myUnisaHelp@unisa.ac.za</A> to have your student records checked.");
			}

			if (student.getFirstNames().length() <= 0) {
				isValid = false;
				throw new OperationFailedException("Please enter full names");
			}
			if (!student.getFirstNames().equalsIgnoreCase(proxyFirstNames)) {
				LOG.debug(this + ": " + student.getStudentNumber() + " fullnames");
				isValid = false;
				throw new OperationFailedException("The full names you entered do not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the <A HREF=\"mailto:myUnisaHelp@unisa.ac.za\">myUnisaHelp@unisa.ac.za</A> to have your student records checked.");
			}

			if (student.getDateOfBirth() == null) {
				isValid = false;
				throw new OperationFailedException("Please enter year for date of birth");
			}
			LocalDate date1 = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = proxyDateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (date1.format(formatter).compareTo(date2.format(formatter)) != 0) {
				LOG.debug(this + ": " + student.getStudentNumber() + " dateofbirth");
				isValid = false;
				throw new OperationFailedException("The date of birth you entered does not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the <A HREF=\"mailto:myUnisaHelp@unisa.ac.za\">myUnisaHelp@unisa.ac.za</A> to have your student records checked.");
			}
			//return;
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}
}