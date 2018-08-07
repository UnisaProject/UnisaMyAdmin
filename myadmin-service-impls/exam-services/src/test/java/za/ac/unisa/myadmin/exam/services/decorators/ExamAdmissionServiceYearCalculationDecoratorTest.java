package za.ac.unisa.myadmin.exam.services.decorators;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;

/**
 * ExamAdmissionServiceYearCalculationDecorator Tester.
 *
 */
public class ExamAdmissionServiceYearCalculationDecoratorTest {

	private static ExamAdmissionServiceYearCalculationDecorator decorator;


	@BeforeClass
	public void beforeClass() {
		decorator = new ExamAdmissionServiceYearCalculationDecorator();
		decorator.setNextDecorator(Mockito.mock(ExamAdmissionService.class));
	}

	/**
	 * Method: getExamAdmissions()
	 */
	@Test
	public void testGetExamAdmissions() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByYear(Integer year)
	 */
	@Test
	public void testGetExamAdmissionsByYear() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByExamPeriodCode(Integer examPeriodCode)
	 */
	@Test
	public void testGetExamAdmissionsByExamPeriodCode() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByExamType(Integer examType)
	 */
	@Test
	public void testGetExamAdmissionsByExamType() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
	 */
	@Test
	public void testGetExamAdmissionsByYearAndExamPeriodCode() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByYearAndExamType(Integer year, Integer examType)
	 */
	@Test
	public void testGetExamAdmissionsByYearAndExamType() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByExamPeriodCodeAndExamType(Integer examPeriodCode, Integer examType)
	 */
	@Test
	public void testGetExamAdmissionsByExamPeriodCodeAndExamType() throws Exception {
//TODO: Test goes here...
	}

	/**
	 * Method: getExamAdmissionsByYearAndExamPeriodCodeAndExamType(Integer year, Integer examPeriodCode, Integer examType)
	 */
	@Test
	public void testGetExamAdmissionsByYearAndExamPeriodCodeAndExamType() throws Exception {
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
