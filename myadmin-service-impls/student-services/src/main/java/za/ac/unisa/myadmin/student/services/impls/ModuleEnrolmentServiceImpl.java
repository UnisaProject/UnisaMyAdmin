package za.ac.unisa.myadmin.student.services.impls;

import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import Srcds01h.Abean.Srcds01sMntStudContactDetail;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.common.services.CommonServicesConstants;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.StudentServicesConstants;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntityId;
import za.ac.unisa.myadmin.student.services.repositories.ModuleEnrolmentRepository;

import static za.ac.unisa.myadmin.studymaterial.services.StudyMaterialServicesConstants.NO_STUDY_MATERIAL_ISSUED_STATUS;

/**
 * Created by Adrian on 2018-06-26.
 */
public class ModuleEnrolmentServiceImpl implements ModuleEnrolmentService {

	private static final Logger LOG = LoggerFactory.getLogger(ModuleEnrolmentServiceImpl.class);

	public static final List<String> TEMP_STUDENT_STATUS_CODES = Arrays.asList("TN", "RG");

	private ModuleEnrolmentRepository moduleEnrolmentRepository;
	private RegistrationPeriodService registrationPeriodService;
	private StudentAnnualService studentAnnualService;

	public void setModuleEnrolmentRepository(ModuleEnrolmentRepository moduleEnrolmentRepository) {
		this.moduleEnrolmentRepository = moduleEnrolmentRepository;
	}

	public void setRegistrationPeriodService(RegistrationPeriodService registrationPeriodService) {
		this.registrationPeriodService = registrationPeriodService;
	}

	public void setStudentAnnualService(StudentAnnualService studentAnnualService) {
		this.studentAnnualService = studentAnnualService;
	}

