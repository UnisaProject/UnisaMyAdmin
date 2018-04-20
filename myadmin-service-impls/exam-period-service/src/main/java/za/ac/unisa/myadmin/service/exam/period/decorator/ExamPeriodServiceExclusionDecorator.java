package za.ac.unisa.myadmin.service.exam.period.decorator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.period.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.period.ExamPeriodService;

/**
 * This decorator will exclude old and obsolete data records. It should be used
 * as an interim until the actual data in the database has been cleaned up,
 * e.g.:
 * 
 * "I would rather use our existing XAMPRD table to get the examination periods
 * from. The table contain however two examination period that are no longer in
 * use, period 8 (August) and period 13 Practical Life Sciences. I would suggest
 * that we add a field on the table to indicate if the period is still in use or
 * not (IN_USE_FLAG)."
 * 
 * @author Jannie
 *
 */
@Service("ExamPeriodServiceExclusionDecorator")
public class ExamPeriodServiceExclusionDecorator implements ExamPeriodService {

	@Autowired
	@Qualifier("ExamPeriodServiceVirtualDecorator")
	private ExamPeriodService examPeriodService;

	private Set<Integer> exclusions = Stream.of(8, 13).collect(Collectors.toSet());

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		return examPeriodService.getExamPeriod(code);
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		List<ExamPeriodInfo> examPeriods = examPeriodService.getExamPeriods();

		return examPeriods.stream().filter(examPeriod -> !exclusions.contains(examPeriod.getCode()))
				.collect(Collectors.toList());

	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<ExamPeriodInfo> examPeriods = examPeriodService.getExamPeriodByCodes(codes);

		return examPeriods.stream().filter(examPeriod -> !exclusions.contains(examPeriod.getCode()))
				.collect(Collectors.toList());
	}

}
