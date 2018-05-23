package za.ac.unisa.myadmin.service.exam.period.date.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.unisa.myadmin.TestApplication;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateInfo;
import za.ac.unisa.myadmin.exam.period.date.ExamPeriodDateService;
import za.ac.unisa.myadmin.service.exam.period.date.dao.ExamPeriodDateRepository;
import za.ac.unisa.myadmin.service.exam.period.date.model.ExamPeriodDateEntity;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodDateServiceImpl Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
@DirtiesContext
public class ExamPeriodDateServiceImplTest {

	private List<ExamPeriodDateEntity> examPeriodDateEntities;
	private List<String> courseCodes;
	private int examYear = 2018;
	@Autowired
	private ExamPeriodDateService examPeriodDateService;

	@MockBean
	private ExamPeriodDateRepository examPeriodDateRepository;

	@TestConfiguration
	static class ExamPeriodDateServiceImplTestContextConfiguration {
		@Bean
		public ExamPeriodDateService examPeriodDateService() {
			return new ExamPeriodDateServiceImpl();
		}
	}

	@Before
	public void before() throws Exception {
		examPeriodDateEntities = new ArrayList<>();
		ExamPeriodDateEntity entity = new ExamPeriodDateEntity();
		entity.setExamPeriodCode(1);
		entity.setYear(2018);
		entity.setCourseCode("ABC123");
		entity.setExamDate(Date.from(LocalDate.ofYearDay(2018, 300).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		examPeriodDateEntities.add(entity);
		//
		courseCodes = new ArrayList<>();
		//
		when(examPeriodDateRepository.findByYear(examYear)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByExamPeriodCode(1)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByCourseCodeIn(courseCodes)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByYearAndExamPeriodCode(examYear, 1)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByYearAndCourseCodeIn(examYear, courseCodes)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByExamPeriodCodeAndCourseCodeIn(1, courseCodes)).thenReturn(examPeriodDateEntities);
		when(examPeriodDateRepository.findByYearAndExamPeriodCodeAndCourseCodeIn(examYear, 1, courseCodes)).thenReturn(examPeriodDateEntities);
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: getExamPeriodDatesByYear(Integer year)
	 */
	@Test
	public void testGetExamPeriodDatesByYear() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByYear(examYear);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByExamPeriodCode(Integer examPeriodCode)
	 */
	@Test
	public void testGetExamPeriodDatesByExamPeriodCode() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByExamPeriodCode(1);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByCourseCodes(List<String> courseCodes)
	 */
	@Test
	public void testGetExamPeriodDatesByCourseCodes() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByCourseCodes(courseCodes);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
	 */
	@Test
	public void testGetExamPeriodDatesByYearAndExamPeriodCode() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByYearAndExamPeriodCode(examYear, 1);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByYearAndCourseCodes(Integer year, List<String> courseCodes)
	 */
	@Test
	public void testGetExamPeriodDatesByYearAndCourseCodes() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByYearAndCourseCodes(examYear, courseCodes);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByExamPeriodCodeAndCourseCodes(Integer examPeriodCode, List<String> courseCodes)
	 */
	@Test
	public void testGetExamPeriodDatesByExamPeriodCodeAndCourseCodes() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByExamPeriodCodeAndCourseCodes(1, courseCodes);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(Integer year, Integer examPeriodCode, List<String> courseCodes)
	 */
	@Test
	public void testGetExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes() throws Exception {
		//when
		List<ExamPeriodDateInfo> foundList = examPeriodDateService.getExamPeriodDatesByYearAndExamPeriodCodeAndCourseCodes(examYear, 1, courseCodes);
		//then
		assertThat(foundList.size()).isEqualTo(1);
	}


} 
