package za.ac.unisa.myadmin.service.exam.admission.decorator;

import java.time.Year;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import za.ac.unisa.myadmin.common.exceptions.InvalidParameterException;
import za.ac.unisa.myadmin.common.exceptions.MissingParameterException;
import za.ac.unisa.myadmin.common.exceptions.OperationFailedException;
import za.ac.unisa.myadmin.exam.admission.ExamAdmissionInfo;
import za.ac.unisa.myadmin.exam.admission.ExamAdmissionService;

/**
 * This decorator calculate the current, previous and next year values and
 * filter out the ExamAdmission records where the year value not equals any of
 * the calculated years.
 * 
 * @author Jannie
 *
 */
@Service("ExamAdmissionServiceYearCalculationDecorator")
public class ExamAdmissionServiceYearCalculationDecorator implements ExamAdmissionService {

	@Autowired
	@Qualifier("ExamAdmissionService")
	private ExamAdmissionService examAdmissionService;

	@Override
	public List<ExamAdmissionInfo> getExamAdmissions() throws OperationFailedException {
		List<ExamAdmissionInfo> examAdmissions = examAdmissionService.getExamAdmissions();

		Set<Integer> years = calculateCurrentYears();

		return examAdmissions.stream().filter(examAdmission -> years.contains(examAdmission.getYear()))
				.collect(Collectors.toList());
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYear(Integer year)
			throws InvalidParameterException, OperationFailedException, MissingParameterException {
		return examAdmissionService.getExamAdmissionsByYear(year);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCode(Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByExamPeriodCode(examPeriodCode);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamType(Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByExamType(examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCode(Integer year, Integer examPeriodCode)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByYearAndExamPeriodCode(year, examPeriodCode);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamType(Integer year, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByYearAndExamType(year, examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByExamPeriodCodeAndExamType(Integer examPeriodCode,
			Integer examType) throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByExamPeriodCodeAndExamType(examPeriodCode, examType);
	}

	@Override
	public List<ExamAdmissionInfo> getExamAdmissionsByYearAndExamPeriodCodeAndExamType(Integer year,
			Integer examPeriodCode, Integer examType)
			throws MissingParameterException, InvalidParameterException, OperationFailedException {
		return examAdmissionService.getExamAdmissionsByYearAndExamPeriodCodeAndExamType(year, examPeriodCode, examType);
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
