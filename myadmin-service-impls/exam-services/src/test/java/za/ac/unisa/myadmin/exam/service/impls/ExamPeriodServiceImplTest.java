package za.ac.unisa.myadmin.exam.service.impls;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
import za.ac.unisa.myadmin.exam.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.service.ExamPeriodService;
import za.ac.unisa.myadmin.exam.service.models.ExamPeriodEntity;
import za.ac.unisa.myadmin.exam.service.repositories.ExamPeriodRepository;

/**
 * ExamPeriodServiceImpl Tester.
 *
 */
@RunWith(SpringRunner.class)
@Import(TestApplication.class)
@DirtiesContext
public class ExamPeriodServiceImplTest {

	private List<Integer> codes;

	private List<ExamPeriodEntity> list;

	private List<ExamPeriodEntity> list2;

	@Autowired
	private ExamPeriodService examPeriodService;

	@MockBean
	private ExamPeriodRepository examPeriodRepository;

	@TestConfiguration
	static class ExamPeriodServiceImplTestContextConfiguration {
		@Bean
		public ExamPeriodService examPeriodService() {
			return new ExamPeriodServiceImpl();
		}
	}

	@Before
	public void before() throws Exception {
		//given
		list = new ArrayList<>();
		list2 = new ArrayList<>();
		ExamPeriodEntity examPeriodEntity = new ExamPeriodEntity();
		examPeriodEntity.setCode(1);
		examPeriodEntity.setAfrDescription("Toets 1");
		examPeriodEntity.setAfrShortDescription("T1");
		examPeriodEntity.setEngDescription("Test 1");
		examPeriodEntity.setEngShortDescription("T1");
		list.add(examPeriodEntity);
		list2.add(examPeriodEntity);
		ExamPeriodEntity examPeriodEntityTwo = new ExamPeriodEntity();
		examPeriodEntityTwo.setCode(8);
		examPeriodEntityTwo.setAfrDescription("Toets 2");
		examPeriodEntityTwo.setAfrShortDescription("T2");
		examPeriodEntityTwo.setEngDescription("Test 2");
		examPeriodEntityTwo.setEngShortDescription("T2");
		list.add(examPeriodEntityTwo);
		ExamPeriodEntity examPeriodEntityThree = new ExamPeriodEntity();
		examPeriodEntityThree.setCode(13);
		examPeriodEntityThree.setAfrDescription("Toets 3");
		examPeriodEntityThree.setAfrShortDescription("T3");
		examPeriodEntityThree.setEngDescription("Test 3");
		examPeriodEntityThree.setEngShortDescription("T3");
		list.add(examPeriodEntityThree);
		list2.add(examPeriodEntityThree);
		ExamPeriodEntity examPeriodEntityFour = new ExamPeriodEntity();
		examPeriodEntityFour.setCode(14);
		examPeriodEntityFour.setAfrDescription("Toets 4");
		examPeriodEntityFour.setAfrShortDescription("T4");
		examPeriodEntityFour.setEngDescription("Test 4");
		examPeriodEntityFour.setEngShortDescription("T4");
		list2.add(examPeriodEntityFour);
		codes = new ArrayList<Integer>();
		codes.add(1);
		codes.add(13);
		codes.add(14);
		//Mock Repo calls
		Optional<ExamPeriodEntity> optional = Optional.of(examPeriodEntity);
		when(examPeriodRepository.findById(1)).thenReturn(optional);
		when(examPeriodRepository.findAll()).thenReturn(list);
		when(examPeriodRepository.findAllById(codes)).thenReturn(list2);
	}

	@After
	public void after() throws Exception {
	}

	/**
	 * Method: getExamPeriod(Integer code)
	 */
	@Test
	public void testGetExamPeriod() throws Exception {
		//when
		ExamPeriodInfo found = examPeriodService.getExamPeriod(1);
		//then
		assertNotNull(found);
		assertThat(found.getCode()).isEqualTo(1);
	}

	/**
	 * Method: getExamPeriods()
	 */
	@Test
	public void testGetExamPeriods() throws Exception {
		//when
		List<ExamPeriodInfo> foundList = examPeriodService.getExamPeriods();
		//then
		assertNotNull(foundList);
		assertThat(foundList.size()).isEqualTo(3);
	}

	/**
	 * Method: getExamPeriodByCodes(List<Integer> codes)
	 */
	@Test
	public void testGetExamPeriodByCodes() throws Exception {
		//when
		List<ExamPeriodInfo> foundList = examPeriodService.getExamPeriodsByCodes(codes);
		//then
		// check if type exist
		assertNotNull(foundList);
		assertThat(foundList.size()).isEqualTo(3);
		assertThat(foundList.get(0).getCode()).isEqualTo(list2.get(0).getCode());
	}
} 
