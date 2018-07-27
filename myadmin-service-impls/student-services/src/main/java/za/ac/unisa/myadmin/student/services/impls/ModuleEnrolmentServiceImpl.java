package za.ac.unisa.myadmin.student.services.impls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.ModuleEnrolmentService;
import za.ac.unisa.myadmin.module.services.dto.ModuleEnrolmentInfo;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.student.services.StudentAnnualService;
import za.ac.unisa.myadmin.student.services.dto.StudentAnnualInfo;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.ModuleEnrolmentEntityId;
import za.ac.unisa.myadmin.student.services.repositories.ModuleEnrolmentRepository;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Adrian on 2018-06-26.
 */
public class ModuleEnrolmentServiceImpl implements ModuleEnrolmentService {

	private static final Logger LOG = LoggerFactory.getLogger(ModuleEnrolmentServiceImpl.class);

	public static final List<String> TEMP_STUDENT_STATUS_CODES = Stream.of("TN", "RG").collect(Collectors.toList());
	public static final String NO_STUDY_MATERIAL_ISSUED_STATUS = "NOSTMISS";

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

		StudentAnnualInfo studentAnnualInfo = studentAnnualService.getLatestStudentAnnualRecord(student.getStudentNumber());
		if (studentAnnualInfo == null) {
			throw new OperationFailedException("Cannot determine current/last academic year for student " + student.getStudentNumber() + ".Please check the student number and try again.");
		}

		List<Integer> semesterList = new ArrayList<>();
		Collections.addAll(semesterList, 0, 1, 6);
		List<ModuleEnrolmentInfo> nonSecondSemesterStudentModules = this
			.getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(
					student.getStudentNumber(),
					studentAnnualInfo.getAcademicYear(),
					semesterList,
					TEMP_STUDENT_STATUS_CODES
				);

		List<ModuleEnrolmentInfo> returnAllStudentModuleEnrolments = new ArrayList<>(nonSecondSemesterStudentModules);
		semesterList.clear();
		semesterList.add(2);

		// Get all the second semester modules
		List<ModuleEnrolmentInfo> secondSemesterStudentModules = this
				.getModuleEnrolmentsByNumberAndYearAndSemesterInAndStatusIn(
					student.getStudentNumber(),
					studentAnnualInfo.getAcademicYear(),
					semesterList,
					TEMP_STUDENT_STATUS_CODES
				);

		// Get registration period info for second semester material that has not been issued
		List<RegistrationPeriodInfo> registrationPeriodInfoList = registrationPeriodService
			.getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(
				studentAnnualInfo.getAcademicYear(),
			2,
				NO_STUDY_MATERIAL_ISSUED_STATUS,
				new Date()
			);

		// Filter for second semester material that has been issued
		for (ModuleEnrolmentInfo moduleEnrolmentInfo : secondSemesterStudentModules) {

			// Stream through registration period info
			registrationPeriodInfoList.stream()
				// Filter
				.filter(periodInfo -> moduleEnrolmentInfo.getAcademicYear().equals(periodInfo.getAcademicYear())
					&& moduleEnrolmentInfo.getSemesterPeriod().equals(periodInfo.getSemester()))
				// Find any entry that matches the filter
				.findAny()
				// If one was present, add the module enrolment info to the list to return
				.ifPresent((periodInfo) -> returnAllStudentModuleEnrolments.add(moduleEnrolmentInfo));
		}
		return returnAllStudentModuleEnrolments;
	}
}
