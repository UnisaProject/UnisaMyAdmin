package za.ac.unisa.myadmin.service.exam.period.decorator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.unisa.myadmin.TestApplication;
import za.ac.unisa.myadmin.exam.period.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.period.ExamPeriodService;
import za.ac.unisa.myadmin.service.exam.admission.dao.ExamAdmissionRepository;
import za.ac.unisa.myadmin.service.exam.admission.model.ExamAdmissionEntity;
import za.ac.unisa.myadmin.service.exam.period.impl.ExamPeriodServiceImplTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodServiceExclusionDecorator Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class ExamPeriodServiceExclusionDecoratorTest extends ExamPeriodServiceImplTest {

	@Autowired
	@Qualifier("ExamPeriodServiceExclusionDecorator")
	private ExamPeriodService examPeriodService;

	@MockBean
	private ExamAdmissionRepository examAdmissionRepository;

	@TestConfiguration
	static class ExamPeriodServiceImplTestContextConfiguration {
		@Bean
		public ExamPeriodService examPeriodService() {
			return new ExamPeriodServiceExclusionDecorator();
		}
	}

	@Before
	public void before() throws Exception {
		super.before();
		List<ExamAdmissionEntity> list = new ArrayList<>();
		List<ExamAdmissionEntity> list2 = new ArrayList<>();
		ExamAdmissionEntity entity = new ExamAdmissionEntity();
		ExamAdmissionEntity entity2 = new ExamAdmissionEntity();
		entity.setYear(2018);
		entity.setExamPeriodCode(1);
		entity.setAdmissionDone(true);
		entity.setExamType(1);
		list.add(entity);
		entity2.setYear(2018);
		entity2.setExamPeriodCode(6);
		entity2.setAdmissionDone(true);
		entity2.setExamType(1);
		list2.add(entity2);
		when(examAdmissionRepository.findByYearAndExamPeriodCode(2018, 1)).thenReturn(list);
		when(examAdmissionRepository.findByYearAndExamPeriodCode(2018, 6)).thenReturn(list2);
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Given 3 mocked ExamPeriods and 3 Years for virtualDecorator thus 12 records
	 * Exclusion Decorator should exclude 6 and keep one.
	 * Method: getExamPeriods()
	 */
	@Test
	public void testGetExamPeriods() throws Exception {
		//Given
		// Periods:{1,8,13}
		// Years:{2017,2018,2019}
		//when
		List<ExamPeriodInfo> foundList = examPeriodService.getExamPeriods();
		//then
		assertThat(foundList.size()).isEqualTo(3);
	}

	/**
	 * Given 3 mocked ExamPeriods and 3 Years for virtualDecorator thus 9 records
	 * Exclusion Decorator should exclude 3 and keep one.
	 *
	 * Method: getExamPeriodByCodes(List<Integer> codes)
	 */
	@Test
	public void testGetExamPeriodByCodes() throws Exception {
		//Given
		// Codes:{1,13,14}
		// Years:{2017,2018,2019}
		//when
		List<ExamPeriodInfo> foundList = examPeriodService.getExamPeriodByCodes(codes);
		//then
		assertThat(foundList.size()).isEqualTo(6);
	}


} 
