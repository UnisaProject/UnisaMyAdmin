package za.ac.unisa.myadmin.student.services.registration;

import java.util.Date;
import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.dto.RegistrationPeriodInfo;

public interface RegistrationPeriodService {

	int getValidQuotationYear() throws OperationFailedException;

	// *********************************

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemester(Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByType(String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemester(Integer year, Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndType(Integer year, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemesterAndType(Integer semester, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndType(Integer year, Integer semester,
			String type) throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearOnDate(Integer year, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterOnDate(Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByTypeOnDate(String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(Integer year,
			Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndTypeOnDate(Integer year, String type,
			Date date) throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(Integer semester,
			String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException;

}
