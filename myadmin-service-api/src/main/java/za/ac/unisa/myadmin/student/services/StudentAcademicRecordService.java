package za.ac.unisa.myadmin.student.services;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.module.services.dto.StudentAcademicStudyUnitResultInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import java.util.List;

public interface StudentAcademicRecordService {


	public List<StudentAcademicQualificationRecordInfo> requestStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	public List<StudentAcademicStudyUnitResultInfo> requestStudentAcademicModuleResults(Integer studentNumber, boolean isCreditsOnly, String selectedQualificationCode) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

}
