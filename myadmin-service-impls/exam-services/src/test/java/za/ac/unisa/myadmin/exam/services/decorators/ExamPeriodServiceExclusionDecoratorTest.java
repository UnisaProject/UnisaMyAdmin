package za.ac.unisa.myadmin.exam.services.decorators;

import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodServiceExclusionDecorator Tester.
 *
 */
public class ExamPeriodServiceExclusionDecoratorTest {

	private static final List<Integer> CODES = Arrays.asList(1, 13, 14);

	private static ExamPeriodServiceExclusionDecorator examPeriodServiceExclusionDecorator;

	private static ExamPeriodService createMockExamPeriodService() throws Exception {
		final ExamPeriodService examPeriodService = mock(ExamPeriodService.class);

		List<ExamPeriodInfo> examPeriodEntities = new ArrayList<>();
		examPeriodEntities.add(new ExamPeriodInfo(){{
			setCode(1);
			setExamType("T1");
		}});

		examPeriodEntities.add(new ExamPeriodInfo(){{
			setCode(13);
			setExamType("T2");
		}});

		examPeriodEntities.add(new ExamPeriodInfo(){{
			setCode(14);
			setExamType("T3");
		}});


		when(examPeriodService.getExamPeriods()).thenReturn(examPeriodEntities);
		when(examPeriodService.getExamPeriodsByCodes(CODES)).thenReturn(examPeriodEntities);
		return examPeriodService;
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		final ExamPeriodService service = createMockExamPeriodService();
		examPeriodServiceExclusionDecorator = new ExamPeriodServiceExclusionDecorator();
		examPeriodServiceExclusionDecorator.setNextDecorator(service);
	}

	@Test
	public void testGetExamPeriods() throws Exception {
		//Given
		// Periods:{1,13,14}
		// Years:{2017,2018,2019}
		//when
		List<ExamPeriodInfo> foundList = examPeriodServiceExclusionDecorator.getExamPeriods();
		//then
		assertThat(foundList.size()).isEqualTo(2);
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
		//when
		List<ExamPeriodInfo> results = examPeriodServiceExclusionDecorator.getExamPeriodsByCodes(CODES);
		//then
		assertEquals(2, results.size());
	}

}
