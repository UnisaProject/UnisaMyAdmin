package za.ac.unisa.myadmin.exam.services.impls;

import org.junit.BeforeClass;
import org.junit.Test;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.services.jpa.models.ExamPeriodEntity;
import za.ac.unisa.myadmin.exam.services.repositories.ExamPeriodRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * ExamPeriodServiceImpl Tester.
 *
 */
public class ExamPeriodServiceImplTest {

	private static final List<Integer> codes = Arrays.asList(1, 13, 14);

	private static ExamPeriodServiceImpl examPeriodService;

	private static final List<ExamPeriodEntity> list = new ArrayList<>();
	private static final List<ExamPeriodEntity> list2 = new ArrayList<>();

	private static ExamPeriodRepository createExamPeriodRepository(){
		ExamPeriodRepository examPeriodRepository = mock(ExamPeriodRepository.class);

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

		//Mock Repo calls
		Optional<ExamPeriodEntity> optional = Optional.of(examPeriodEntity);
		when(examPeriodRepository.findById(1)).thenReturn(optional);
		when(examPeriodRepository.findAll()).thenReturn(list);
		when(examPeriodRepository.findAllById(codes)).thenReturn(list2);
		return examPeriodRepository;
	}

	@BeforeClass
	public static void beforeClass() {
		examPeriodService = new ExamPeriodServiceImpl();
		examPeriodService.setExamPeriodRepository(createExamPeriodRepository());
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
		//thenExamAdmissionServiceYearCalculationDecoratorTest
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
