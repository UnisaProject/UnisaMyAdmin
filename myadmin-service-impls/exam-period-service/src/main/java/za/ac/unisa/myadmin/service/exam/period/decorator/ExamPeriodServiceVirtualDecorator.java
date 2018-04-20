package za.ac.unisa.myadmin.service.exam.period.decorator;

import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.DoesNotExistException;
import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.admission.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.admission.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.period.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.period.ExamPeriodService;

/**
 * This decorator creates virtual data ExamPeriods from actual ExamPeriods. Each
 * virtual ExamPeriod includes the exam year and examination timetable type by
 * getting the ExamAdmission record.
 * 
 * @author Jannie
 *
 */
@Service("ExamPeriodServiceVirtualDecorator")
public class ExamPeriodServiceVirtualDecorator implements ExamPeriodService {

	@Autowired
	@Qualifier("ExamPeriodService")
	private ExamPeriodService examPeriodService;

	@Autowired
	@Qualifier("ExamAdmissionService")
	private ExamAdmissionService examAdmissionService;

	@Override
	public ExamPeriodInfo getExamPeriod(Integer code) throws DoesNotExistException, MissingParameterException,
			InvalidParameterException, OperationFailedException {
		return examPeriodService.getExamPeriod(code);
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		List<ExamPeriodInfo> virtuals = new ArrayList<>();
		Set<Integer> years = calculateCurrentYears();
		List<ExamPeriodInfo> examPeriods = examPeriodService.getExamPeriods();

		for (ExamPeriodInfo examPeriod : examPeriods) {
			try {
				virtuals.addAll(createVirtualExamPeriod(examPeriod, years));
			} catch (MissingParameterException | InvalidParameterException e) {
				throw new OperationFailedException(e);
			}
		}

		return virtuals;
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriodByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<ExamPeriodInfo> virtuals = new ArrayList<>();
		Set<Integer> years = calculateCurrentYears();
		List<ExamPeriodInfo> examPeriods = examPeriodService.getExamPeriodByCodes(codes);

		for (ExamPeriodInfo examPeriod : examPeriods) {
			virtuals.addAll(createVirtualExamPeriod(examPeriod, years));
		}

		return virtuals;
	}

	private List<ExamPeriodInfo> createVirtualExamPeriod(ExamPeriodInfo examPeriod, Set<Integer> years)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<ExamPeriodInfo> virtuals = new ArrayList<>();

		for (Integer year : years) {
			ExamPeriodInfo virtualExamPeriod = new ExamPeriodInfo(examPeriod);
			virtualExamPeriod.setExamYear(year);

			List<ExamAdmissionInfo> examAdmissions = examAdmissionService.getExamAdmissionsByYearAndExamPeriodCode(year,
					examPeriod.getCode());
			if (examAdmissions.isEmpty()) {
				virtualExamPeriod.setExamType("Provisional Examination Timetable");
			} else if (examAdmissions.get(0).isAdmissionDone()) {
				virtualExamPeriod.setExamType("Final Examination Timetable");
			} else {
				virtualExamPeriod.setExamType("Provisional Examination Timetable");
			}

			virtuals.add(virtualExamPeriod);
		}

		return virtuals;
	}

	private Set<Integer> calculateCurrentYears() {
		Set<Integer> years = new HashSet<>();
		int currentYear = Year.now(ZoneId.systemDefault()).getValue();
		years.add(currentYear);
		years.add(currentYear - 1);
		years.add(currentYear + 1);

		return years;
	}

}
