package za.ac.unisa.myadmin.service.exam.paper.details.impl;

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
import za.ac.unisa.myadmin.exam.paper.details.ExamPaperDetailsService;
import za.ac.unisa.myadmin.service.exam.paper.details.dao.ExamPaperDetailsRepository;
import za.ac.unisa.myadmin.service.exam.paper.details.model.ExamPaperDetailsEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * ExamPaperDetailsServiceImpl Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
@DirtiesContext
public class ExamPaperDetailsServiceImplTest {

	private int year = 2018;

	private List<ExamPaperDetailsEntity> paperDetailsEntities;

	private List<String> courseCodes;

	@Autowired
	private ExamPaperDetailsService examPaperDetailsService;

	@MockBean
	private ExamPaperDetailsRepository examPaperDetailsRepository;


	@TestConfiguration
	static class ExamPaperDetailsServiceImplTestContextConfiguration {

		@Bean
		public ExamPaperDetailsService examPaperDetailsService() {
			return new ExamPaperDetailsServiceImpl();
		}
	}

	@Before
	public void setUp() {
		//given
		ExamPaperDetailsEntity examPaperDetailsEntity = new ExamPaperDetailsEntity();
		examPaperDetailsEntity.setYear(year);
		paperDetailsEntities = new ArrayList<>();
		paperDetailsEntities.add(examPaperDetailsEntity);
		courseCodes = new ArrayList<String>();
		courseCodes.add("A");
		courseCodes.add("B");
		courseCodes.add("C");
		//Mock Repo calls
		when(examPaperDetailsRepository.findByYear(year)).thenReturn(paperDetailsEntities);

		when(examPaperDetailsRepository.findByCourseCodeIn(courseCodes)).thenReturn(paperDetailsEntities);

		when(examPaperDetailsRepository.findByYearAndCourseCodeIn(year, courseCodes)).thenReturn(paperDetailsEntities);
	}


	@After
	public void after() throws Exception {
	}

	/**
	 * Method: getPaperDetailsByYear(Integer year)
	 */
	@Test
	public void testGetPaperDetailsByYear() throws Exception {
		//when
		List foundEntities = examPaperDetailsService.getPaperDetailsByYear(year);
		//then
		assertThat(foundEntities.size()).isEqualTo(paperDetailsEntities.size());
	}

	/**
	 * Method: getPaperDetailsByCourseCodes(List<String> courseCodes)
	 */
	@Test
	public void testGetPaperDetailsByCourseCodes() throws Exception {
		//when
		List foundEntities = examPaperDetailsService.getPaperDetailsByCourseCodes(courseCodes);
		//then
		assertThat(foundEntities.size()).isEqualTo(paperDetailsEntities.size());
	}

	/**
	 * Method: getPaperDetailsByYearAndCourseCodes(Integer year, List<String> courseCodes)
	 */
	@Test
	public void testGetPaperDetailsByYearAndCourseCodes() throws Exception {
		//when
		List foundEntities = examPaperDetailsService.getPaperDetailsByYearAndCourseCodes(year, courseCodes);
		//then
		assertThat(foundEntities.size()).isEqualTo(paperDetailsEntities.size());
	}
} 
