package za.ac.unisa.myadmin.service.studyquotation.date.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.unisa.myadmin.service.studyquotation.date.StudyQuotationDateService;
import za.ac.unisa.myadmin.service.studyquotation.date.dao.StudyQuoteDateRepository;
import za.ac.unisa.myadmin.service.studyquotation.date.model.StudyQuoteDateEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 *
 */
@Service("StudyQuoteDateServiceImpl")
public class StudyQuoteDateServiceImpl implements StudyQuotationDateService {

	/**
	 * A reference to a logger.
	 */
	private static final Logger LOG = LoggerFactory.getLogger(StudyQuoteDateServiceImpl.class);

	/**
	 * A reference to the <code>StudyQuoteDateRepository</code>.
	 */
	@Autowired
	private StudyQuoteDateRepository studyQuoteDateRepository;

	/**
	 * The semester type to use when queering for quotation dates
	 */
	private static final String TYPE = "IQ";

	/**
	 * The semester period to use when queering for quotation dates
	 */
	private static final int SEMESTER_PERIOD = 0;

	@Override
	public int getValidQuotationYear() {
		int currentYear = getCurrentYear();
		Integer year = getQuotationYear(currentYear);
		if(year == null){
			//Try again with current year + 1
			year = getQuotationYear(currentYear + 1);
		}
		return year;
	}

	/**
	 * Attempt to find a quotation year using a search year.
	 * @param searchForYear The year to use when searching for a valid quotation year
	 * @return The year that may be used for a quotation.
	 */
	private Integer getQuotationYear(int searchForYear){
		Integer year = null;
		final LocalDate truncatedNow = LocalDate.now();
		Optional<StudyQuoteDateEntity> entity = studyQuoteDateRepository.getFirstByTypeAndSemesterPeriodAndAcademicYear(TYPE, SEMESTER_PERIOD, searchForYear);
		if(entity.isPresent()){
			Instant toDateInstant = entity.get().getToDate().toInstant();
			if(toDateInstant.get(ChronoField.YEAR) == 1){
				entity = studyQuoteDateRepository.getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqual(TYPE, SEMESTER_PERIOD, searchForYear, truncatedNow);
			}
			else{
				entity = studyQuoteDateRepository.getFirstByTypeAndSemesterPeriodAndAcademicYearAndFromDateGreaterThanEqualAndToDateLessThanEqual(TYPE, SEMESTER_PERIOD, searchForYear, truncatedNow, truncatedNow);
			}
			if(entity.isPresent()){
				year = entity.get().getToDate().toInstant().get(ChronoField.YEAR);
			}
		}

		LOG.debug("Quotation period 0 = " + year);
		return year;
	}

	/**
	 * Get the current year to attempt to use.
	 * If the current month is larger than the
	 * @return The current year to use when querying for quotations
	 */
	private int getCurrentYear(){
		int currentYear = LocalDate.now().get(ChronoField.YEAR);
		final int currentMonth = LocalDate.now().get(ChronoField.MONTH_OF_YEAR);
		// In the default ISO calendar system, this has values from January (1) to December (12).
		// If the month is larger than July, use the next year
		if(currentMonth > 7){
			currentYear++;
		}
		LOG.debug(String.format("Returning \"%s\" as current study fee quotation year", currentYear));
		return currentYear;
	}
}
