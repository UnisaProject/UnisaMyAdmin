package za.ac.unisa.myadmin.exam.services.decorators;

import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.services.ExamAdmissionService;
import za.ac.unisa.myadmin.exam.services.ExamPeriodService;
import za.ac.unisa.myadmin.exam.services.ExamServicesConstants;
import za.ac.unisa.myadmin.exam.services.dto.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.services.dto.ExamPeriodInfo;
import za.ac.unisa.myadmin.service.base.decorators.ExamPeriodServiceDecorator;

/**
 * This decorator creates virtual data ExamPeriods from actual ExamPeriods. Each
 * virtual ExamPeriod includes the exam year and examination timetable type by
 * getting the ExamAdmission record.
 * 
 * @author Jannie
 *
 */
public class ExamPeriodServiceVirtualDecorator extends ExamPeriodServiceDecorator implements ExamPeriodService {

	private ExamAdmissionService examAdmissionService;

	public ExamAdmissionService getExamAdmissionService() {
		return examAdmissionService;
	}

	public void setExamAdmissionService(ExamAdmissionService examAdmissionService) {
		this.examAdmissionService = examAdmissionService;
	}

	@Override
	public List<ExamPeriodInfo> getExamPeriods() throws OperationFailedException {
		List<ExamPeriodInfo> virtuals = new ArrayList<>();

		Set<Integer> years = calculateCurrentYears();
		List<ExamPeriodInfo> examPeriods = getNextDecorator().getExamPeriods();
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
		List<ExamPeriodInfo> examPeriods = getNextDecorator().getExamPeriodsByCodes(codes);
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
		String examType = ExamServicesConstants.EXAM_TYPE_PROVISIONAL;

		List<ExamAdmissionInfo> examAdmissions = getExamAdmissionService()
				.getExamAdmissionsByYearAndExamPeriodCode(year, examPeriodCode);
		if (!examAdmissions.isEmpty() && examAdmissions.get(0).isAdmissionDone()) {
			examType = ExamServicesConstants.EXAM_TYPE_FINAL;
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
