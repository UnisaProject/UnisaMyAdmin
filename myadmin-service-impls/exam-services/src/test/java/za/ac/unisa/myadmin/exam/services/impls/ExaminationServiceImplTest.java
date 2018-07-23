package za.ac.unisa.myadmin.exam.services.impls;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.After;
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
import za.ac.unisa.myadmin.exam.services.ExaminationService;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.services.impls.ExaminationServiceImpl;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExaminationEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExaminationRepository;

/**
 * ExamPeriodDateServiceImpl Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class ExaminationServiceImplTest {

	private List<ExaminationEntity> examinationEntities;
	private List<String> courseCodes;
	private int examYear = 2018;
	@Autowired
	private ExaminationService examinationService;

	@MockBean
	private ExaminationRepository examinationRepository;

	@TestConfiguration
	static class ExaminationServiceImplTestContextConfiguration {
		@Bean
		public ExaminationService examinationService() {
			return new ExaminationServiceImpl();
		}
	}

	@Before
	public void before() throws Exception {
		examinationEntities = new ArrayList<>();
		ExaminationEntity entity = new ExaminationEntity();
		entity.setExamPeriodCode(1);
		entity.setYear(2018);
		entity.setCourseCode("ABC123");
		entity.setExamDate(Date.from(LocalDate.ofYearDay(2018, 300).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		examinationEntities.add(entity);
		//
		courseCodes = new ArrayList<>();
		//
		when(examinationRepository.findByYear(examYear)).thenReturn(examinationEntities);
		when(examinationRepository.findByExamPeriodCode(1)).thenReturn(examinationEntities);
		when(examinationRepository.findByCourseCodeIn(courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndExamPeriodCode(examYear, 1)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndCourseCodeIn(examYear, courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByExamPeriodCodeAndCourseCodeIn(1, courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndExamPeriodCodeAndCourseCodeIn(examYear, 1, courseCodes)).thenReturn(examinationEntities);
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: getExaminationsByYear(Integer year)
	 */
	@Test
	public void testGetExaminationsByYear() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByYear(examYear);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByExamPeriodCode(Integer examPeriodCode)
	 */
	@Test
	public void testGetExaminationsByExamPeriodCode() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByExamPeriodCode(1);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByCourseCodes(List<String> courseCodes)
	 */
	@Test
	public void testGetExaminationsByCourseCodes() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByCourseCodes(courseCodes);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByYearAndExamPeriodCode(Integer year, Integer
	 * examPeriodCode)
	 */
	@Test
	public void testGetExaminationsByYearAndExamPeriodCode() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByYearAndExamPeriodCode(examYear, 1);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByYearAndCourseCodes(Integer year, List<String>
	 * courseCodes)
	 */
	@Test
	public void testGetExaminationsByYearAndCourseCodes() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByYearAndCourseCodes(examYear,
				courseCodes);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByExamPeriodCodeAndCourseCodes(Integer examPeriodCode,
	 * List<String> courseCodes)
	 */
	@Test
	public void testGetExaminationsByExamPeriodCodeAndCourseCodes() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService.getExaminationsByExamPeriodCodeAndCourseCodes(1,
				courseCodes);
		//then
		assertEquals(1, results.size());
	}

	/**
	 * Method: getExaminationsByYearAndExamPeriodCodeAndCourseCodes(Integer year,
	 * Integer examPeriodCode, List<String> courseCodes)
	 */
	@Test
	public void testGetExaminationsByYearAndExamPeriodCodeAndCourseCodes() throws Exception {
		//when
		List<ExaminationInfo> results = examinationService
				.getExaminationsByYearAndExamPeriodCodeAndCourseCodes(examYear, 1, courseCodes);
		//then
		assertEquals(1, results.size());
	}


} 
