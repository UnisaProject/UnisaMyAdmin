package za.ac.unisa.myadmin.student.services.impls;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.registration.services.RegistrationPeriodService;
import za.ac.unisa.myadmin.registration.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.student.services.jpa.models.RegistrationPeriodEntity;
import za.ac.unisa.myadmin.student.services.repositories.RegistrationPeriodRepository;

public class RegistrationPeriodServiceImpl implements RegistrationPeriodService {

	private RegistrationPeriodRepository registrationPeriodRepository;

	public void setRegistrationPeriodRepository(RegistrationPeriodRepository registrationPeriodRepository) {
		this.registrationPeriodRepository = registrationPeriodRepository;
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByAcademicYear(year)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemester(Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findBySemesterPeriod(semester)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByType(String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByType(type)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemester(Integer year, Integer semester)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByAcademicYearAndSemesterPeriod(year, semester)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndType(Integer year, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByAcademicYearAndType(year, type)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsBySemesterAndType(Integer semester, String type)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findBySemesterPeriodAndType(semester, type)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndType(Integer year, Integer semester,
			String type) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByAcademicYearAndSemesterPeriodAndType(year, semester, type)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearOnDate(Integer year, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findByAcademicYearAndEffectiveDateAfterAndExpirationDateBefore(year, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterOnDate(Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findBySemesterPeriodAndEffectiveDateAfterAndExpirationDateBefore(semester, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByTypeOnDate(String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByTypeAndEffectiveDateAfterAndExpirationDateBefore(type, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterOnDate(Integer year,
			Integer semester, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findByAcademicYearAndSemesterPeriodAndEffectiveDateAfterAndExpirationDateBefore(year, semester,
							date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndTypeOnDate(Integer year, String type,
			Date date) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findByAcademicYearAndTypeAndEffectiveDateAfterAndExpirationDateBefore(year, type, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsBySemesterAndTypeOnDate(Integer semester,
			String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findBySemesterPeriodAndTypeAndEffectiveDateAfterAndExpirationDateBefore(semester, type, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getEffectiveRegistrationPeriodsByYearAndSemesterAndTypeOnDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findByAcademicYearAndSemesterPeriodAndTypeAndEffectiveDateAfterAndExpirationDateBefore(year,
							semester, type, date, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYearAndSemesterAndTypeAfterExpirationDate(Integer year,
			Integer semester, String type, Date date)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository
					.findByAcademicYearAndSemesterPeriodAndTypeAndExpirationDateBefore(year, semester, type, date)
					.stream()
					.map(RegistrationPeriodEntity::toDto)
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

}
