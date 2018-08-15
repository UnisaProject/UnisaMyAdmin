package za.ac.unisa.myadmin.exam.services.impls;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.unisa.myadmin.exam.services.dto.ExamPaperInfo;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExamPaperEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPaperRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ExamPaperServiceImpl Tester.
 *
 */
public class ExamPaperServiceImplTest {

	private static final int year = 2018;

	private static final List<ExamPaperEntity> examPaperEntities = new ArrayList<>();

	private static final List<String> courseCodes = Arrays.asList("A", "B", "C");

	private static ExamPaperServiceImpl examPaperService;

	private static ExamPaperRepository createExamPaperRepository(){
		ExamPaperRepository examPaperRepository = mock(ExamPaperRepository.class);
		examPaperEntities.add(new ExamPaperEntity(){{
			setYear(year);
		}});
		// Mock Repo calls
		when(examPaperRepository.findByYear(year)).thenReturn(examPaperEntities);
		when(examPaperRepository.findByCourseCodeIn(courseCodes)).thenReturn(examPaperEntities);
		when(examPaperRepository.findByYearAndCourseCodeIn(year, courseCodes)).thenReturn(examPaperEntities);
		return examPaperRepository;
	}


	@BeforeClass
	public static void beforeClass(){
		examPaperService = new ExamPaperServiceImpl();
		examPaperService.setExamPaperRepository(createExamPaperRepository());
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
