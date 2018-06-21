package za.ac.unisa.myadmin.exam.services.decorators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.decorators.ExamPeriodServiceExclusionDecorator;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.services.models.ExamAdmissionEntity;
import za.ac.unisa.myadmin.exam.services.models.ExamPeriodEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExamAdmissionRepository;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPeriodRepository;

/**
 * ExamPeriodServiceExclusionDecorator Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class ExamPeriodServiceExclusionDecoratorTest {

	private List<Integer> codes;

	@Autowired
	@Qualifier("ExamPeriodServiceExclusionDecorator")
	private ExamPeriodService examPeriodService;

	@MockBean
	private ExamAdmissionRepository examAdmissionRepository;

	@MockBean
	private ExamPeriodRepository examPeriodRepository;

	@TestConfiguration
	static class ExamPeriodServiceImplTestContextConfiguration {
		@Bean
		public ExamPeriodService examPeriodService() {
			return new ExamPeriodServiceExclusionDecorator();
		}
	}

	@Before
	public void before() throws Exception {
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
		List<ExamPeriodEntity> examPeriodEntities = new ArrayList<>();
		ExamPeriodEntity examPeriodEntity = new ExamPeriodEntity();
		examPeriodEntity.setCode(1);
		examPeriodEntity.setAfrDescription("Toets 1");
		examPeriodEntity.setAfrShortDescription("T1");
		examPeriodEntity.setEngDescription("Test 1");
		examPeriodEntity.setEngShortDescription("T1");
		examPeriodEntities.add(examPeriodEntity);
		codes = new ArrayList<Integer>();
		codes.add(1);
		codes.add(13);
		codes.add(14);
		when(examAdmissionRepository.findByYearAndExamPeriodCode(2018, 1)).thenReturn(list);
		when(examAdmissionRepository.findByYearAndExamPeriodCode(2018, 6)).thenReturn(list2);
		when(examPeriodRepository.findAll()).thenReturn(examPeriodEntities);
		when(examPeriodRepository.findAllById(codes)).thenReturn(examPeriodEntities);
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
		List<ExamPeriodInfo> results = examPeriodService.getExamPeriodsByCodes(codes);
		//then
		assertEquals(3, results.size());
	}


} 
