package za.ac.unisa.myadmin.student.services.impls;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.student.services.StudentServiceConstants;
import za.ac.unisa.myadmin.student.services.dto.RegistrationPeriodInfo;
import za.ac.unisa.myadmin.student.services.models.RegistrationPeriodEntity;
import za.ac.unisa.myadmin.student.services.registration.RegistrationPeriodService;
import za.ac.unisa.myadmin.student.services.repositories.RegistrationPeriodRepository;

@Service("RegistrationPeriodService")
public class RegistrationPeriodServiceImpl implements RegistrationPeriodService {

	private static final Logger LOG = LoggerFactory.getLogger(RegistrationPeriodServiceImpl.class);

	@Autowired
	private RegistrationPeriodRepository registrationPeriodRepository;

	@Override
	public List<RegistrationPeriodInfo> getRegistrationPeriodsByYear(Integer year)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		try {
			return registrationPeriodRepository.findByAcademicYear(year)
					.stream()
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.map(entity -> entity.toDto())
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
					.findByAcademicYearAndSemesterPeriodAndTypeAndExpirationDateBefore(year,
							semester, type, date)
					.stream()
					.map(entity -> entity.toDto())
					.collect(Collectors.toList());
		} catch (Exception e) {
			throw new OperationFailedException(e);
		}
	}

	// *********************************

	@Override
	public int getValidQuotationYear() throws OperationFailedException {
		int currentYear = getCurrentYear();
		Integer year = getQuotationYear(currentYear);
		if (year == null) {
			// Try again with current year + 1
			year = getQuotationYear(currentYear + 1);
		}
		if (year == null) {
			throw new OperationFailedException("Failed to get a valid academic year");
		}
		return year;
	}

	/**
	 * Attempt to find a quotation year using a search year.
	 * 
	 * @param searchForYear
	 *            The year to use when searching for a valid quotation year
	 * @return The year that may be used for a quotation.
	 */
	private Integer getQuotationYear(int searchForYear) {
		Integer year = null;
		final LocalDate truncatedNow = LocalDate.now();
		Date truncDateNow = Date.from(truncatedNow.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		Optional<RegistrationPeriodEntity> entity = registrationPeriodRepository
				.getTopByTypeAndSemesterPeriodAndAcademicYear(
						StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE,
						StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_SEMESTER, searchForYear);
		if (entity.isPresent()) {
			Instant toDateInstant = entity.get().getExpirationDate().toInstant();
			if (toDateInstant.atZone(ZoneId.systemDefault()).get(ChronoField.YEAR) == 1) {
				entity = registrationPeriodRepository
						.getTopByTypeAndSemesterPeriodAndAcademicYearAndEffectiveDateLessThan(
						StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE,
						StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_SEMESTER, searchForYear, truncDateNow);
			} else {
				entity = registrationPeriodRepository
						.getTopByTypeAndSemesterPeriodAndAcademicYearAndEffectiveDateLessThanEqualAndExpirationDateGreaterThanEqual(
								StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE,
								StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_SEMESTER, searchForYear,
								truncDateNow, truncDateNow);
			}
			if (entity.isPresent()) {

				year = entity.get().getExpirationDate().toInstant().atZone(ZoneId.systemDefault())
						.get(ChronoField.YEAR);
			}
		}

		LOG.debug("Quotation period 0 = " + year);
		return year;
	}

	/**
	 * Get the current year to attempt to use. If the current month is larger than
	 * the
	 * 
	 * @return The current year to use when querying for quotations
	 */
	private int getCurrentYear() {
		int currentYear = LocalDate.now().get(ChronoField.YEAR);
		final int currentMonth = LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
		// In the default ISO calendar system, this has values from January (1) to
		// December (12).
		// If the month is larger than July, use the next year
		if (currentMonth > 7) {
			currentYear++;
		}
		LOG.debug(String.format("Returning \"%s\" as current study fee quotation year", currentYear));
		return currentYear;
	}

}
