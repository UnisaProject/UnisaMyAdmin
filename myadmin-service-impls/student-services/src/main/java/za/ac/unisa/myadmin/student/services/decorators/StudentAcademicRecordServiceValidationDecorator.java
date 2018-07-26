package za.ac.unisa.myadmin.student.services.decorators;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;
import za.ac.unisa.myadmin.services.base.decorators.StudentAcademicRecordServiceDecorator;
import za.ac.unisa.myadmin.student.services.StudentAcademicRecordService;
import za.ac.unisa.myadmin.student.services.StudentService;
import za.ac.unisa.myadmin.student.services.dto.StudentInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntity;
import za.ac.unisa.myadmin.student.services.jpa.models.GenericMessageEntityId;
import za.ac.unisa.myadmin.student.services.repositories.GenericCodeRepository;
import za.ac.unisa.myadmin.student.services.repositories.GenericMessageRepository;

import java.util.List;
import java.util.Optional;

/**
 * This decorator validates student status.
 */
public class StudentAcademicRecordServiceValidationDecorator extends StudentAcademicRecordServiceDecorator implements StudentAcademicRecordService {

	//TODO Replace with suitable authorization lookup possibly CAS info.
	private boolean isStudentUser = true;

	private StudentService studentService;

	private GenericMessageRepository genericMessageRepository;

	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	public void setGenericMessageRepository(GenericMessageRepository genericMessageRepository) {
		this.genericMessageRepository = genericMessageRepository;
	}

	@Override
	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		if (isStudentUser) {

			StudentInfo student = studentService.getStudentByNumber(studentNumber);

			GenericMessageEntityId genericMessageEntityId = new GenericMessageEntityId();
			Optional<GenericMessageEntity> genMessageEnt;
			if (student.isExamFeesDebt() || student.isFinancialBlock()) {
				genericMessageEntityId.setMessageCode("FEEBLOCK");
				genericMessageEntityId.setProgram("UNISA_ACADHISTORY");
				genMessageEnt = genericMessageRepository.findById(genericMessageEntityId);
				if (genMessageEnt.isPresent()) {
					throw new InvalidParameterException(genMessageEnt.get().getMessage());
				}
			}

			if (student.getLibraryBlackList() > 0) {
				genericMessageEntityId.setMessageCode("LIBRARY");
				genericMessageEntityId.setProgram("UNISA_ACADHISTORY");
				genMessageEnt = genericMessageRepository.findById(genericMessageEntityId);
				if (genMessageEnt.isPresent()) {
					throw new InvalidParameterException(genMessageEnt.get().getMessage());
				}
			}

			if (student.getDisciplinaryIncident() > 0) {
				genericMessageEntityId.setMessageCode("STUDISPL");
				genericMessageEntityId.setProgram("UNISA_ACADHISTORY");
				genMessageEnt = genericMessageRepository.findById(genericMessageEntityId);
				if (genMessageEnt.isPresent()) {
					throw new InvalidParameterException(genMessageEnt.get().getMessage());
				}

			}
		}
		return getNextDecorator().requestStudentAcademicQualificationResults(studentNumber);
	}

	@Override
	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException {
		return getNextDecorator().requestStudentAcademicModuleResults(studentNumber, isCreditsOnly, selectedQualificationCode);
	}
}