	@Override
	public ModuleEnrolmentInfo getStudentModuleEnrolmentByNumberAndYearAndPeriodAndModuleAndSemester(
			Integer academicYear, Integer academicPeriod, String studyUnitCode, Integer studentNumber,
			Integer semesterPeriod) throws MissingParameterException, InvalidParameterException,
			OperationFailedException, DoesNotExistException {
		ModuleEnrolmentEntityId studentAnnualEntityId = new ModuleEnrolmentEntityId(academicYear, academicPeriod, studyUnitCode, studentNumber, semesterPeriod);
		Optional<ModuleEnrolmentEntity> entity = moduleEnrolmentRepository.findById(studentAnnualEntityId);
		if (entity.isPresent()) {
			return entity.get().toDto();
		} else {
			throw new DoesNotExistException(studentAnnualEntityId.toString());
		}
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumber(Integer studentNumber)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumber(studentNumber)
				.stream()
				.map(ModuleEnrolmentEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolmentByStudentNumberAndAcademicYear(Integer studentNumber,
			Integer academicYear)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumberAndAcademicYear(studentNumber, academicYear)
				.stream()
				.map(ModuleEnrolmentEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ModuleEnrolmentInfo> getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(Integer studentNumber,
			Integer year, List<Integer> semesterList, List<String> statusCodesList)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return moduleEnrolmentRepository.findByStudentNumberAndAcademicYearAndSemesterPeriodInAndStatusCodeIn(studentNumber, year, semesterList, statusCodesList)
				.stream()
				.map(ModuleEnrolmentEntity::toDto)
				.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<ModuleEnrolmentInfo> getStudentModuleEnrolments(StudentInfo student)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return null;
	}

	@Override
	public List<ModuleEnrolmentInfo> requestStudentModuleEnrolments(StudentInfo student)
			throws MissingParameterException, InvalidParameterException, OperationFailedException,
			DoesNotExistException {

		//This proxy could probably go into the Validation decorator.
		validateStudentWithStudentContactDetailProxy(student);

		StudentAnnualInfo studentAnnualInfo = studentAnnualService.getLatestStudentAnnualRecord(student.getStudentNumber());
		if (studentAnnualInfo == null) {
			throw new OperationFailedException("Cannot determine current/last academic year for student " + student.getStudentNumber() + ".Please check the student number and try again.");
		}

		List<Integer> semesterList = new ArrayList<>();
		Collections.addAll(semesterList, 0, 1, 6);
		List<ModuleEnrolmentInfo> returnAllStudentModuleEnrolments = new ArrayList<>();
		List<ModuleEnrolmentInfo> nonSecondSemesterStudentModules = this
				.getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(student.getStudentNumber(),
						studentAnnualInfo.getAcademicYear(), semesterList, TEMP_STUDENT_STATUS_CODES);

		returnAllStudentModuleEnrolments.addAll(nonSecondSemesterStudentModules);
		semesterList.clear();
		Collections.addAll(semesterList, 2);
		List<ModuleEnrolmentInfo> secondSemesterStudentModules = this
				.getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(student.getStudentNumber(),
						studentAnnualInfo.getAcademicYear(), semesterList, TEMP_STUDENT_STATUS_CODES);
		List<RegistrationPeriodInfo> registrationPeriodInfoList = registrationPeriodService.getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(studentAnnualInfo.getAcademicYear(),
			2, NO_STUDY_MATERIAL_ISSUED_STATUS, new Date());
		for (ModuleEnrolmentInfo moduleEnrolmentInfo : secondSemesterStudentModules) {
			returnAllStudentModuleEnrolments.addAll(registrationPeriodInfoList.stream().filter(periodInfo -> moduleEnrolmentInfo.getAcademicYear().equals(periodInfo.getAcademicYear()) && moduleEnrolmentInfo.getSemesterPeriod().equals(periodInfo.getSemester())).map(periodInfo -> moduleEnrolmentInfo).collect(Collectors.toList()));
		}
		return returnAllStudentModuleEnrolments;
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

	private void validateStudentWithStudentContactDetailProxy(StudentInfo student) throws OperationFailedException {
		try {
			boolean isValid = true;
			StudentInfo proxyStudent = new StudentInfo();
			//String proxySurname = "";
			//String proxyFirstNames = "";
			//Date proxyDateOfBirth = null;
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
				throw new OperationFailedException(errorMsg);
			}
			//Trim leading and trailing whitespace
			proxyStudent.setSurname(StringUtils.trimWhitespace(studentContactDetailProxy.getOutWsStudentSurname()));
			proxyStudent.setFirstNames(StringUtils.trimWhitespace(studentContactDetailProxy.getOutWsStudentFirstNames()));
			try {
				proxyStudent.setDateOfBirth(studentContactDetailProxy.getOutWsStudentBirthDate().getTime());
			} catch (NullPointerException e) {

			}

			if (student.getSurname().length() <= 0) {
				throw new OperationFailedException("Please enter the surname");
			}

			if (!student.getSurname().equalsIgnoreCase(proxyStudent.getSurname())) {
				LOG.debug(this + ": " + student.getStudentNumber() + " surname");
				throw new OperationFailedException("The surname you entered does not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
			}

			if (student.getFirstNames().length() <= 0) {
				throw new OperationFailedException("Please enter full names");
			}
			if (!student.getFirstNames().equalsIgnoreCase(proxyStudent.getFirstNames())) {
				LOG.debug(this + ": " + student.getStudentNumber() + " fullnames");
				throw new OperationFailedException("The full names you entered do not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
			}

			if (student.getDateOfBirth() == null) {
				throw new OperationFailedException("Please enter year for date of birth");
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date1 = student.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate date2 = proxyStudent.getDateOfBirth().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			if (date1.format(formatter).compareTo(date2.format(formatter)) != 0) {
				LOG.debug(this + ": " + student.getStudentNumber() + " dateofbirth");
				throw new OperationFailedException("The date of birth you entered does not correspond with our records. Please check and try again." +
					" If you are convinced you have entered the correct information, please contact the myUnisaHelp@unisa.ac.za to have your student records checked.");
			}
		} catch (PropertyVetoException e) {
			throw new OperationFailedException(e);
		}
	}
}
