package za.ac.unisa.myadmin.exam.services.decorators;

import java.util.List;
import java.util.stream.Collectors;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.ExamServiceConstants;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.service.base.decorators.ExamPeriodServiceDecorator;

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
public class ExamPeriodServiceExclusionDecorator extends ExamPeriodServiceDecorator implements ExamPeriodService {

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		List<ExamPeriodInfo> examPeriods = getNextDecorator().getExamPeriods();

		return filterObsoleteCodes(examPeriods);

	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodsByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<ExamPeriodInfo> examPeriods = getNextDecorator().getExamPeriodsByCodes(codes);

		return filterObsoleteCodes(examPeriods);
	}

	private List<ExamPeriodInfo> filterObsoleteCodes(List<ExamPeriodInfo> examPeriods) {
		return examPeriods.stream()
				.filter(examPeriod -> !ExamServiceConstants.EXAM_PERIOD_OBSOLETE_CODES.contains(examPeriod.getCode()))
				.collect(Collectors.toList());
	}

}
