package za.ac.unisa.myadmin.exam.services.impls;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.unisa.myadmin.exam.services.dto.ExaminationInfo;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExaminationEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExaminationRepository;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodDateServiceImpl Tester.
 *
 */
public class ExaminationServiceImplTest {

	private static final List<ExaminationEntity> examinationEntities = new ArrayList<>();
	private static final List<String> courseCodes = new ArrayList<>();
	private static final int examYear = 2018;
	private static ExaminationServiceImpl examinationService;


	private static ExaminationRepository createExaminationRepository(){
		ExaminationRepository examinationRepository = mock(ExaminationRepository.class);
		when(examinationRepository.findByYear(examYear)).thenReturn(examinationEntities);
		when(examinationRepository.findByExamPeriodCode(1)).thenReturn(examinationEntities);
		when(examinationRepository.findByCourseCodeIn(courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndExamPeriodCode(examYear, 1)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndCourseCodeIn(examYear, courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByExamPeriodCodeAndCourseCodeIn(1, courseCodes)).thenReturn(examinationEntities);
		when(examinationRepository.findByYearAndExamPeriodCodeAndCourseCodeIn(examYear, 1, courseCodes)).thenReturn(examinationEntities);
		return examinationRepository;
	}


	@BeforeClass
	public static void beforeClass() {
		examinationEntities.add(new ExaminationEntity(){{
			setExamPeriodCode(1);
			setYear(2018);
			setCourseCode("ABC123");
			setExamDate(Date.from(LocalDate.ofYearDay(2018, 300).atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
		}});



		examinationService = new ExaminationServiceImpl();
		examinationService.setExaminationRepository(createExaminationRepository());

	}

	@Before
	public void before() throws Exception {
		//
		//

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
