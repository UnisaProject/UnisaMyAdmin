package za.ac.unisa.myadmin.student.services.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

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
import za.ac.unisa.myadmin.student.services.StudentServiceConstants;
import za.ac.unisa.myadmin.student.services.impls.RegistrationPeriodServiceImpl;
import za.ac.unisa.myadmin.student.services.models.RegistrationPeriodEntity;
import za.ac.unisa.myadmin.student.services.registration.RegistrationPeriodService;
import za.ac.unisa.myadmin.student.services.repositories.RegistrationPeriodRepository;

@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class RegistrationPeriodServiceImplTest {

	@Autowired
	RegistrationPeriodService registrationPeriodService;

	@MockBean
	private RegistrationPeriodRepository registrationPeriodRepository;

	@TestConfiguration
	static class RegistrationPeriodServiceImplTestContextConfiguration {
		@Bean
		public RegistrationPeriodService registrationPeriodService() {
			return new RegistrationPeriodServiceImpl();
		}
	}

	@Before
	public void setUp() {
		//given
		final LocalDate truncatedNow = LocalDate.now();
		Date truncDateNow = Date.from(truncatedNow.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		RegistrationPeriodEntity dateEntity = new RegistrationPeriodEntity();
		dateEntity.setAcademicYear(2018);
		dateEntity.setExpirationDate(new Date());
		dateEntity.setSemesterPeriod(0);
		dateEntity.setType(StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE);
		Optional<RegistrationPeriodEntity> registrationDateEntity = Optional.of(dateEntity);
		Optional<RegistrationPeriodEntity> registrationDateEntity2 = Optional.of(dateEntity);
		when(registrationPeriodRepository.getTopByTypeAndSemesterPeriodAndAcademicYear(
				StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE, 0, 2018))
						.thenReturn(registrationDateEntity);
		when(registrationPeriodRepository.getTopByTypeAndSemesterPeriodAndAcademicYearAndEffectiveDateLessThan(
				StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE, 0, 2018, truncDateNow))
						.thenReturn(registrationDateEntity2);
		when(registrationPeriodRepository
				.getTopByTypeAndSemesterPeriodAndAcademicYearAndEffectiveDateLessThanEqualAndExpirationDateGreaterThanEqual(
						StudentServiceConstants.STUDY_QUOTE_REGISTRATION_PERIOD_TYPE, 0, 2018, truncDateNow,
						truncDateNow)).thenReturn(registrationDateEntity2);
	}

	@Test
	public void testValidQuotationYear() throws OperationFailedException {
		int validYear = 2018;
		//when
		int found = registrationPeriodService.getValidQuotationYear();
		//then
		assertThat(found).isEqualTo(validYear);
	}
}
