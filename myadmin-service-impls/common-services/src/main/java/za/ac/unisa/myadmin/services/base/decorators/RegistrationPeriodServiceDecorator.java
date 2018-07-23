package za.ac.unisa.myadmin.services.base.decorators;

import java.util.Date;
import java.util.List;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;

public class RegistrationPeriodServiceDecorator implements RegistrationPeriodService {

	private RegistrationPeriodService nextDecorator;

	public RegistrationPeriodService getNextDecorator() throws OperationFailedException {
		if (null == nextDecorator) {
			throw new OperationFailedException("Misconfigured application: nextDecorator is null");
		}
		return nextDecorator;
	}

	public void setNextDecorator(RegistrationPeriodService nextDecorator) {
		this.nextDecorator = nextDecorator;
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByYear(year);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemester(Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsBySemester(semester);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByType(String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByType(type);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemester(Integer year, Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByYearAndSemester(year, semester);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndType(Integer year, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByYearAndType(year, type);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemesterAndType(Integer semester, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsBySemesterAndType(semester, type);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndType(Integer year, Integer semester,
			String type) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByYearAndSemester(year, semester);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearOnDate(Integer year, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsByYearOnDate(year, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterOnDate(Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsBySemesterOnDate(semester, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByTypeOnDate(String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsByTypeOnDate(type, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(Integer year,
			Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(year, semester, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndTypeOnDate(Integer year, String type,
			Date date) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndTypeOnDate(year, type, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(Integer semester,
			String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(semester, type, date);
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(year, semester, type,
				date);
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return getNextDecorator().getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(year, semester,
				type, date);
	}

}
