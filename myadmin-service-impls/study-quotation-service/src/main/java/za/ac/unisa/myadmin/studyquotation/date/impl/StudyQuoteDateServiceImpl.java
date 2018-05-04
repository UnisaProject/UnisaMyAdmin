package za.ac.unisa.myadmin.studyquotation.date.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import za.ac.unisa.myadmin.studyquotation.date.StudyQuotationDateService;
import za.ac.unisa.myadmin.studyquotation.date.dao.StudyQuoteDateRepository;
import za.ac.unisa.myadmin.studyquotation.date.model.StudyQuoteDateEntity;

import java.time.Instant;
import java.time.temporal.ChronoField;
import java.util.Objects;

public class StudyQuoteDateServiceImpl implements StudyQuotationDateService {

	private static final Logger LOG = LoggerFactory.getLogger(StudyQuoteDateServiceImpl.class);

	@Autowired
	private StudyQuoteDateRepository studyQuoteDateRepository;


	private static final String TYPE = "IQ";
	private static final int SEMESTER_PERIOD = 0;

	@Override
	public int getValidQuotationYear() {

		StudyQuoteDateEntity entity = studyQuoteDateRepository.getFirstByTypeAndSemesterPeriodAndAcademicYear(TYPE, SEMESTER_PERIOD, getCurrentYear());
		if(Objects.nonNull(entity.getToDate())){

		}

		return 0;
	}

	private int getCurrentYear(){
		int currentYear = Instant.now().get(ChronoField.YEAR);
		final int currentMonth = Instant.now().get(ChronoField.MONTH_OF_YEAR);
		if(currentMonth > 7){
			currentYear++;
		}
		LOG.debug(String.format("Returning \"%s\" as current study fee quotation year", currentYear));
		return currentYear;
	}
}
