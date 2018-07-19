package za.ac.unisa.myadmin.exam.services.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import za.ac.unisa.myadmin.exam.services.ExamPaperService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.services.impls.ExamPaperServiceImpl;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExamPaperEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPaperRepository;

/**
 * ExamPaperServiceImpl Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class ExamPaperServiceImplTest {

	private int year = 2018;

	private List<ExamPaperEntity> examPaperEntities;

	private List<String> courseCodes;

	@Autowired
	private ExamPaperService examPaperService;

	@MockBean
	private ExamPaperRepository examPaperRepository;


	@TestConfiguration
	static class ExamPaperServiceImplTestContextConfiguration {

		@Bean
		public ExamPaperService examPaperService() {
			return new ExamPaperServiceImpl();
		}
	}

	@Before
	public void setUp() {
		//given
		ExamPaperEntity entity = new ExamPaperEntity();
		entity.setYear(year);
		examPaperEntities = new ArrayList<>();
		examPaperEntities.add(entity);
		courseCodes = new ArrayList<String>();
		courseCodes.add("A");
		courseCodes.add("B");
		courseCodes.add("C");
		// Mock Repo calls
		when(examPaperRepository.findByYear(year)).thenReturn(examPaperEntities);

		when(examPaperRepository.findByCourseCodeIn(courseCodes)).thenReturn(examPaperEntities);

		when(examPaperRepository.findByYearAndCourseCodeIn(year, courseCodes)).thenReturn(examPaperEntities);
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: getExamPapersByYear(Integer year)
	 */
	@Test
	public void testGetExamPapersByYear() throws Exception {
		//when
		List<ExamPaperInfo> results = examPaperService.getExamPapersByYear(year);
		//then
		assertThat(results.size()).isEqualTo(examPaperEntities.size());
	}

	/**
	 * Method: getExamPapersByCourseCodes(List<String> courseCodes)
	 */
	@Test
	public void testGetExamPapersByCourseCodes() throws Exception {
		//when
		List<ExamPaperInfo> results = examPaperService.getExamPapersByCourseCodes(courseCodes);
		//then
		assertThat(results.size()).isEqualTo(examPaperEntities.size());
	}

	/**
	 * Method: getExamPapersByYearAndCourseCodes(Integer year, List<String>
	 * courseCodes)
	 */
	@Test
	public void testGetExamPapersByYearAndCourseCodes() throws Exception {
		//when
		List<ExamPaperInfo> results = examPaperService.getExamPapersByYearAndCourseCodes(year, courseCodes);
		//then
		assertThat(results.size()).isEqualTo(examPaperEntities.size());
	}
} 
