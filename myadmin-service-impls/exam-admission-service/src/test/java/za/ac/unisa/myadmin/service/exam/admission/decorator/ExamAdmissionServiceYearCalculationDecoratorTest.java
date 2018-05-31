package za.ac.unisa.myadmin.service.exam.admission.decorator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.unisa.myadmin.TestApplication;
import za.ac.unisa.myadmin.exam.admission.ExamAdmissionService;
import za.ac.unisa.myadmin.service.exam.admission.impl.ExamAdmissionServiceImplTest;

/**
 * ExamAdmissionServiceYearCalculationDecorator Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
public class ExamAdmissionServiceYearCalculationDecoratorTest extends ExamAdmissionServiceImplTest {

	@Autowired
	@Qualifier("ExamAdmissionServiceYearCalculationDecorator")
	private ExamAdmissionService examAdmissionService;

	@TestConfiguration
	static class ExamAdmissionServiceImplTestContextConfiguration {
		@Bean
		public ExamAdmissionService examAdmissionService() {
			return new ExamAdmissionServiceYearCalculationDecorator();
		}
	}

	@Before
	public void before() throws Exception {
	}

	@After
	public void after() throws Exception {
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
