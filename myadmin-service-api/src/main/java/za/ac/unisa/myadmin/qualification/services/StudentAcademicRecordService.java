package za.ac.unisa.myadmin.qualification.services;

import za.ac.unisa.myadmin.common.dto.ErrorInfo;
import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.qualification.services.dto.AcademicRecordEmailRequestInfo;
import za.ac.unisa.myadmin.qualification.services.dto.StudentAcademicQualificationRecordInfo;

import java.util.List;

public interface StudentAcademicRecordService {

	List<StudentAcademicQualificationRecordInfo> getStudentAcademicQualificationResults(Integer studentNumber) throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	ErrorInfo sendStudentAcademicRecordEmail(AcademicRecordEmailRequestInfo emailRequestInfo)
		throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	List<StudentAcademicQualificationRecordInfo> getQualificationResultsByStudentNumber(Integer studentNumber)throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

	StudentAcademicQualificationRecordInfo getQualificationResultByStudentNumberAndQualCode(Integer studentNumber, String qualCode)throws MissingParameterException, InvalidParameterException, OperationFailedException, DoesNotExistException;

}
