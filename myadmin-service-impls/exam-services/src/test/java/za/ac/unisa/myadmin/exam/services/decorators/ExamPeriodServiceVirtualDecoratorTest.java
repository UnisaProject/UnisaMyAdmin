package za.ac.unisa.myadmin.exam.services.decorators;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodServiceVirtualDecorator Tester.
 *
 */
public class ExamPeriodServiceVirtualDecoratorTest {

	private static final List<Integer> CODES = Arrays.asList(1, 13, 14);

	private static ExamPeriodServiceVirtualDecorator decorator;


	private static ExamPeriodService createMockExamPeriodService() throws OperationFailedException, InvalidParameterException, MissingParameterException {
		ExamPeriodService service = mock(ExamPeriodService.class);

		List<ExamPeriodInfo> examPeriodInfos = new ArrayList<>();
		examPeriodInfos.add(new ExamPeriodInfo(){{
			setCode(1);
			setExamType("T1");
		}});

		when(service.getExamPeriods()).thenReturn(examPeriodInfos);
		when(service.getExamPeriodsByCodes(CODES)).thenReturn(examPeriodInfos);
		return service;
	}

	private static ExamAdmissionService createMockExamAdmissionService() throws Exception {
		ExamAdmissionService service = mock(ExamAdmissionService.class);
		List<ExamAdmissionInfo> list = new ArrayList<>();
		List<ExamAdmissionInfo> list2 = new ArrayList<>();
		ExamAdmissionInfo entity = new ExamAdmissionInfo();
		ExamAdmissionInfo entity2 = new ExamAdmissionInfo();
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

		when(service.getExamAdmissionsByYearAndExamPeriodCode(2018, 1)).thenReturn(list);
		when(service.getExamAdmissionsByYearAndExamPeriodCode(2018, 6)).thenReturn(list2);
		return service;
	}

	@BeforeClass
	public static void beforeClass() throws Exception {
		decorator = new ExamPeriodServiceVirtualDecorator();
		decorator.setExamAdmissionService(createMockExamAdmissionService());
		decorator.setNextDecorator(createMockExamPeriodService());
	}

	/**
	 * Method: getExamPeriods()
	 */
	@Test
	public void testGetExamPeriods() throws Exception {
		//Given
		// Periods:{1,8,13}
		// Years:{2017,2018,2018}
		//when
		List<ExamPeriodInfo> foundList = decorator.getExamPeriods();
		//then
		assertThat(foundList.size()).isEqualTo(3);
	}

	/**
	 * Method: getExamPeriodByCodes(List<Integer> codes)
	 */
	@Test
	public void testGetExamPeriodByCodes() throws Exception {
		//Given
		// Codes:{1,13,14}
		// Years:{2017,2018,2019}
		//when
		List<ExamPeriodInfo> foundList = decorator.getExamPeriodsByCodes(CODES);
		//then
		assertThat(foundList.size()).isEqualTo(3);
	}


	/**
	 * Method: createVirtualExamPeriod(ExamPeriodInfo examPeriod, Set<Integer> years)
	 */
	@Test
	public void testCreateVirtualExamPeriod() throws Exception {
	//TODO: Test goes here...

	}

	/**
	 * Method: calculateCurrentYears()
	 */
	@Test
	public void testCalculateCurrentYears() throws Exception {
	//TODO: Test goes here...

	}

}
