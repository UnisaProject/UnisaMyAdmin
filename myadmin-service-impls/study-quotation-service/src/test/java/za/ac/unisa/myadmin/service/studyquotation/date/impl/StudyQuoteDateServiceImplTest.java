package za.ac.unisa.myadmin.service.studyquotation.date.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.unisa.myadmin.TestApplication;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.service.studyquotation.date.StudyQuotationDateService;
import za.ac.unisa.myadmin.service.studyquotation.date.dao.StudyQuoteDateRepository;
import za.ac.unisa.myadmin.service.studyquotation.date.model.RegistrationDateEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class StudyQuoteDateServiceImplTest {

	@Autowired
	StudyQuotationDateService studyQuotationDateService;

	@MockBean
	private StudyQuoteDateRepository studyQuoteDateRepository;

	@TestConfiguration
	static class StudyQuoteDateServiceImplTestContextConfiguration {
		@Bean
		public StudyQuotationDateService studyQuotationDateService() {
			return new StudyQuoteDateServiceImpl();
		}
	}

	@Before
	public void setUp() {
		//given
		final LocalDate truncatedNow = LocalDate.now();
		Date truncDateNow = Date.from(truncatedNow.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		RegistrationDateEntity dateEntity = new RegistrationDateEntity();
		dateEntity.setAcademicYear(2018);
		dateEntity.setToDate(new Date());
		dateEntity.setSemesterPeriod(0);
		dateEntity.setType("IQ");
		Optional<RegistrationDateEntity> registrationDateEntity = Optional.of(dateEntity);
		Optional<RegistrationDateEntity> registrationDateEntity2 = Optional.of(dateEntity);
		when(studyQuoteDateRepository.getTopByTypeAndSemesterPeriodAndAcademicYear("IQ", 0, 2018)).thenReturn(registrationDateEntity);
		when(studyQuoteDateRepository.getTopByTypeAndSemesterPeriodAndAcademicYearAndFromDateLessThan("IQ", 0, 2018, truncDateNow)).thenReturn(registrationDateEntity2);
		when(studyQuoteDateRepository.getTopByTypeAndSemesterPeriodAndAcademicYearAndFromDateLessThanEqualAndToDateGreaterThanEqual("IQ", 0, 2018, truncDateNow, truncDateNow)).thenReturn(registrationDateEntity2);
	}

	@Test
	public void testValidQuotationYear() throws OperationFailedException {
		int validYear = 2018;
		//when
		int found = studyQuotationDateService.getValidQuotationYear();
		//then
		assertThat(found).isEqualTo(validYear);
	}
}
