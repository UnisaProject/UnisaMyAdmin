package za.ac.unisa.myadmin.exam.service.decorators;

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
import za.ac.unisa.myadmin.exam.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.exam.service.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.service.ExamPeriodService;
import za.ac.unisa.myadmin.exam.service.ExamServiceConstants;

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
	public List<ExamPeriodInfo> getExamPeriodsByCodes(List<Integer> codes)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		List<ExamPeriodInfo> virtuals = new ArrayList<>();

		Set<Integer> years = calculateCurrentYears();
		List<ExamPeriodInfo> examPeriods = examPeriodService.getExamPeriodsByCodes(codes);
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
			virtualExamPeriod.setExamType(calculateExamType(year, examPeriod.getCode()));
			virtuals.add(virtualExamPeriod);
		}

		return virtuals;
	}

	private String calculateExamType(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		String examType = ExamServiceConstants.EXAM_TYPE_PROVISIONAL;

		List<ExamAdmissionInfo> examAdmissions = examAdmissionService.getExamAdmissionsByYearAndExamPeriodCode(year,
				examPeriodCode);
		if (!examAdmissions.isEmpty() && examAdmissions.get(0).isAdmissionDone()) {
			examType = ExamServiceConstants.EXAM_TYPE_FINAL;
		}

		return examType;
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
