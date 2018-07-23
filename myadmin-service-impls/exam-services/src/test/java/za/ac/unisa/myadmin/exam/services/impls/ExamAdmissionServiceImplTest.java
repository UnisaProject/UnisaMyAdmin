package za.ac.unisa.myadmin.exam.services.impls;

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
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.impls.ExamAdmissionServiceImpl;
import za.ac.unisa.myadmin.exam.services.repositories.ExamAdmissionRepository;

/**
 * ExamAdmissionServiceImpl Tester.
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
@DirtiesContext
public class ExamAdmissionServiceImplTest {

	@Autowired
	private ExamAdmissionService examAdmissionService;

	@MockBean
	private ExamAdmissionRepository examAdmissionRepository;

	@TestConfiguration
	static class ExamAdmissionServiceImplTestContextConfiguration {
		@Bean
		public ExamAdmissionService examAdmissionService() {
			return new ExamAdmissionServiceImpl();
		}
	}

	@Before
	public void before() throws Exception {
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

} 
